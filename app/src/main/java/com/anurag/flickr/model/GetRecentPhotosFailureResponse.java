package com.anurag.flickr.model;

/**
 * Created by anurag on 17/05/18.
 */

public class GetRecentPhotosFailureResponse {
    private final String mStat;
    private final int mCode;
    private final String mMessage;

    private GetRecentPhotosFailureResponse(Builder builder) {
        mStat = builder.mStat;
        mCode = builder.mCode;
        mMessage = builder.mMessage;
    }

    public static final class Builder {
        private final String mStat;
        private final int mCode;
        private final String mMessage;

        public Builder(String mStat, int mCode, String mMessage) {
            this.mStat = mStat;
            this.mCode = mCode;
            this.mMessage = mMessage;
        }

        public GetRecentPhotosFailureResponse build() {
            return new GetRecentPhotosFailureResponse(this);
        }
    }
}
