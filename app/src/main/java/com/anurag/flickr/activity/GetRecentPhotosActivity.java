package com.anurag.flickr.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.anurag.flickr.R;
import com.anurag.flickr.adapter.PhotoAdapter;
import com.anurag.flickr.event.global.NetworkStatusChangedEvent;
import com.anurag.flickr.event.photo.GetRecentPhotosFailureEvent;
import com.anurag.flickr.event.photo.GetRecentPhotosSuccessResponseEvent;
import com.anurag.flickr.image.ImageLoader;
import com.anurag.flickr.loader.GetPhotoLoader;
import com.anurag.flickr.model.GetRecentPhotosResponse;
import com.anurag.flickr.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.anurag.flickr.module.ApplicationModule.applicationContext;
import static com.anurag.flickr.module.ImageLoaderModule.picassoImageLoader;
import static com.anurag.flickr.module.LoaderModule.getPhotosFetcher;

/**
 * Activity showing a list view of Recent Flickr Pics (upto a 1000 pics) from API
 */
public class GetRecentPhotosActivity extends BaseActivity {
    private static final String TAG = GetRecentPhotosActivity.class.getSimpleName();

    @BindView(R.id.activity_flickr_recents_container)
    public CoordinatorLayout mCoordinator;

    @BindView(R.id.activity_flickr_list_view)
    public ListView mListView;

    @BindView(R.id.activity_flickr_recents_progress)
    public ProgressBar mLoadingIndicator;

    private View mFooterView;
    private int mCurrentPage = 0;

    // Making Snackbar public to facilitate testing via robolectric, TODO: investigate shadowing!
    public Snackbar mSnackbar;
    private PhotoAdapter mAdapter;

    private final GetPhotoLoader mPhotoFetcher;
    private final ImageLoader mImageLoader;

    public GetRecentPhotosActivity() {
        this(getPhotosFetcher(), picassoImageLoader());
    }

    public GetRecentPhotosActivity(GetPhotoLoader photoFetcher, ImageLoader imageLoader) {
        mPhotoFetcher = photoFetcher;
        mImageLoader = imageLoader;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_photos);

        // Making app fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initializing required components
        ButterKnife.bind(this);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mSnackbar = Snackbar.make(mCoordinator, R.string.activity_network_unavailable_copy,
                Snackbar.LENGTH_INDEFINITE);
        mAdapter = new PhotoAdapter(this, mImageLoader);
        mFooterView = LayoutInflater.from(applicationContext())
                .inflate(R.layout.view_photo_list_footer, null, false);
        mFooterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoFetcher.getRecentPhotos(++mCurrentPage);
            }
        });

        // Making get photos network call
        mPhotoFetcher.getRecentPhotos(mCurrentPage);
    }

    public void onEvent(NetworkStatusChangedEvent event) {
        if (event.isNetworkEnabled()) {
            if (mSnackbar.isShown())
                mSnackbar.dismiss();

            // Perform additional action when network connection is back here
        } else {
            mSnackbar.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* Perform any additional action ?
                     Snack bar automatically dismisses itself onClick. */
                }
            }).show();
        }
    }

    /**
     * Invoked when GetRecentPhotos network call succeeds
     *
     * @param event Success Event object
     */
    public void onEvent(GetRecentPhotosSuccessResponseEvent event) {
        final GetRecentPhotosResponse response = event.getResponse();
        // Initializing footerView for loading more items onClick
        // Also check if response is same as last response which was persisted

        if (event.isNetworkResponse()) {
            mCurrentPage = response.getPage();
            if (mCurrentPage == 1) {
                mAdapter.clearList();
                mListView.addFooterView(mFooterView);
            }
            if (response.getPage() * response.getPerPage() >= response.getTotal()) {
                mFooterView.setVisibility(View.GONE);
            } else {
                mFooterView.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addToList(response.getPhotoList());
        mAdapter.notifyDataSetChanged();

        if (mListView.getAdapter() == null) {
            mListView.setAdapter(mAdapter);
            mLoadingIndicator.setVisibility(View.GONE);
        }
    }

    /**
     * Invoked when GetRecentPhotos network call fails
     *
     * @param event Failure Event object
     */
    public void onEvent(GetRecentPhotosFailureEvent event) {
        Logger.i(TAG, "Inside failure event: " + event.getMessage());
        Snackbar.make(mCoordinator, event.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
