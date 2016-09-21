package com.i7676.qyclient.functions.main.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/9/20.
 */
@Layout(R.layout.fragment_login_reg_fgtpass) public class ProfileFragment
    extends BaseFragment<ProfileFrPresenter, ProfileFrView> implements ProfileFrView {

    public static ProfileFragment create(Bundle args) {
        final ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override protected void initRootViews(View rootView) {

    }

    @NonNull @Override public ProfileFrPresenter providePresenter() {
        return new ProfileFrPresenter();
    }
}
