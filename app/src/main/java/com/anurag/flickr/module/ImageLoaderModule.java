package com.anurag.flickr.module;

import com.anurag.flickr.image.ImageLoader;
import com.anurag.flickr.image.PicassoImageLoader;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import static com.anurag.flickr.module.ApplicationModule.applicationContext;

public class ImageLoaderModule {
    private static final int MAX_CACHE_SIZE = 100 * 1024 * 1024;

    private static Picasso sPicasso;

    public static ImageLoader picassoImageLoader() {
        if (sPicasso == null) {
            sPicasso = new Picasso.Builder(applicationContext())
                    .downloader(new OkHttp3Downloader(applicationContext(), MAX_CACHE_SIZE))
                    .build();
        }

        return new PicassoImageLoader(sPicasso);
    }
}
