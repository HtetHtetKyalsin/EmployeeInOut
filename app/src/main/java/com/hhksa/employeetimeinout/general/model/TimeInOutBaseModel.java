package com.hhksa.employeetimeinout.general.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hhksa.employeetimeinout.general.api.TimeInOutService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimeInOutBaseModel {
    protected TimeInOutService theApi;
    protected String apiUrl;
    CacheControl cacheControl;

    public TimeInOutBaseModel(final Context context) {
        apiUrl = "https://api.helpster.tech/v1/staff-requests/";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
//                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                        Credentials.basic("+6281313272005","alexander"));
                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                       "token 5a8971dd007661589fde8bc28567ffdfc6f7e31c");
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .cache(provideCache(context))
                .addInterceptor(provideCacheInterceptor(context))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(TimeInOutService.class);

    }

    private Cache provideCache(Context context) {
        Cache cache = null;
        try {
            cache = new Cache(new File(context.getCacheDir(), "http-cache"),
                    10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        return cache;
    }

    public Interceptor provideCacheInterceptor(Context context) {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                    cacheControl = new CacheControl.Builder()
                            .maxAge(5, TimeUnit.MINUTES)
                            .build();

                return response.newBuilder()
                        /*.removeHeader("Pragma")
                        .removeHeader("Cache-Control")*/
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        };
    }

}
