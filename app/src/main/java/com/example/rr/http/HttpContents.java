package com.example.rr.http;

/**
 * Created by mzp on 2016/11/15.
 */

public interface HttpContents
{
    //域名
    String DOMAIN_URL = "http://www.oyochou.com/";

    //默认超时时间
    int DEFAULT_TIMEOUT = 10;

    //请求状态
    String SUCCESS = "0";
    String ERROR = "1";
    String FAIL = "2";

    //登录
    String API_LOGIN = "/api/?m=api&c=&a=login_do";
}
