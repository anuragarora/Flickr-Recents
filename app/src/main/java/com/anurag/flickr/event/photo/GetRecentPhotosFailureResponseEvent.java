package com.anurag.flickr.event.photo;

import com.anurag.flickr.model.GetRecentPhotosFailureResponse;

/**
 * Created by anurag on 17/05/18.
 */

public class GetRecentPhotosFailureResponseEvent {
    private GetRecentPhotosFailureResponse mFailureResponse;

    public GetRecentPhotosFailureResponseEvent(GetRecentPhotosFailureResponse mFailureResponse) {
        this.mFailureResponse = mFailureResponse;
    }

    public GetRecentPhotosFailureResponse getResponse() {
        return mFailureResponse;
    }
}
