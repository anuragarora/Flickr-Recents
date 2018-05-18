package com.anurag.flickr.module;

import com.anurag.flickr.image.ImageLoader;
import com.anurag.flickr.image.PicassoImageLoader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import static com.anurag.flickr.module.ApplicationModule.applicationContext;

public class ImageLoaderModule {
    private static final int MAX_CACHE_SIZE = 42 * 1024 * 1024;

    private static Picasso sPicasso;

    public static ImageLoader picassoImageLoader() {
        if (sPicasso == null) {
            sPicasso = new Picasso.Builder(applicationContext())
                    .downloader(new OkHttpDownloader(applicationContext(), MAX_CACHE_SIZE))
                    .build();
        }

        return new PicassoImageLoader(sPicasso);
    }
}
