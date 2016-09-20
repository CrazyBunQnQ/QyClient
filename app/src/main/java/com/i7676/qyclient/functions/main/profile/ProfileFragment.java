package com.i7676.qyclient.functions.main.profile;

import android.support.annotation.NonNull;
import android.view.View;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/9/20.
 */

public class ProfileFragment extends BaseFragment<ProfileFrPresenter, ProFileFrView>
    implements ProFileFrView {
  @Override protected void initRootViews(View rootView) {

  }

  @NonNull @Override public ProfileFrPresenter providePresenter() {
    return null;
  }
}
