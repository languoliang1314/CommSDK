package com.lan.paysharedsk.pay;

import android.app.Activity;

import com.lan.paysharedsk.callbacks.IAlPayResultListener;
import com.lan.paysharedsk.wechat.WeChatFactory;

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

    public void aliPay(String orderInfo) {
    }

    public void weChatPay(String orderInfo){
        WeChatFactory.getInstance().weChatPay(orderInfo);

    }


}
