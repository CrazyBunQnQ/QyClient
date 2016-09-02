package com.i7676.qyclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by Administrator on 2016/8/31.
 */
public class LoginActivity extends BaseActivity {

  private static final String TAG = LoginActivity.class.getName();

  // QQ
  private static final String QQ_API_ID = "1105660746";
  private Tencent tencentInstance;

  // WeiXin
  private static final String WX_APP_ID = "wx2534d167763b1f30";
  private static final String WX_APP_SECRET = "";
  private IWXAPI api;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    tencentInstance = Tencent.createInstance(QQ_API_ID, this.getApplicationContext());
    // 设置QQ登陆态,如果存在有效的登陆态的话，则无需输入账号密码进行登录验证，直接请求授权即可。
    //tencentInstance.setOpenId(getQQOpenId());
    //tencentInstance.setAccessToken(getQQAccessToken(),);
    // 否则，进入正常的登录流程，然后再授权。
  }

  public void wxLogin(View view) {
    //api注册
    api = WXAPIFactory.createWXAPI(this, WX_APP_ID, true);
    api.registerApp(WX_APP_ID);

    SendAuth.Req req = new SendAuth.Req();
    //授权读取用户信息
    req.scope = "snsapi_userinfo";
    //自定义信息
    req.state = "wechat_sdk_demo_test";
    //向微信发送请求
    api.sendReq(req);
  }

  public void zfbLogin(View view) {
    //Class clz = null;
    //try {
    //  clz = Class.forName("com.tencent.mm.plugin.base.stub.WXPayEntryActivity");
    //} catch (Exception e) {
    //  return;
    //}
    String orderURL =
        "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx426b3015555a46be&redirect_uri=http%3A%2F%2Fpaysdk.weixin.qq.com%2Fexample%2Fjsapi.php&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
    Intent mIntent = new Intent();
    //mIntent.setDataAndType(Uri.parse(orderURL), Intent.ACTION_VIEW);
    startActivity(mIntent);
  }

  public void qqLogin(View view) {
    if (tencentInstance == null) {
      Log.e(TAG, "qqLogin: >>> QQ SDK init failed.");
    }
    // context,scope,callback
    tencentInstance.login(this, "all", new IUiListener() {
      @Override public void onComplete(Object o) {
        Log.i(TAG, "onComplete");
      }

      @Override public void onError(UiError uiError) {
        Log.i(TAG, "onError");
      }

      @Override public void onCancel() {
        Log.i(TAG, "onCancel");
      }
    });
  }

  private String getQQOpenId() {
    return null;
  }

  private String getQQAccessToken() {
    return null;
  }
}
