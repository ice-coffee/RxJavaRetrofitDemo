package com.example.rr.http;

import com.example.rr.Model.BaseEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mzp on 2016/9/6.
 */
public interface ApiInterface
{
    @FormUrlEncoded
    @POST(HttpContents.API_LOGIN)
    Observable<BaseEntity> getTopMovie(@Field("mobile") String mobile, @Field("password") String password);
}
