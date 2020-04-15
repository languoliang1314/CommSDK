package com.lan.paysharedsk.wechat;

import android.os.AsyncTask;

import com.lan.paysharedsk.Config;
import com.lan.paysharedsk.utils.HttpUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseWXSignInEntryActivity extends WXEntryTemplateActivity{
    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);


    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {
    }
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(Config.APP_ID)
                .append("&secret=")
                .append(Config.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        final GetTokenTask getTokenTask = new GetTokenTask();
        getTokenTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, authUrl.toString());
    }

    private class GetTokenTask  extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result=HttpUtil.doGet(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null){
                try {
                    JSONObject authObj = new JSONObject(result);
                    final String accessToken = authObj.getString("access_token");
                    final String openId = authObj.getString("openid");

                    final StringBuilder userInfoUrl = new StringBuilder();
                    userInfoUrl
                            .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                            .append(accessToken)
                            .append("&openid=")
                            .append(openId)
                            .append("&lang=")
                            .append("zh_CN");
                    final GetUserTask getUserTask = new GetUserTask();
                    getUserTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInfoUrl.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class GetUserTask  extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result=HttpUtil.doGet(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null){
                onSignInSuccess(result);
            }
        }
    }
}
