package com.lan.paysharedsk;

import android.app.Activity;
import android.content.Context;

import com.lan.paysharedsk.pay.FastPay;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class Config {
    //微信APPID
    public static String APP_ID;

    //微信Secret
    public static String APP_SECRET;

    //QQ  APPID
    public static String QQ_APP_ID;


    private Activity mContext;
    /**
     * 单例
     */
    private static class Holder{
        private static Config mInstance=new Config();
    }
    public static Config getInstance(){
        return Holder.mInstance;
    }

    public Config init(Activity context){
        mContext=context;
        return this;
    }

    public Config withWeChatAppID(String appId){
        APP_ID=appId;
        return this;
    }

    public Config withWeChatAppSecret (String appSecret){
        APP_SECRET=appSecret;
        return this;
    }

    public Config withQqApId (String appId){
        QQ_APP_ID=appId;
        return this;
    }

    public Activity getContext(){
        return mContext;
    }
}
