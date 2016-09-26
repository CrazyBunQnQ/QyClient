package com.i7676.qyclient.wxapi;

import android.app.Activity;
import android.os.Bundle;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class WXEntryActivity extends Activity {

    @Inject WXAPIEventHandlerImp wxapiEventHandlerImp;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();

        wxapiEventHandlerImp.handleIntent(getIntent());
        finish();
    }

    private void initInject() {
        ((QyClient) getApplication()).getClientComponent().inject(this);
    }
}