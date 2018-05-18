package com.anurag.flickr.image;

import android.widget.ImageView;

public interface ImageLoader {
    void loadImage(String url, ImageFetchListener imageFetchListener, int placeholder, int errorPlaceholder);

    void loadImage(String url, ImageView imageView);

    void cancelRequest(Object tag);
}
