package com.lan.paysharedsk;

import android.app.Activity;
import android.content.Context;

import com.lan.paysharedsk.pay.FastPay;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class Config {
    //微信APPID
    public static String APP_ID;

    private static IWXAPI wxapi;

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

    public Config registeWeChat(Context context){
        wxapi = WXAPIFactory.createWXAPI(context, null);
        wxapi.registerApp(Config.APP_ID);
        return this;
    }

    public  IWXAPI getWxApi(){
        return wxapi;
    }

    public Activity getContext(){
        return mContext;
    }
}
