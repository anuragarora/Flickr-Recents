package com.anurag.flickr.image;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface ImageFetchListener {
    void onBitmapLoaded(Bitmap bitmap, boolean fromMemory);

    void onBitmapFailed(Drawable errorDrawable);

    void onPrepareLoad(Drawable placeHolderDrawable);

    void saveReference(Object o);
}
