package com.i7676.qyclient.functions;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import net.grandcentrix.thirtyinch.TiActivity;

/**
 * Created by Administrator on 2016/9/19.
 */
public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView>
    extends TiActivity<P, V> implements BaseView {

    private ProgressDialog mProgressDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Goto {@link BasePresenter#create()}
         */
        setContentView(com.i7676.qyclient.util.AnnotationUtil.getLayoutResId(this.getClass()));
        initViews();
    }

    protected void showProcessDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, null, msg);
        } else {
            mProgressDialog.setMessage(msg);
        }
        mProgressDialog.show();
    }

    protected void closeProcessDialog() {
        if (mProgressDialog == null || !mProgressDialog.isShowing()) return;

        mProgressDialog.dismiss();
    }
}
