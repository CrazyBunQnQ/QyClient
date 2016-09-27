package com.i7676.qyclient.functions;

import android.app.ProgressDialog;
import android.os.Bundle;
import net.grandcentrix.thirtyinch.TiActivity;

/**
 * Created by Administrator on 2016/9/19.
 */
public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView>
    extends TiActivity<P, V> implements BaseView {

    private ProgressDialog mDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Goto {@link BasePresenter#create()}
         */
        setContentView(com.i7676.qyclient.util.AnnotationUtil.getLayoutResId(this.getClass()));
        initViews();
    }

    protected void showProcessDialog(String msg) {
        if (mDialog == null) {
            mDialog = ProgressDialog.show(this, null, msg);
        } else {
            mDialog.setMessage(msg);
        }
        mDialog.show();
    }

    protected void closeProcessDialog() {
        if (mDialog == null || !mDialog.isShowing()) return;

        mDialog.dismiss();
    }
}
