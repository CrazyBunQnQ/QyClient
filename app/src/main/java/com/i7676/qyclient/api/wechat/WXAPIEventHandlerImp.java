package com.i7676.qyclient.api.wechat;

import android.content.Context;
import android.content.Intent;
import com.i7676.qyclient.BuildConfig;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.api.ApiConnection;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import rx.schedulers.Schedulers;

@PerActivity public class WXAPIEventHandlerImp implements IWXAPIEventHandler {

    private Context context;
    // WeiXin
    private static final String WX_APP_ID = "wx2534d167763b1f30";
    private static final String WX_APP_SECRET = "c6474471270be2bc0957699a65330275";
    private IWXAPI wxAPI;
    // API
    private WXAPIService apiService;
    // 用户信息回调
    private NetCallback<WXUserInfoResponse> mWXUserInfoCallback;

    public void setWXUserInfoCallback(NetCallback<WXUserInfoResponse> mWXUserInfoCallback) {
        this.mWXUserInfoCallback = mWXUserInfoCallback;
    }

    public WXAPIEventHandlerImp(Context context, WXAPIService apiService) {
        this.context = context;
        this.apiService = apiService;
        hackWXLogger();
        wxAPI = WXAPIFactory.createWXAPI(context, WX_APP_ID, true);
        wxAPI.registerApp(WX_APP_ID);
    }

    private void hackWXLogger() {

        if (!BuildConfig.DEBUG) return;

        Class<?> logger;
        try {
            logger = Class.forName("com.tencent.mm.sdk.b.b");
            Field declaredFields[] = logger.getDeclaredFields();
            Field levelField = null;
            for (Field field : declaredFields) {
                if (field.getName().equals("level")) {
                    levelField = field;
                    break;
                }
            }
            if (levelField != null) {
                levelField.setAccessible(true);
                levelField.setInt(logger, 0);
            }
        } catch (Exception e) {
            Logger.e("WXLogger hack failed.");
        }
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
            loggerResponse(newResp);

            this.apiService.getAccessToken(WX_APP_ID, WX_APP_SECRET, newResp.code)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<WXAccessTokenResponse>() {
                    @Override public void onStart() {
                        super.onStart();
                        Logger.i(">>> getAccessToken@onStart");
                    }

                    @Override public void onCompleted() {
                        super.onCompleted();
                        Logger.i(">>> getAccessToken@onCompleted");
                    }

                    @Override public void onNext(WXAccessTokenResponse wxAccessTokenResponse) {
                        super.onNext(wxAccessTokenResponse);
                        Logger.i(">>> getAccessToken@onNext");
                        if (wxAccessTokenResponse.errcode != 0) return;
                        // TODO callback 回来，请求用户信息了改,请求完用户信息，就回到 Activity 了
                        apiService.getUserInfo(wxAccessTokenResponse.access_token,
                            wxAccessTokenResponse.openid).subscribe(wxUserInfoResponse -> {
                            mWXUserInfoCallback.onResponse(wxUserInfoResponse);
                        });
                    }

                    @Override public void onError(Throwable e) {
                        super.onError(e);
                        Logger.i(">>> getAccessToken@onError: " + e.getMessage());
                    }
                });
        }
    }

    private void loggerResponse(SendAuth.Resp newResp) {
        if (newResp == null) {
            Logger.e(">>> wx response is null.");
            return;
        }

        Logger.i(">>> wx response is:" +
            "{ code: " + newResp.code +
            " country: " + newResp.country +
            " lang: " + newResp.lang +
            " url: " + newResp.url +
            " state: " + newResp.state +
            " getType: " + newResp.getType() +
            " checkArgs: " + newResp.checkArgs() + "}");
    }

    public interface NetCallback<T> {
        void onResponse(T t);

        void onFailure(Throwable e);
    }

    private String syncNetwork(String url) throws MalformedURLException {
        return ApiConnection.createGET(url).requestSyncCall();
    }
}
