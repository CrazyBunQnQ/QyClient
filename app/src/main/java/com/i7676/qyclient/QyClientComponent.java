package com.i7676.qyclient;


import android.app.Application;

import com.i7676.qyclient.function.main.presenter.GamePresenter;
import com.i7676.qyclient.net.EgretApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HCol on 2016/9/13.
 */
@Singleton
@Component(modules = QyClientModule.class)
public interface QyClientComponent {

    void inject(GamePresenter gameFragment);

    Application getApplication();

    EgretApiService getEgretApiService();

}
