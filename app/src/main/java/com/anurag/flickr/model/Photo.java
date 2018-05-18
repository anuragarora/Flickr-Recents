package com.anurag.flickr.model;

/**
 * Created by anurag on 18/05/18.
 */

public class Photo {
    private final String mUrl;
    private final String mTitle;

    private Photo(Builder builder) {
        mUrl = builder.mUrl;
        mTitle = builder.mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }


    public static final class Builder {
        private String mUrl;
        private String mTitle;

        public Builder() {
        }

        public Builder url(String val) {
            mUrl = val;
            return this;
        }

        public Builder title(String val) {
            mTitle = val;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }
}
