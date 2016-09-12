package com.example.rr;

import android.app.Activity;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.rr.entity.BaseEntity;
import com.example.rr.entity.UserInfos;
import com.example.rr.http.HttpMethod;
import com.example.rr.http.MovieService;
import com.google.gson.Gson;

import java.util.List;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mzp on 2016/9/6.
 */
public class MainActivity extends Activity
{

    private final static String TAG = "RetrofitRxxJava";

    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.click_me_BN)
    public void onClick()
    {
        getMovie();
    }

    private void getMovie()
    {
        SubscriberOnNextListener<BaseEntity> onNextListener = new SubscriberOnNextListener<BaseEntity>()
        {
            @Override
            public void onNext(BaseEntity userInfos)
            {
                String json = new Gson().toJson(userInfos);
                Log.e(TAG, "onNext: " + json);
            }
        };

        HttpMethod.getInstance().getMovie(new ProgressSubscriber<UserInfos>(this, onNextListener));
    }
}
