package com.example.rr.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.rr.Model.UserInfos;
import com.example.rr.http.ApiService;
import com.example.rr.View.LoginView;
import com.example.rr.http.ApiInterface;
import com.example.rr.http.RetrofitFactory;
import com.example.rr.listener.SubscriberListener;
import com.example.rr.http.HttpRequestSubscriber;
import com.google.gson.Gson;

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

    private SubscriberListener onNextListener;

    public LoginPresenter(final Context context, final LoginView loginView)
    {
        this.context = context;
        this.loginView = loginView;

        apiService = new ApiService();

        onNextListener = new SubscriberListener()
        {
            @Override
            public void onSuccess(String dataMsg)
            {
                loginView.loginSuccess(new Gson().fromJson(dataMsg, UserInfos.class));
            }

            @Override
            public void onError(String errorMsg)
            {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void loginPresenter()
    {
        ApiInterface apiInterface = RetrofitFactory.getInstance().create(ApiInterface.class);

        Observable observable = apiInterface.getTopMovie(loginView.getUserName(), loginView.getPassword());

        apiService.ApiRequest(new HttpRequestSubscriber(context, onNextListener), observable);
    }
}
