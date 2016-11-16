package com.example.rr.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


import com.example.rr.Model.BaseEntity;
import com.example.rr.listener.SubscriberListener;
import com.example.rr.weight.ProgressDialogWeight;
import com.google.gson.Gson;

import rx.Subscriber;

/**
 * Created by mzp on 2016/9/11.
 */
public class HttpRequestSubscriber extends Subscriber<BaseEntity> implements ProgressDialog.OnCancelListener
{
    private Context context;

    private ProgressDialogWeight pdh;

    private SubscriberListener onNextListener;

    public HttpRequestSubscriber(Context context, SubscriberListener onNextListener)
    {
        this.context = context;
        this.onNextListener = onNextListener;

        pdh = ProgressDialogWeight.getInstance(context, this);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        pdh.show();
    }

    @Override
    public void onCompleted()
    {
        pdh.dismiss();
    }

    @Override
    public void onError(Throwable e)
    {
        pdh.dismiss();
        Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(BaseEntity baseEntity)
    {
        //对请求是否得到正确相应做出判断
        if (null != onNextListener)
        {
            if (HttpContents.SUCCESS.equals(baseEntity.getCode()))
            {
                Gson gson = new Gson();
                String infoStr = gson.toJson(baseEntity.getData());
                onNextListener.onSuccess(infoStr);
            }
            else
            {
                onNextListener.onSuccess(baseEntity.getMsg());
            }
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancel(DialogInterface dialog)
    {
        if (this.isUnsubscribed())
        {
            this.unsubscribe();
        }
    }
}
