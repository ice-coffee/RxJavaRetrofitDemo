package com.example.rr.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 1.这样的话HttpMethod和Service耦合的太严重了
 */
public class RetrofitFactory
{
    private final static String BASEURL = "http://www.oyochou.com/";

    //默认超时时间
    private static final int DEFAULT_TIMEOUT = 10;

    private static Retrofit retrofit;

    private RetrofitFactory()
    {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        /**
         * addConverterFactory添加Gson解析器
         * addCallAdapterFactory(RxJavaCallAdapterFactory.create())后调用service的返回值为Observable
         */
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEURL)
                .build();
    }

    /**
     * 单例模式创建实例
     * 懒汉式单例模式需要加 `synchronized`
     * @return
     */
    public synchronized static Retrofit getInstance()
    {
        if (null == retrofit)
        {
            new RetrofitFactory();
        }

        return retrofit;
    }
}
