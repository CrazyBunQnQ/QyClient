package com.i7676.qyclient.functions.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.OneHasToolbarActivity;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import com.i7676.qyclient.functions.login.rof.RoFFragment;
import com.i7676.qyclient.util.SharedPreferencesUtil;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
@Layout(R.layout.activity_toolbar) public class LoginActivity
    extends OneHasToolbarActivity<LoginAtyPresenter, LoginAtyView> implements LoginAtyView {

    @Inject LoginNavigator navigator;

    public static Intent buildIntent(Context from, Bundle args) {
        final Intent mIntent = new Intent(from, LoginActivity.class);
        if (args != null) mIntent.putExtras(args);
        return mIntent;
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    // Dagger
    private LoginAtyComponent atyComponent;

    @Override public void initViews() {
        super.initViews();
        initInject();
        setTitleTextColor(Color.WHITE);
    }

    private void initInject() {
        atyComponent = DaggerLoginAtyComponent.builder()
            .loginAtyModule(new LoginAtyModule(this))
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();

        atyComponent.inject(this);
        atyComponent.inject(getPresenter());
    }

    @NonNull @Override public LoginAtyPresenter providePresenter() {
        return new LoginAtyPresenter();
    }

    public int getFrPlaceHolderResId() {
        return getContainerResId();
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

    @Override public void showDialog2User(String msg) {
        this.showProgressDialog(msg);
    }

    @Override public void closeDialog() {
        this.closeProgressDialog();
    }

    @Override public void signUpSuccess() {
        this.closeDialog();
        this.finish();
    }

    @Override public void signUpFailed(String msg) {
        this.showToast2User(msg, Toast.LENGTH_SHORT);
    }

    @Override public void storeUser(UserEntity userEntity) {
        SharedPreferencesUtil.getInstance(this)
            .saveSerializable(QyClient.CURRENT_USER_SP_TAG, userEntity);
    }

    private boolean freezeTag = false;

    @Override public void freezeBackEvent() {
        freezeTag = !freezeTag;
    }

    public LoginAtyComponent getAtyComponent() {
        return atyComponent;
    }

    @Override public void onBackPressed() {
        navigator.onBackPress();
    }

    public void eatAnyClick(View v) {
        com.orhanobut.logger.Logger.i(">>> eat it. :D");
    }
}
