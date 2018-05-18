package com.anurag.flickr.fetcher;

import com.anurag.flickr.event.photo.GetRecentPhotosSuccessResponseEvent;
import com.anurag.flickr.model.GetRecentPhotosResponse;
import com.anurag.flickr.network.NetworkManager;
import com.anurag.flickr.util.Logger;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by anurag on 10/04/17.
 */
public class GetPhotoNetworkFetcher implements GetPhotoFetcher, Callback<GetRecentPhotosResponse> {
    private final NetworkManager mNetworkManager;
    private final EventBus mEventBus;

    public GetPhotoNetworkFetcher(NetworkManager networkManager, EventBus eventBus) {
        this.mNetworkManager = networkManager;
        this.mEventBus = eventBus;
    }

    @Override
    public void getRecentPhotos(int page) {
        mNetworkManager.getRecentPhotos(1, this);
    }

    @Override
    public void onResponse(Call<GetRecentPhotosResponse> call, Response<GetRecentPhotosResponse> response) {
        if(response.isSuccessful()) {

        }
        Logger.i(GetPhotoNetworkFetcher.class.getSimpleName(), "Response successfully received");
        mEventBus.post(new GetRecentPhotosSuccessResponseEvent(response.body()));
    }

    @Override
    public void onFailure(Call<GetRecentPhotosResponse> call, Throwable t) {
        Logger.i(GetPhotoNetworkFetcher.class.getSimpleName(), "Response failure");
        //mEventBus.post(new GetRecentPhotosFailureResponseEvent());
        Logger.i(GetPhotoNetworkFetcher.class.getSimpleName(), "Failure Response: " + t.getMessage());
    }
}
