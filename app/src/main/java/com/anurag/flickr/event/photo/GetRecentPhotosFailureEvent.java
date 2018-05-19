package com.anurag.flickr.event.photo;

/**
 * Created by anurag on 18/05/18.
 */

public class GetRecentPhotosFailureEvent {
    private final String mMessage;

    public GetRecentPhotosFailureEvent(String message) {
        this.mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
