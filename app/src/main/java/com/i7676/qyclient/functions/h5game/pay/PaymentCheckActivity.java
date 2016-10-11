package com.i7676.qyclient.functions.h5game.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.functions.BaseActivity;
import com.switfpass.pay.activity.PayPlugin;
import com.switfpass.pay.bean.RequestMsg;

/**
 * Created by Administrator on 2016/10/10.
 */
public class PaymentCheckActivity
    extends BaseActivity<PaymentCheckAtyPresenter, PaymentCheckAtyView>
    implements PaymentCheckAtyView {

    private PaymentCheckAtyComponent atyComponent;

    public static Intent buildIntent(Context context, Bundle args) {
        final Intent mIntent = new Intent(context, PaymentCheckActivity.class);
        mIntent.putExtras(args);
        return mIntent;
    }

    @Override public void initViews() {
        setupInject();
        showProgressDialog("加载中...");
    }

    private void setupInject() {
        atyComponent = DaggerPaymentCheckAtyComponent.builder()
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();

        atyComponent.inject(getPresenter());
    }

    @NonNull @Override public PaymentCheckAtyPresenter providePresenter() {
        return new PaymentCheckAtyPresenter(getIntent().getExtras());
    }

    @Override public void go2Payment(RequestMsg msg) {
        PayPlugin.unifiedH5Pay(this, msg);
    }

    @Override public void payResults(String msg) {
        closeProgressDialog();
        showToast2User(msg, Toast.LENGTH_SHORT);
        getPresenter().resetPayFlag();
    }
}
