package com.anurag.flickr.event.photo;

import com.anurag.flickr.model.GetRecentPhotosResponse;

/**
 * Created by anurag on 17/05/18.
 */

public class GetRecentPhotosSuccessResponseEvent {
    private GetRecentPhotosResponse mSuccessResponse;


    public GetRecentPhotosSuccessResponseEvent(GetRecentPhotosResponse mSuccessResponse) {
        this.mSuccessResponse = mSuccessResponse;
    }

    public GetRecentPhotosResponse getResponse() {
        return mSuccessResponse;
    }
}
