package com.example.rr.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.rr.progress.ProgressSubscriber;
import com.example.rr.R;
import com.example.rr.listener.SubscriberOnNextListener;
import com.example.rr.entity.BaseEntity;
import com.example.rr.entity.UserInfos;
import com.example.rr.http.HttpMethod;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
