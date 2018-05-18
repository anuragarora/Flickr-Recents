package com.anurag.flickr.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.anurag.flickr.R;
import com.anurag.flickr.event.global.NetworkStatusChangedEvent;
import com.anurag.flickr.event.photo.GetRecentPhotosSuccessResponseEvent;
import com.anurag.flickr.fetcher.GetPhotoFetcher;
import com.anurag.flickr.model.GetRecentPhotosFailureResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.anurag.flickr.module.FetcherModule.getPhotosFetcher;

public class GetRecentPhotosActivity extends BaseActivity {

    private static final String TAG = GetRecentPhotosActivity.class.getSimpleName();
    @BindView(R.id.activity_flickr_recents_container)
    public CoordinatorLayout mCoordinator;

    /*@BindView(R.id.activity_flickr_recents_text_view)
    public TextView mTestTextView;*/

    private Snackbar mSnackbar;
    private final GetPhotoFetcher mPhotoFetcher;

    public GetRecentPhotosActivity() {
        this(getPhotosFetcher());
    }

    public GetRecentPhotosActivity(GetPhotoFetcher photoFetcher) {
        mPhotoFetcher = photoFetcher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_photos);

        ButterKnife.bind(this);

        // Initializing Snackbar for display
        mSnackbar = Snackbar.make(mCoordinator, "Network connection unavailable",
                Snackbar.LENGTH_INDEFINITE);

        // Test calling the api
        mPhotoFetcher.getRecentPhotos(1);
    }

    public void onEvent(NetworkStatusChangedEvent event) {
        if (event.isNetworkEnabled()) {
            if(mSnackbar.isShown())
                mSnackbar.dismiss();

            //TODO: Perform additional action when network connection is back.
        } else {
            mSnackbar.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* Perform any additional action if required.
                     Snack bar automatically dismisses itself onClick. */
                }
            }).show();
        }
    }

    public void onEvent(GetRecentPhotosSuccessResponseEvent event) {
        //Logger.i(TAG, event.getResponse().getStatus());
        //mTestTextView.setText(event.getResponse().getStatus());
    }

    public void onEvent(GetRecentPhotosFailureResponse event) {

    }
}
