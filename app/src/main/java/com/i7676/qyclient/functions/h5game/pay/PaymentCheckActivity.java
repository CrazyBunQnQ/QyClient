package com.i7676.qyclient.functions.h5game.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseActivity;
import com.switfpass.pay.activity.PayPlugin;
import com.switfpass.pay.bean.RequestMsg;

/**
 * Created by Administrator on 2016/10/10.
 *
 * 不需要这个 Activity
 *
 * 直接在 PlayGameActivity 里面处理就好了
 *
 * 这个 Activity 是多余的,应该是一个威富通的包装工具类...
 */
@Layout(R.layout.activity_payment) public class PaymentCheckActivity
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

    @Override public void loadProgressDialog(String msg) {
        this.showProgressDialog(msg);
    }

    @Override public void payResults(String msg) {
        closeProgressDialog();
        showToast2User(msg, Toast.LENGTH_SHORT);
        getPresenter().resetPayFlag();
        this.finish();
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {
            return;
        }

        String retCode = data.getExtras().getString("resultCode");
        if (!TextUtils.isEmpty(retCode) && "success".equals(retCode)) {
            payResults("支付完成");
        } else {
            payResults("支付失败");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
