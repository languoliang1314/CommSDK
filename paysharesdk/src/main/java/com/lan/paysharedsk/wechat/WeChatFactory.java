package com.lan.paysharedsk.wechat;

import android.app.Activity;
import android.content.Context;

import com.lan.paysharedsk.Config;
import com.lan.paysharedsk.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信相关操作
 */
public class WeChatFactory {
    public static final String APP_ID = Config.APP_ID;
    private final IWXAPI WXAPI;
    private Context mContext;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final WeChatFactory INSTANCE = new WeChatFactory();
    }

    public static WeChatFactory getInstance() {
        return Holder.INSTANCE;
    }


    private WeChatFactory() {
        final Activity context = Config.getInstance().getContext();
        WXAPI = WXAPIFactory.createWXAPI(context, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }
    //微信支付
    public void weChatPay(String orderInfo) {
        try {
            final JSONObject result = new JSONObject(orderInfo);
            final String prepayId = result.getString("prepayid");
            final String partnerId = result.getString("partnerid");
            final String packageValue = result.getString("package");
            final String timestamp = result.getString("timestamp");
            final String nonceStr = result.getString("noncestr");
            final String paySign = result.getString("sign");

            final PayReq payReq = new PayReq();
            payReq.appId = Config.APP_ID;
            payReq.prepayId = prepayId;
            payReq.partnerId = partnerId;
            payReq.packageValue = packageValue;
            payReq.timeStamp = timestamp;
            payReq.nonceStr = nonceStr;
            payReq.sign = paySign;
            WXAPI.sendReq(payReq);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public WeChatFactory onSignSuccess(IWeChatSignInCallback signInCallback){
        this.mSignInCallback=signInCallback;
        return this;
    }
    //微信登录
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
