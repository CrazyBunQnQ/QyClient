package com.i7676.qyclient.functions.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseActivity;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import com.i7676.qyclient.functions.login.rof.RoFFragment;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
@Layout(R.layout.activity_login) public class LoginActivity
    extends BaseActivity<LoginAtyPresenter, LoginAtyView> implements LoginAtyView {

    @Inject LoginNavigator navigator;

    public static Intent buildIntent(Context from, Bundle args) {
        final Intent mIntent = new Intent(from, LoginActivity.class);
        if (args != null) mIntent.putExtras(args);
        return mIntent;
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    // Views
    private Toolbar mToolbar;

    // Dagger
    private LoginAtyComponent atyComponent;

    @Override public void initViews() {
        initInject();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
    }

    private void initInject() {
        atyComponent = DaggerLoginAtyComponent.builder()
            .loginAtyModule(new LoginAtyModule(this))
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();

        atyComponent.inject(this);
    }

    @NonNull @Override public LoginAtyPresenter providePresenter() {
        return new LoginAtyPresenter();
    }

    public int getFrPlaceHolderResId() {
        return R.id.container;
    }

    @Override public void setTitle(String titleText) {
        mToolbar.setTitle(titleText);
    }

    @Override public void showSignInFr() {
        navigator.showSignInFr();
    }

    @Override public void showForgetPasswordFr() {
        Bundle args = new Bundle();
        args.putString(RoFFragment.RENDER_TYPE, RoFFragment.RENDER_TYPE_FORGET_PASSWORD);
        navigator.showRofFr(args);
    }

    @Override public void showRegisterFr() {
        Bundle args = new Bundle();
        args.putString(RoFFragment.RENDER_TYPE, RoFFragment.RENDER_TYPE_REGISTER);
        navigator.showRofFr(args);
    }

    @Override public void showQuickRegFr() {
        navigator.showQuickRegFr();
    }

    public LoginAtyComponent getAtyComponent() {
        return atyComponent;
    }

    @Override public void onBackPressed() {
        navigator.onBackPress();
    }
}
