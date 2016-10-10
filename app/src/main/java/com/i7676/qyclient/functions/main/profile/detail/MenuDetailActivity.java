package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseActivity;

/**
 * Created by Administrator on 2016/10/8.
 */

@Layout(R.layout.activity_menu_detail) public class MenuDetailActivity
    extends BaseActivity<MenuDetailAtyPresenter, MenuDetailAtyView> implements MenuDetailAtyView {

    public static final String SHOW_TAG = "SHOW_TAG";

    public static Intent buildIntent(Context from, Bundle args) {
        final Intent mIntent = new Intent(from, MenuDetailActivity.class);
        mIntent.putExtras(args);
        return mIntent;
    }

    private FrameLayout mContainer;
    private Toolbar mToolbar;
    private MenuDetailComponent atyComponent;

    public MenuDetailComponent getAtyComponent() {
        return atyComponent;
    }

    @Override public void initViews() {
        setupInject();

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(clickAsPressBack);

        mContainer = (FrameLayout) findViewById(R.id.container);
    }

    public int getContainerResId() {
        return R.id.container;
    }

    private void setupInject() {
        atyComponent = DaggerMenuDetailComponent.builder()
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();
    }

    @NonNull @Override public MenuDetailAtyPresenter providePresenter() {
        return new MenuDetailAtyPresenter(getIntent().getExtras(),
            new MenuDetailAtyNavigator(this));
    }

    @Override public void msg2User(String msg) {
        showToast2User(msg, Toast.LENGTH_SHORT);
    }
}
