package com.anurag.flickr.network;

import com.anurag.flickr.model.server.ServerGetRecentPhotosSuccessResponse;

import retrofit2.Callback;

/**
 * Network Manager interface.
 */
public interface NetworkManager {

    void getRecentPhotos(int page, Callback<ServerGetRecentPhotosSuccessResponse> callback);

    /*void getRecentPhotos(final String lat, final String lon, Callback<WeatherResponse> callback);

    void getWeatherByCityName(final String fullCityName, Callback<WeatherResponse> callback);

    void getWeatherForecastByCityName(final String fullCityName, Callback<WeatherForecastResponse> callback);

    void getWeatherForecastByGeoData(final String lat, final String lon, Callback<WeatherForecastResponse> callback);*/
}
