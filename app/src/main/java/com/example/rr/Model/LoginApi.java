package com.example.rr.Model;

import com.example.rr.entity.UserEntity;
import com.example.rr.http.HttpMethod;
import com.example.rr.http.LoginService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mzp on 2016/9/13.
 */
public class LoginApi
{
    public void loginRequest(Subscriber<UserEntity> subscriber, String userName, String password)
    {
        LoginService loginService = HttpMethod.getInstance().create(LoginService.class);

        Observable observable = loginService.getTopMovie(userName, password);
        /**
         * 设置事件发生在新的线程, 也可以设置为"Schedulers.io()"
         * 事件处理在主线程中
         * Schedulers.io()和schedulers.newThread()的区别,
         * Schedulers.io()中通过线程池来操作线程的
         * 而使用newThread()每次访问都会有线程的创建以及销毁, 这样来说的话一般使用io线程是比较合适的
         */
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
