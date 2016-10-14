package com.i7676.qyclient.functions.login.navigation;

import android.os.Bundle;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.functions.login.rof.QuickRegFragment;
import com.i7676.qyclient.functions.login.rof.RoFFragment;
import com.i7676.qyclient.functions.login.sign.SignInFragment;

/**
 * Created by Administrator on 2016/9/20.
 */

public class LoginNavigator {

    public static LoginNavigator create(LoginActivity loginActivity) {
        return new LoginNavigator(loginActivity);
    }

    private LoginActivity loginActivity;
    private BaseFragment selectedFragment;

    private LoginNavigator(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public void showSignInFr() {
        transform((selectedFragment = SignInFragment.create(null)));
    }

    public void showRofFr(Bundle args) {
        transform((selectedFragment = RoFFragment.create(args)));
    }

    public void showQuickRegFr() {
        transform((selectedFragment = QuickRegFragment.create(null)));
    }

    private void transform(BaseFragment fragment) {
        loginActivity.getSupportFragmentManager().beginTransaction()
            // 添加Fragment
            .add(loginActivity.getFrPlaceHolderResId(), fragment,
                fragment.getClass().getSimpleName())
            // 设置进回退栈里
            .addToBackStack(fragment.getClass().getSimpleName())
            // 提交
            .commit();
    }

    public void onBackPress() {
        if (selectedFragment instanceof QuickRegFragment
            || selectedFragment instanceof RoFFragment) {
            loginActivity.getSupportFragmentManager().popBackStackImmediate();
            loginActivity.getPresenter().getView().setTitleText("登录");
            // FIXME: 2016/9/23  只有两层还好管理，要是有三层就。。。
            selectedFragment = (BaseFragment) loginActivity.getSupportFragmentManager()
                .findFragmentByTag(SignInFragment.class.getSimpleName());
        } else {
            loginActivity.finish();
        }
    }
}
