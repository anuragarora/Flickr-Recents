package com.anurag.flickr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anurag.flickr.R;
import com.anurag.flickr.image.ImageLoader;
import com.anurag.flickr.model.server.ServerPhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PhotoAdapter
 */

public class PhotoAdapter extends BaseAdapter {
    private final LayoutInflater mLayoutInflator;
    private final ImageLoader mImageLoader;
    private final List<ServerPhoto> mServerPhotoList = new ArrayList<>();

    public PhotoAdapter(Context context, ImageLoader imageLoader) {
        mLayoutInflator = LayoutInflater.from(context);
        mImageLoader = imageLoader;
    }

    public void setList(List<ServerPhoto> list) {
        mServerPhotoList.clear();
        mServerPhotoList.addAll(list);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mServerPhotoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mServerPhotoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ServerPhoto serverPhoto = ((ServerPhoto) getItem(position));
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflator
                    .inflate(R.layout.view_photo, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Setting image
        //mImageLoader.loadImage();

        // Setting Title
        holder.mTitle.setText(serverPhoto.getTitle());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.view_photo_image_view)
        ImageView mImage;
        @BindView(R.id.view_photo_title)
        TextView mTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
