package com.example.rr.http;

import com.example.rr.entity.BaseEntity;
import com.example.rr.entity.UserInfos;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 1.这样的话HttpMethod和Service耦合的太严重了
 */
public class HttpMethod
{
    private final static String BASEURL = "http://www.oyochou.com/";

    //默认超时时间
    private static final int DEFAULT_TIMEOUT = 10;

    private static HttpMethod instance;

    private Retrofit retrofit;

    private MovieService movieService;

    private HttpMethod()
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

        movieService = retrofit.create(MovieService.class);
    }

    /**
     * 单例模式创建实例
     * @return
     */
    public static HttpMethod getInstance()
    {
        if (null == instance)
        {
            instance = new HttpMethod();
        }

        return instance;
    }

    public void getMovie(Subscriber subscriber)
    {
        Observable observable = movieService.getTopMovie("13683622079", "112233");
        /**
         * 设置事件发生在新的线程, 也可以设置为"Schedulers.io()"
         * 事件处理在主线程中
         * Schedulers.io()和schedulers.newThread()的区别,
         * Schedulers.io()中通过线程池来操作线程的
         * 而使用newThread()每次访问都会有线程的创建以及销毁, 这样来说的话一般使用io线程是比较合适的
         */
        observable
//                .map(new HttpResultFunc<UserInfos>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
//    private class HttpResultFunc<T> implements Func1<BaseEntity<T>, T>{
//
//        @Override
//        public T call(BaseEntity<T> tBaseEntity)
//        {
//            T t = tBaseEntity.getData();
//            return t;
//        }
//    }

}
