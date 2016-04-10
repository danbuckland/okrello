package com.blocksolid.okrello.api;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dan Buckland on 04/02/2016.
 */
public class ServiceGenerator {

    // public static final String BASE_URL = "https://api.trello.com/1/"; // production
    public static final String BASE_URL = "http://10.0.2.2:9292/"; // mock backend with emulator

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static Interceptor logging = interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .addInterceptor(logging)
            .build();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
