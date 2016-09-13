package com.i7676.qyclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i7676.qyclient.function.presenter.BasePresenter;
import com.i7676.qyclient.function.view.BaseView;
import com.i7676.qyclient.interfaces.ToolbarAgent;
import com.i7676.qyclient.util.AnnotationUtil;

import net.grandcentrix.thirtyinch.TiFragment;

/**
 * Created by HCol on 2016/9/13.
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView> extends TiFragment<P, V> {

    protected ToolbarAgent mToolbarAgent;

    public void registerToolbarAgent(ToolbarAgent mToolbarAgent) {
        this.mToolbarAgent = mToolbarAgent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(AnnotationUtil.getLayoutResId(getClass()), container, false);
        initView(rootView);
        inject();
        return rootView;
    }

    protected abstract void inject();

    @Override
    public void onResume() {
        super.onResume();
        initHostToolbar();
    }

    protected abstract void initView(View view);

    protected void initHostToolbar() {
        if (mToolbarAgent != null) {
            mToolbarAgent.setBgColor(0xFFFF6F00);
            mToolbarAgent.opMenuVisibility(true);
        }
    }
}
