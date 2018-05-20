package com.anurag.flickr.module;


import com.anurag.flickr.converter.PhotoResponseConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.anurag.flickr.module.ApplicationModule.resources;

/**
 * Converter to help with request/response serialization/deserialization.
 */
public class ConverterModule {
    private static final String GSON_DATE_FORMAT = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";
    private static final Gson gson = new GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .create();

    public static Converter.Factory gsonConverter() {
        return GsonConverterFactory.create(gson);
    }

    public static Converter.Factory recentPhotosConverter() {
        return new PhotoResponseConverter(gsonConverter(), resources());
    }
}
