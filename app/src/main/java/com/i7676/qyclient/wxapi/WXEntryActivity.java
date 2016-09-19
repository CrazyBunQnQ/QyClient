package com.i7676.qyclient.wxapi;

import android.app.Activity;
import android.os.Bundle;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;

public class WXEntryActivity extends Activity {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    WXAPIEventHandlerImp.getInstance().handleIntent(getIntent());
    finish();
  }
}