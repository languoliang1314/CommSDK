package com.lan.paysharedsk.pay;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

public class FastPay{
    private Activity mActivity;
    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;

    private FastPay(Activity activity) {
        this.mActivity = activity;
    }

    public static FastPay create(Activity activity) {
        return new FastPay(activity);
    }

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    public FastPay alPay(String orderInfo) {
        final AlPayAsyncTask payAsyncTask = new AlPayAsyncTask(mActivity, mIAlPayResultListener);
        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, orderInfo);
        return this;
    }

    public FastPay weChatPay() {
        return this;
    }
}
