package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseActivity;

/**
 * Created by Administrator on 2016/10/8.
 */

@Layout(R.layout.activity_menu_detail) public class MenuDetailActivity
    extends BaseActivity<MenuDetailAtyPresenter, MenuMetailAtyView> implements MenuMetailAtyView {

    public static final String SHOW_TAG = "SHOW_TAG";
    // 账号设置
    public static final int TAG_ACCOUNT = 1;
    // 我的好友
    public static final int TAG_FRIENDS = 2;
    // 充值中心
    public static final int TAG_RECHARGE = 3;
    // 绑定手机
    public static final int TAG_TEL_BIND = 4;

    public static Intent buildIntent(Context from, Bundle args) {
        final Intent mIntent = new Intent(from, MenuDetailActivity.class);
        mIntent.putExtras(args);
        return mIntent;
    }

    private FrameLayout mContainer;
    private Toolbar mToolbar;
    private MenuDetailAtyNavigator navigator;

    @Override public void initViews() {
        navigator = new MenuDetailAtyNavigator(this);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        mContainer = (FrameLayout) findViewById(R.id.container);
    }

    public int getContainerResId() {
        return R.id.container;
    }

    @NonNull @Override public MenuDetailAtyPresenter providePresenter() {
        return new MenuDetailAtyPresenter(navigator);
    }
}
