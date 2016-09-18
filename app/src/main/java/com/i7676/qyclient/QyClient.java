package com.i7676.qyclient;

import android.app.Application;
import com.i7676.qyclient.di.DaggerQyClientComponent;
import com.i7676.qyclient.di.QyClientComponent;
import com.i7676.qyclient.di.QyClientModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2016/9/9.
 */

@Singleton
public class QyClient extends Application {

    private QyClientComponent mQyClientComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDaggerComponent();
        init3rdPartyLibs(BuildConfig.DEBUG);
    }

    private void init3rdPartyLibs(boolean isDebug) {
        Settings logSettings = Logger.init(QyClient.class.getSimpleName());
        if (isDebug) {
            logSettings.logLevel(LogLevel.FULL);
        } else {
            logSettings.logLevel(LogLevel.NONE);
        }
    }

    private void initDaggerComponent() {
        mQyClientComponent = DaggerQyClientComponent.builder()
                .qyClientModule(new QyClientModule(this))
                .build();
    }

    public QyClientComponent getComponent() {
        return mQyClientComponent;
    }
}
