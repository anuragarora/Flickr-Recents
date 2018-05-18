package com.anurag.flickr.module;


import com.anurag.flickr.fetcher.GetPhotoFetcher;
import com.anurag.flickr.fetcher.GetPhotoNetworkFetcher;

import static com.anurag.flickr.module.EventBusModule.eventBus;
import static com.anurag.flickr.module.NetworkManagerModule.retrofitNetworkManager;

/**
 * Module for fetcher/loader to facilitate dependency injection
 */
public class FetcherModule {
    public static GetPhotoFetcher getPhotosFetcher() {
        return new GetPhotoNetworkFetcher(retrofitNetworkManager(), eventBus());
    }
}
