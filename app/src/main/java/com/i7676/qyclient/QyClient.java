package com.i7676.qyclient;

import android.app.Application;
import android.content.Context;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * Created by Administrator on 2016/9/9.
 */
public class QyClient extends Application {

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    // TODO 做一些初始化工作，别做耗时的创建工作
  }

  @Override public void onCreate() {
    super.onCreate();

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
}
