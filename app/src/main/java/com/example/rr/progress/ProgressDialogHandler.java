package com.example.rr.progress;

import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 */
public class ProgressDialogHandler extends ProgressDialog
{
    private static ProgressDialogHandler pdh;

    public ProgressDialogHandler(Context context)
    {
        super(context);
    }

    public synchronized static ProgressDialogHandler getInstance(Context context, OnCancelListener onCancelListener)
    {
        if (null == pdh)
        {
            pdh = new ProgressDialogHandler(context);
            pdh.setCancelable(true);
            pdh.setOnCancelListener(onCancelListener);
        }

        return pdh;
    }

    @Override
    public void dismiss()
    {
        super.dismiss();
        pdh = null;
    }
}
