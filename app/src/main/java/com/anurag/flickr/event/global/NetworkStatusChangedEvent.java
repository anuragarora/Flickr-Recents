package com.anurag.flickr.event.global;

public class NetworkStatusChangedEvent {
    private boolean mNetworkEnabled;

    public NetworkStatusChangedEvent(boolean networkEnabled) {
        mNetworkEnabled = networkEnabled;
    }

    public boolean isNetworkEnabled() {
        return mNetworkEnabled;
    }
}
