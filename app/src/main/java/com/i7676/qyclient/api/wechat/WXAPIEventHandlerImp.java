package com.i7676.qyclient.api.wechat;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.i7676.qyclient.annotations.PerActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerActivity public class WXAPIEventHandlerImp implements IWXAPIEventHandler {

    private Context context;
    // WeiXin
    private static final String WX_APP_ID = "wx2534d167763b1f30";
    private static final String WX_APP_SECRET = "";
    private IWXAPI wxAPI;
    // API
    private WXAPIService apiService;
    // 用户信息回调
    private Callback<WXUserInfoResponse> mWXUserInfoCallback;
    // 请求 AccessToken 回调
    private Callback<WXAccessTokenResponse> mAccessTokenCallback =
        new Callback<WXAccessTokenResponse>() {
            @Override public void onResponse(Call<WXAccessTokenResponse> call,
                Response<WXAccessTokenResponse> response) {
                if (response.body().errcode != 0) return;
                // TODO callback 回来，请求用户信息了改,请求完用户信息，就回到 Activity 了
                apiService.getUserInfo(response.body().accessToken, response.body().openid,
                    mWXUserInfoCallback);
            }

            @Override public void onFailure(Call<WXAccessTokenResponse> call, Throwable t) {
                // TODO 异常处理
            }
        };

    public void setWXUserInfoCallback(Callback<WXUserInfoResponse> mWXUserInfoCallback) {
        this.mWXUserInfoCallback = mWXUserInfoCallback;
    }

    public WXAPIEventHandlerImp(Context context, WXAPIService apiService) {
        this.apiService = apiService;
        this.context = context;
        wxAPI = WXAPIFactory.createWXAPI(context, WX_APP_ID, true);
        wxAPI.registerApp(WX_APP_ID);
    }

    public void handleIntent(Intent intent) {
        wxAPI.handleIntent(intent, this);
    }

    public void loginAndRegister() {
        sendEvent("REG");
    }

    public void loginAndBind() {
        sendEvent("BD");
    }

    private void sendEvent(String str) {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_message,snsapi_userinfo,snsapi_friend,snsapi_contact";
        req.state = str;
        this.wxAPI.sendReq(req);
    }

    @Override public void onReq(BaseReq baseReq) {
        Logger.i(">>> baseReq: " + baseReq);
    }

    @Override public void onResp(BaseResp resp) {
        if (resp instanceof SendAuth.Resp) {
            SendAuth.Resp newResp = (SendAuth.Resp) resp;
            //获取微信传回的code
            //String code = newResp.code;
            Toast.makeText(context, newResp.toString(), Toast.LENGTH_SHORT).show();
            // 请求 accessToken
            this.apiService.getAccessToken(WX_APP_ID, WX_APP_SECRET, newResp.code,
                mAccessTokenCallback);
        }
    }
}
