package com.i7676.qyclient.functions.login;

import android.content.Context;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import com.i7676.qyclient.api.wechat.WXAPIService;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import com.i7676.qyclient.net.RetrofitFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/9/20.
 */
@Module /*package*/ class LoginAtyModule {

    private Context loginAtyContext;

    /*package*/ LoginAtyModule(Context loginAtyContext) {
        this.loginAtyContext = loginAtyContext;
    }

    @Provides @PerActivity LoginActivity providedLoginAtySelf() {
        return (LoginActivity) loginAtyContext;
    }

    @Provides @PerActivity Context providedLoginAtyContext() {
        return loginAtyContext;
    }

    @Provides @PerActivity LoginNavigator providedLoginNavigator(LoginActivity loginActivity) {
        return LoginNavigator.create(loginActivity);
    }

    @Provides @PerActivity WXAPIEventHandlerImp providedWXAPIInstance(LoginActivity loginActivity) {
        return new WXAPIEventHandlerImp(loginActivity,
            RetrofitFactory.createService("https://api.weixin.qq.com", WXAPIService.class,
                loginActivity.getCacheDir()));
    }
}
