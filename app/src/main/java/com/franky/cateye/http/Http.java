package com.franky.cateye.http;

import com.franky.cateye.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/1/28.
 * Retrofit 抽取
 */

public class Http {

    private static final long DEFAULT_TIMEOUT = 10;
    private static Retrofit sRetrofit;
    private static final OkHttpClient CLIENT;

    static {
        //设置超时
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (BuildConfig.LOG_DEBUG){
//            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS));
//        }
        CLIENT = builder
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(CLIENT)
                .build();
    }

    public static <T> T create(Class<T> cls) {
        return sRetrofit.create(cls);
    }

}
