package com.wessam.footballleagueapplication.data.network;

import com.wessam.footballleagueapplication.MyApplication;
import com.wessam.footballleagueapplication.utils.Constants;
import com.wessam.footballleagueapplication.utils.NetworkHelper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_PRAGMA = "Pragma";
    private static final long cacheSize = 10 * 1024 * 1024; // 10 MB

    private RetrofitClient() {
    }

    public static Retrofit getClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor())
                .addInterceptor(offlineInterceptor())

                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(Constants.LEAGUE_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static Cache cache(){
        return new Cache(new File(MyApplication.getInstance().getCacheDir(),"football_league_cache"), cacheSize);
    }

    /**
     * This interceptor will be called both if the network is available and if the network is not available
     */
    private static Interceptor offlineInterceptor() {
        return chain -> {
            Request request = chain.request();

            if (!NetworkHelper.isNetworkAvailable(MyApplication.getInstance())) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(30, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build();
            }

            return chain.proceed(request);
        };
    }

    /**
     * This interceptor will be called only if the network is available
     */
    private static Interceptor networkInterceptor() {
        return chain -> {

            Request original = chain.request();

            Request request = original.newBuilder()
                    .addHeader("X-Auth-Token", Constants.LEAGUE_API_KEY)
                    .build();

            Response response = chain.proceed(request);

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

}