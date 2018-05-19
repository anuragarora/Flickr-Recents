package com.anurag.flickr.module;

import com.anurag.flickr.Repository.AndroidFlickrPreferences;
import com.anurag.flickr.Repository.FlickrPreferences;
import com.anurag.flickr.Repository.PreferenceRecentPhotosRepository;
import com.anurag.flickr.Repository.RecentPhotosRepository;

import static com.anurag.flickr.module.ApplicationModule.applicationContext;

/**
 * Repository module to facilitate dependency injection.
 */
public class RepositoryModule {
    private static FlickrPreferences androidFlickrPreferences() {
        return new AndroidFlickrPreferences(applicationContext());
    }

    public static RecentPhotosRepository recentPhotosRepository() {
        return new PreferenceRecentPhotosRepository(androidFlickrPreferences());
    }
}
