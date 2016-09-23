package com.i7676.qyclient.functions.login;

import android.os.Bundle;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import com.i7676.qyclient.functions.login.rof.RoFFragment;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/20.
 */

public class LoginAtyPresenter extends BasePresenter<LoginAtyView> {

    @Inject LoginNavigator navigator;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        navigator.showSignInFr();
    }

    void onBackPress() {
        navigator.onBackPress();
    }

    public void showForgetPasswordFr() {
        Bundle args = new Bundle();
        args.putString(RoFFragment.RENDER_TYPE, RoFFragment.RENDER_TYPE_FORGET_PASSWORD);
        navigator.renderRoFFr(args);
    }

    public void showRegisterFr() {
        Bundle args = new Bundle();
        args.putString(RoFFragment.RENDER_TYPE, RoFFragment.RENDER_TYP_REGISTER);
        navigator.renderRoFFr(args);
    }
}
