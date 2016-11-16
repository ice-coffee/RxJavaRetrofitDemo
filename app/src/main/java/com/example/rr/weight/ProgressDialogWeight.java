package com.example.rr.weight;

import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 */
public class ProgressDialogWeight extends ProgressDialog
{
    private static ProgressDialogWeight pdh;

    public ProgressDialogWeight(Context context)
    {
        super(context);
    }

    public synchronized static ProgressDialogWeight getInstance(Context context, OnCancelListener onCancelListener)
    {
        if (null == pdh)
        {
            pdh = new ProgressDialogWeight(context);
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
