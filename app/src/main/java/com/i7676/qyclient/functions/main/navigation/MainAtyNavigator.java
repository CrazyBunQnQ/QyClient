package com.i7676.qyclient.functions.main.navigation;

import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.home.HomeFragment;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MainAtyNavigator {

  public static MainAtyNavigator create(MainActivity mAty) {
    return new MainAtyNavigator(mAty);
  }

  private MainActivity mAty;
  private BaseFragment selectedFragment;

  private MainAtyNavigator(MainActivity mAty) {
    this.mAty = mAty;
  }

  public void showHome() {
    final HomeFragment fragment = HomeFragment.create(null);
    transform(fragment);
  }

  public void showSelectedFragment() {
    if (selectedFragment == null) {
      showHome();
    } else {
      transform(selectedFragment);
    }
  }

  private void transform(BaseFragment fragment) {
    selectedFragment = fragment;
    mAty.getSupportFragmentManager()
        .beginTransaction()
        .replace(mAty.frPlaceHolderId(), fragment, fragment.getClass().getCanonicalName())
        .commit();
  }
}
