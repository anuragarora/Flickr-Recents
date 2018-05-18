package com.anurag.flickr.network;

import android.content.res.Resources;

import com.anurag.flickr.model.GetRecentPhotosResponse;
import com.anurag.flickr.model.server.ServerGetRecentPhotosSuccessResponse;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Implementation class of NetworkManager interface via RetrofitNetworkManager
 */
public class RetrofitNetworkManager implements NetworkManager {
    private static final String TAG = RetrofitNetworkManager.class.getSimpleName();
    private final String GET_RECENT_PHOTOS_QUERY_METHOD = "flickr.photos.getRecent";

    private final OkHttpClient mOkClient;
    private final String mBaseUrl;
    private final Converter.Factory mGsonConverter;
    private final Converter.Factory mRecentPhotosConverter;
    private final Resources mResources;

    public RetrofitNetworkManager(OkHttpClient okClient,
                                  String baseUrl,
                                  Converter.Factory gsonConverter,
                                  Converter.Factory recentPhotosConverter,
                                  Resources resources) {

        this.mOkClient = okClient;
        this.mBaseUrl = baseUrl;
        this.mGsonConverter = gsonConverter;
        this.mRecentPhotosConverter = recentPhotosConverter;
        this.mResources = resources;
    }

    /**
     * Creates a partially build retrofit object for Flickr service.
     *
     * @return Builder for retrofit with OpenWeatherMap base url and okHttpClient
     */
    private Retrofit.Builder getAdapter() {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mOkClient);
    }

    @Override
    public void getRecentPhotos(int page, Callback<GetRecentPhotosResponse> callback) {
        getAdapter()
                .addConverterFactory(mRecentPhotosConverter)
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .getRecentPhotos(GET_RECENT_PHOTOS_QUERY_METHOD, String.valueOf(page))
                .enqueue(callback);
    }

    /*@Override
    public void getWeatherByGeoData(String lat, String lon, Callback<WeatherResponse> callback) {
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .getWeatherByGeoData(lat, lon, "1", mResources.getString(R.string.weather_open_weather_map_appid))
                .enqueue(callback);
    }

    @Override
    public void getWeatherByCityName(String fullCityName, Callback<WeatherResponse> callback) {
        Logger.i(TAG, "Reached retrofit network manager cityName");
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .getWeatherByCityName(mResources.getString(R.string.weather_open_weather_map_appid), fullCityName)
                .enqueue(callback);
    }

    @Override
    public void getWeatherForecastByCityName(String fullCityName, Callback<WeatherForecastResponse> callback) {
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .get16DayWeatherForcastByCityName(mResources.getString(R.string.weather_open_weather_map_appid),
                        fullCityName, "16")
                .enqueue(callback);
    }

    @Override
    public void getWeatherForecastByGeoData(String lat, String lon, Callback<WeatherForecastResponse> callback) {
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .get16DayWeatherForecastByGeoData(lat, lon, "16",
                        mResources.getString(R.string.weather_open_weather_map_appid))
                .enqueue(callback);
    }*/
}
