package com.i7676.qyclient.functions.main.profile.detail.recharge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/10/8.
 */

public class RechargeFragment extends BaseFragment<RechargeFraPresenter, RechargeFraView>
    implements RechargeFraView {

    public static RechargeFragment create(@Nullable Bundle args) {
        final RechargeFragment fragment = new RechargeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override protected void initRootViews(View rootView) {

    }

    @NonNull @Override public RechargeFraPresenter providePresenter() {
        return null;
    }
}
