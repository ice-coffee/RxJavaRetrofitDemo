package com.example.rr.View;

import com.example.rr.Model.UserInfos;

/**
 * Created by mzp on 2016/9/13.
 */
public interface LoginView
{
    String getUserName();

    String getPassword();

    void loginSuccess(UserInfos userInfos);
}
