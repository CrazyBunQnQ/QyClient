package com.i7676.qyclient.api.wechat;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXAPIEventHandlerImp implements IWXAPIEventHandler {

  private Context context;
  // WeiXin
  private static final String WX_APP_ID = "wx2534d167763b1f30";
  private static final String WX_APP_SECRET = "";
  private IWXAPI wxAPI;

  private static final WXAPIEventHandlerImp INSTANCE = new WXAPIEventHandlerImp();

  public static WXAPIEventHandlerImp getInstance() {
    return INSTANCE;
  }

  public WXAPIEventHandlerImp() {
  }

  public void initWXAPI(Context context) {
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

  }

  @Override public void onResp(BaseResp resp) {
    if (resp instanceof SendAuth.Resp) {
      SendAuth.Resp newResp = (SendAuth.Resp) resp;
      //获取微信传回的code
      //String code = newResp.code;
      Toast.makeText(context, newResp.toString(), Toast.LENGTH_SHORT).show();
    }
  }
}
