package com.i7676.qyclient;

import android.app.Application;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2016/9/9.
 */

@Singleton public class QyClient extends Application {

    private QyClientComponent mQyClientComponent;

    public static String mToken;

    @Override public void onCreate() {
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
        mQyClientComponent =
            DaggerQyClientComponent.builder().qyClientModule(new QyClientModule(this)).build();
    }

    public QyClientComponent getClientComponent() {
        return mQyClientComponent;
    }
}
