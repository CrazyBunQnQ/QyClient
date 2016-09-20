package com.i7676.qyclient.functions.login.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/9/20.
 *
 * R. Register
 * F. Forget
 *
 * RaF. Register and Forget password
 */

public class RaFFragment extends BaseFragment<RaFPresenter, RaFView> implements RaFView {

  public static RaFFragment create(Bundle args) {
    final RaFFragment fFragment = new RaFFragment();
    fFragment.setArguments(args);
    return fFragment;
  }

  @Override protected void initRootViews(View rootView) {

  }

  @NonNull @Override public RaFPresenter providePresenter() {
    return new RaFPresenter();
  }
}
