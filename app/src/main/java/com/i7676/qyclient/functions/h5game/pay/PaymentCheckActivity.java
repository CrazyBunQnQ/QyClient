package com.i7676.qyclient.functions.h5game.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.i7676.qyclient.functions.BaseActivity;

/**
 * Created by Administrator on 2016/10/10.
 */

public class PaymentCheckActivity
    extends BaseActivity<PaymentCheckAtyPresenter, PaymentCheckAtyView>
    implements PaymentCheckAtyView {

    public static Intent buildIntent(Context context, Bundle args) {
        final Intent mIntent = new Intent(context, PaymentCheckActivity.class);
        mIntent.putExtras(args);
        return mIntent;
    }

    @Override public void initViews() {

    }

    @NonNull @Override public PaymentCheckAtyPresenter providePresenter() {
        return new PaymentCheckAtyPresenter(getIntent().getExtras());
    }
}
