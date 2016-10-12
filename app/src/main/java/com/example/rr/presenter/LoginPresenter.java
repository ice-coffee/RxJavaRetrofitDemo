package com.example.rr.presenter;

import android.content.Context;

import com.example.rr.http.ApiService;
import com.example.rr.View.LoginView;
import com.example.rr.Model.UserEntity;
import com.example.rr.http.ApiInterface;
import com.example.rr.http.RetrofitFactory;
import com.example.rr.listener.SubscriberOnNextListener;
import com.example.rr.progress.ProgressSubscriber;

import rx.Observable;

/**
 * Created by mzp on 2016/9/13.
 */
public class LoginPresenter
{
    private final static String TAG = "RetrofitRxxJava";

    private Context context;

    private LoginView loginView;

    private ApiService apiService;

    private SubscriberOnNextListener<UserEntity> onNextListener;

    public LoginPresenter(Context context, final LoginView loginView)
    {
        this.context = context;
        this.loginView = loginView;

        apiService = new ApiService();

        onNextListener = new SubscriberOnNextListener<UserEntity>()
        {
            @Override
            public void onNext(UserEntity userEntity)
            {
                loginView.loginSuccess(userEntity.getData());
            }
        };
    }

    public void loginPresenter()
    {
        ApiInterface apiInterface = RetrofitFactory.getInstance().create(ApiInterface.class);

        Observable observable = apiInterface.getTopMovie(loginView.getUserName(), loginView.getPassword());

        apiService.ApiRequest(new ProgressSubscriber<UserEntity>(context, onNextListener), observable);
    }
}
