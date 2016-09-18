package com.i7676.qyclient.di;

import android.app.Application;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.net.EgretApiService;
import com.i7676.qyclient.net.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HCol on 2016/9/13.
 */
@Module
public class QyClientModule {

    private QyClient mApplication;

    public QyClientModule(QyClient mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    Application providedApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    EgretApiService providedEgretApiService() {
        return RetrofitFactory.createService(EgretApiService.BASE_URL, EgretApiService.class, mApplication.getCacheDir());
    }

}
