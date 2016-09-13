package com.example.rr.presenter;

import android.content.Context;

import com.example.rr.Model.LoginApi;
import com.example.rr.View.LoginView;
import com.example.rr.entity.UserEntity;
import com.example.rr.listener.SubscriberOnNextListener;
import com.example.rr.progress.ProgressSubscriber;

/**
 * Created by mzp on 2016/9/13.
 */
public class LoginPresenterImpel implements LoginPresenter
{
    private final static String TAG = "RetrofitRxxJava";

    private Context context;

    private LoginView loginView;

    private LoginApi loginApi;

    private SubscriberOnNextListener<UserEntity> onNextListener;

    public LoginPresenterImpel(Context context, final LoginView loginView)
    {
        this.context = context;
        this.loginView = loginView;

        loginApi = new LoginApi();

        onNextListener = new SubscriberOnNextListener<UserEntity>()
        {
            @Override
            public void onNext(UserEntity userEntity)
            {
                loginView.loginSuccess(userEntity.getData());
            }
        };
    }

    @Override
    public void loginPresenter()
    {
        loginApi.loginRequest(new ProgressSubscriber<UserEntity>(context, onNextListener), loginView.getUserName(), loginView.getPassword());
    }
}
