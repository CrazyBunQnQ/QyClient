package com.i7676.qyclient.functions.login;

import android.content.Context;
import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import com.i7676.qyclient.functions.login.sign.SignInFrPresenter;
import dagger.Component;

/**
 * Created by Administrator on 2016/9/20.
 */

@PerActivity @Component(modules = LoginAtyModule.class, dependencies = QyClientComponent.class)
public interface LoginAtyComponent {

    //void inject(LoginActivity activity);

    void inject(LoginAtyPresenter presenter);

    void inject(SignInFrPresenter presenter);

    Context getLoginAtyContext();

    LoginActivity getLoginAtySelf();

    LoginNavigator getLoginNavigator();

    WXAPIEventHandlerImp getWXAPIInstance();
}
