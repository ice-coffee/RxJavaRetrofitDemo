package com.example.rr.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


import com.example.rr.listener.SubscriberOnNextListener;

import rx.Subscriber;

/**
 * Created by mzp on 2016/9/11.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressDialog.OnCancelListener
{
    private Context context;

    private ProgressDialogHandler pdh;

    private SubscriberOnNextListener onNextListener;

    public ProgressSubscriber(Context context, SubscriberOnNextListener onNextListener)
    {
        this.context = context;
        this.onNextListener = onNextListener;

        pdh = ProgressDialogHandler.getInstance(context, this);
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
    public void onNext(T t)
    {
        if (null != onNextListener)
        {
            onNextListener.onNext(t);
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
