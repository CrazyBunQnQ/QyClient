package com.i7676.qyclient;

import android.app.Application;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.util.SharedPreferencesUtil;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import io.rong.imkit.RongIM;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2016/9/9.
 */

@Singleton public class QyClient extends Application {

    private QyClientComponent mQyClientComponent;

    public static UserEntity curUser;
    public static final String CURRENT_USER_SP_TAG = "CURRENT_USER_SP_TAG";

    @Override public void onCreate() {
        super.onCreate();
        initDaggerComponent();
        init3rdPartyLibs(BuildConfig.DEBUG);

        // 载入用户
        curUser = (UserEntity) SharedPreferencesUtil.getInstance(this)
            .restoreSerializable(CURRENT_USER_SP_TAG);

        // 融云
        RongIM.init(this);
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
