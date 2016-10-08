package com.i7676.qyclient.functions.main.profile.detail.account;

import android.support.annotation.NonNull;
import android.view.View;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/10/8.
 */

public class AccountFragment extends BaseFragment<AccountFraPresenter, AccountFraView>
    implements AccountFraView {
    @Override protected void initRootViews(View rootView) {

    }

    @NonNull @Override public AccountFraPresenter providePresenter() {
        return null;
    }
}
