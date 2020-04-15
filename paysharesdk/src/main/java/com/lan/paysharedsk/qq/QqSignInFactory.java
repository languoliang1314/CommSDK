package com.lan.paysharedsk.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lan.paysharedsk.Config;
import com.lan.paysharedsk.callbacks.IQqSignInCallback;
import com.lan.paysharedsk.wechat.WeChatFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class QqSignInFactory {
    private Tencent mTencent;
    private IQqSignInCallback signInCallback;
    private Activity mActivity;
    private QQLoginListener qqLoginListener;

    private static final class Holder {
        private static final QqSignInFactory INSTANCE = new QqSignInFactory();
    }

    public static QqSignInFactory getInstance() {
        return QqSignInFactory.Holder.INSTANCE;
    }

    public QqSignInFactory setSignInSuccess(IQqSignInCallback callback){
        signInCallback=callback;
        return this;
    }


    public void signIn(Activity activity){
        this.mActivity=activity;
        mTencent = Tencent.createInstance(Config.QQ_APP_ID, mActivity);
        qqLoginListener=new QQLoginListener();
        //如果session不可用，则登录，否则说明已经登录
        if (!mTencent.isSessionValid()) {
            mTencent.login(mActivity, "all", qqLoginListener);
        }
    }

    public QQLoginListener getQqLoginListener(){
        return qqLoginListener;
    }

    // 实现登录成功与否的接口
    private class QQLoginListener implements IUiListener {
        @Override
        public void onComplete(Object object) { //登录成功
            //获取openid和token
            initOpenIdAndToken(object);
            Toast.makeText(mActivity,"登录成功",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(UiError uiError) {  //登录失败
            Toast.makeText(mActivity,"登录失败",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {                //取消登录
        }
    }

    private void initOpenIdAndToken(Object object) {
        JSONObject jb = (JSONObject) object;
        try {
            String openID = jb.getString("openid");  //openid用户唯一标识
            Toast.makeText(mActivity,"登录成功"+openID,Toast.LENGTH_SHORT).show();
            signInCallback.onSuccess(openID);
//            String access_token = jb.getString("access_token");
//            String expires = jb.getString("expires_in");
//            mTencent.setOpenId(openID);
//            mTencent.setAccessToken(access_token, expires);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
