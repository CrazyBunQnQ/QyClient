package com.i7676.qyclient.functions;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
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

    protected void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, null, msg);
        } else {
            mProgressDialog.setMessage(msg);
        }
        mProgressDialog.show();
    }

    protected void closeProgressDialog() {
        if (mProgressDialog == null || !mProgressDialog.isShowing()) return;
        mProgressDialog.dismiss();
    }

    protected void showToast2User(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    protected View.OnClickListener clickAsPressBack = v -> {
        onBackPressed();
    };
}
