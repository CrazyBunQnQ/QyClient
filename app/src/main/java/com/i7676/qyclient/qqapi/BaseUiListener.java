package com.i7676.qyclient.qqapi;

import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class BaseUiListener implements IUiListener {

  private static final String TAG = BaseUiListener.class.getName();

  protected void doComplete(JSONObject values) {
    Log.i(TAG, "doComplete: >>>" + values.toString());
  }

  @Override public void onComplete(Object response) {
    //mBaseMessageText.setText("onComplete:");
    //mMessageText.setText(response.toString());
    doComplete((JSONObject) response);
  }

  @Override public void onError(UiError e) {
    showResult("onError:",
        "code:" + e.errorCode + ", msg:" + e.errorMessage + ", detail:" + e.errorDetail);
  }

  @Override public void onCancel() {
    showResult("onCancel", "");
  }

  private void showResult(String... str) {
    Log.i(TAG, "showResult: {\"process\":\"" + str[0] + "\" \"ret\":\"" + str[1] + "\"");
  }
}