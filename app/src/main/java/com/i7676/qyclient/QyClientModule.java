package com.i7676.qyclient;

import android.app.Application;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import com.i7676.qyclient.api.wechat.WXAPIService;
import com.i7676.qyclient.api.EgretApiService;
import com.i7676.qyclient.api.RetrofitFactory;
import com.i7676.qyclient.api.YNetApiService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by HCol on 2016/9/13.
 */
@Module public class QyClientModule {

    private QyClient mApplication;

    public QyClientModule(QyClient mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton @Provides Application providedApplication() {
        return mApplication;
    }

    @Singleton @Provides EgretApiService providedEgretApiService() {
        return RetrofitFactory.createService(EgretApiService.BASE_URL, EgretApiService.class,
            mApplication.getCacheDir());
    }

    @Singleton @Provides YNetApiService providedYNetApiService() {
        return RetrofitFactory.createService(YNetApiService.BASE_URL, YNetApiService.class,
            mApplication.getCacheDir());
    }

    @Singleton @Provides WXAPIEventHandlerImp providedWXAPIInstance() {
        return new WXAPIEventHandlerImp(mApplication,
            RetrofitFactory.createService("https://api.weixin.qq.com", WXAPIService.class,
                mApplication.getCacheDir()));
    }
}
