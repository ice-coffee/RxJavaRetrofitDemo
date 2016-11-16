package com.example.rr.listener;

/**
 * Created by mzp on 2016/9/11.
 */
public interface SubscriberListener
{
    /**
     * 请求成功回调
     * @param dataMsg
     */
    void onSuccess(String dataMsg);

    /**
     * 请求状态为error回调
     * @param errorMsg
     */
    void onError(String errorMsg);
}
