package com.example.rr.http;

import com.example.rr.entity.BaseEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mzp on 2016/9/6.
 */
public interface MovieService
{
    @FormUrlEncoded
    @POST("/api/?m=api&c=&a=login_do")
    Observable<BaseEntity> getTopMovie(@Field("mobile") String mobile, @Field("password") String password);
}
