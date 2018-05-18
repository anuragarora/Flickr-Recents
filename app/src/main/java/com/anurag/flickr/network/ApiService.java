package com.anurag.flickr.network;

import com.anurag.flickr.model.GetRecentPhotosResponse;
import com.anurag.flickr.model.server.ServerGetRecentPhotosSuccessResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Endpoints for the REST web services
 */
public interface ApiService {

    @GET("/services/rest")
    Call<GetRecentPhotosResponse> getRecentPhotos(@Query("method") String method,
                                                  @Query("page") String page);

    /*@GET("data/2.5/find")
    Call<WeatherResponse> getWeatherByGeoData(@Query("lat") String lat, @Query("lon") String lon,
                                              @Query("cnt") String cities, @Query("appid") String appId);

    @GET("data/2.5/find")
    Call<WeatherResponse> getWeatherByCityName(@Query("appid") String appid, @Query("q") String cityName);

    @GET("data/2.5/forecast/daily")
    Call<WeatherForecastResponse> get16DayWeatherForcastByCityName(@Query("appid") String appid,
                                                                   @Query("q") String cityName,
                                                                   @Query("cnt") String count);


    @GET("data/2.5/forecast/daily")
    Call<WeatherForecastResponse> get16DayWeatherForecastByGeoData(@Query("lat") String lat, @Query("lon") String lon,
                                                                   @Query("cnt") String dayCount, @Query("appid") String appId);*/
}
