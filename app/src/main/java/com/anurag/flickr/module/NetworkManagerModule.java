package com.anurag.flickr.module;


import com.anurag.flickr.R;
import com.anurag.flickr.network.BasicAuthorizationRequestInterceptor;
import com.anurag.flickr.network.NetworkManager;
import com.anurag.flickr.network.RetrofitNetworkManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.anurag.flickr.module.ApplicationModule.resources;
import static com.anurag.flickr.module.ConverterModule.gsonConverter;
import static com.anurag.flickr.module.ConverterModule.recentPhotosConverter;

/**
 * Network Manager Module
 */
public class NetworkManagerModule {
    private static final int TIMEOUT = 60;
    private static final String TAG = NetworkManagerModule.class.getSimpleName();
    private static OkHttpClient sOkClient;

    public static NetworkManager retrofitNetworkManager() {
        return new RetrofitNetworkManager(okClient(),
                resources().getString(R.string.base_url),
                gsonConverter(), recentPhotosConverter(), resources());
    }

    private static OkHttpClient okClient() {
        if (sOkClient == null) {
            sOkClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new BasicAuthorizationRequestInterceptor(resources()))
                    .build();
        }

        return sOkClient;
    }


}
