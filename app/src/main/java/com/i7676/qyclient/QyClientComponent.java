package com.i7676.qyclient;

import android.app.Application;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import com.i7676.qyclient.functions.login.sign.SignInFrPresenter;
import com.i7676.qyclient.api.EgretApiService;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.wxapi.WXEntryActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by HCol on 2016/9/13.
 */
@Singleton @Component(modules = QyClientModule.class) public interface QyClientComponent {

    // for WXAPI
    void inject(SignInFrPresenter presenter);

    void inject(WXEntryActivity wxEntryActivity);

    Application getApplication();

    EgretApiService getEgretApiService();

    YNetApiService getYNetApiService();

    WXAPIEventHandlerImp getWXAPIInstance();
}
