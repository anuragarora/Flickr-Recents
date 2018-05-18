package com.anurag.flickr.image;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class PicassoImageLoader implements ImageLoader {
    private final Picasso mPicasso;

    public PicassoImageLoader(Picasso picasso) {
        mPicasso = picasso;
    }

    @Override
    public void loadImage(String url, final ImageFetchListener imageFetchListener,
                          int placeholder,
                          int errorPlaceholder) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageFetchListener.onBitmapLoaded(bitmap, from == Picasso.LoadedFrom.MEMORY);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                imageFetchListener.onBitmapFailed(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                imageFetchListener.onPrepareLoad(placeHolderDrawable);
            }
        };

        RequestCreator requestCreator = mPicasso.load(url)
                .placeholder(placeholder)
                .error(errorPlaceholder);

        requestCreator.into(target);
        imageFetchListener.saveReference(target);
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        mPicasso.load(url)
                .into(imageView);
    }

    @Override
    public void cancelRequest(Object tag) {
        if(tag != null) {
            mPicasso.cancelRequest((Target) tag);
        }
    }
}
