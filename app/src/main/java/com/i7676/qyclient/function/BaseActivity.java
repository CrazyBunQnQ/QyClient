package com.i7676.qyclient.function;

import android.os.Bundle;
import android.view.View;

import com.i7676.qyclient.function.main.presenter.BasePresenter;
import com.i7676.qyclient.function.main.view.BaseView;
import com.i7676.qyclient.util.AnnotationUtil;

import net.grandcentrix.thirtyinch.TiActivity;

/**
 * Created by HCol on 2016/9/18.
 */
public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView> extends TiActivity<P, V> implements BaseView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(AnnotationUtil.getLayoutResId(getClass()));
        initViews();
    }

    @Override
    public void initViews(View rootView) {
        // empty
    }
}
