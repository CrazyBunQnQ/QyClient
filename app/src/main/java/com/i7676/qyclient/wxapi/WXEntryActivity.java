package com.i7676.qyclient.wxapi;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

  private static final String APP_ID = "wx2534d167763b1f30";
  private IWXAPI api;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //注册API
    api = WXAPIFactory.createWXAPI(this, APP_ID, true);
    api.handleIntent(getIntent(), this);
  }

  @Override public void onReq(BaseReq baseReq) {

  }

  @Override public void onResp(BaseResp resp) {
    if (resp instanceof SendAuth.Resp) {
      SendAuth.Resp newResp = (SendAuth.Resp) resp;
      //获取微信传回的code
      String code = newResp.code;
    }
  }
}