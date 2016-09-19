package com.i7676.qyclient.functions.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;

/**
 * Created by Administrator on 2016/9/19.
 *
 * 主页-活动
 */
@Layout(R.layout.fragment_activitiy) public class ActivityFragment
    extends BaseFragment<ActivityFrPresenter, ActivityFrView> {

  public static ActivityFragment create(@Nullable Bundle args) {
    final ActivityFragment fragment = new ActivityFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initRootViews(View rootView) {
    initInject();
  }

  @NonNull @Override public ActivityFrPresenter providePresenter() {
    return new ActivityFrPresenter();
  }

  private void initInject() {
    ((MainActivity) getActivity()).getAtyComponent().inject(this);
    ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
  }
}
