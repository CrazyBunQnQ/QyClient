package com.i7676.qyclient.functions;

import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.i7676.qyclient.R;

/**
 * Created by Administrator on 2016/10/14.
 *
 * Toolbar Activity 的抽象基类
 */
public abstract class OneHasToolbarActivity<P extends BasePresenter<V>, V extends BaseView>
    extends BaseActivity<P, V> implements OneHasToolbarAtyView {

    private android.support.v7.widget.Toolbar mToolbar;
    private View.OnClickListener mClickAsPressBack = v -> {
        onBackPressed();
    };

    @Override public void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        // 默认显示返回键功能
        setupToolbarIconAndEvent(R.drawable.ic_arrow_back_white_24dp, mClickAsPressBack);
        // 主题样式
        setupToolbarPrimaryColor();
        setTitleTextColor(Color.WHITE);
    }

    @Override public void hideToolbar() {
        this.mToolbar.setVisibility(View.GONE);
    }

    @Override public void showToolbar() {
        this.mToolbar.setVisibility(View.VISIBLE);
    }

    @Override public void setupToolbarIconAndEvent(@DrawableRes int drawableRes,
        View.OnClickListener clickListener) {
        mToolbar.setNavigationIcon(drawableRes);
        mToolbar.setNavigationOnClickListener(clickListener);
    }

    @Override public void setTitleTextColor(int color) {
        mToolbar.setTitleTextColor(color);
    }

    @Override public void setupToolbarPrimaryColor() {
        mToolbar.setBackgroundResource(R.color.colorPrimary);
    }

    @Override public void setToolbarBackgroundColor(int color) {
        mToolbar.setBackgroundColor(color);
    }

    @Override public void setTitleText(String titleText) {
        mToolbar.setTitle(titleText);
    }

    protected @IdRes int getContainerResId() {
        return R.id.container;
    }
}
