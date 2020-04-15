package com.lan.paysharedsk.ali;

import android.app.Activity;
import android.os.AsyncTask;

import com.lan.paysharedsk.Config;
import com.lan.paysharedsk.callbacks.IAlPayResultListener;

public class AliPayFactory {
    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;
    private static final class Holder {
        private static final AliPayFactory INSTANCE = new AliPayFactory();
    }

    public static AliPayFactory getInstance() {
        return Holder.INSTANCE;
    }
    private AliPayFactory() {
    }

    public void AliPay(String orderInfo,IAlPayResultListener listener){
        final Activity context= Config.getInstance().getContext();
        final AlPayAsyncTask payAsyncTask = new AlPayAsyncTask(context, listener);
        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, orderInfo);
    }
}
