package com.anurag.flickr.module;


import com.anurag.flickr.loader.GetPhotoLoader;
import com.anurag.flickr.loader.GetPhotoNetworkLoader;

import static com.anurag.flickr.module.ApplicationModule.resources;
import static com.anurag.flickr.module.EventBusModule.eventBus;
import static com.anurag.flickr.module.NetworkManagerModule.retrofitNetworkManager;
import static com.anurag.flickr.module.RepositoryModule.recentPhotosRepository;

/**
 * Module for fetcher/loader to facilitate dependency injection
 */
public class LoaderModule {
    public static GetPhotoLoader getPhotosFetcher() {
        return new GetPhotoNetworkLoader(retrofitNetworkManager(), eventBus(), resources(),
                recentPhotosRepository());
    }
}
