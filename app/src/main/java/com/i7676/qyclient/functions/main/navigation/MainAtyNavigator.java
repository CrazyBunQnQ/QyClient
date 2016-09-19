package com.i7676.qyclient.functions.main.navigation;

import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.activity.ActivityFragment;
import com.i7676.qyclient.functions.main.home.HomeFragment;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MainAtyNavigator {

  public static MainAtyNavigator create(MainActivity mAty) {
    return new MainAtyNavigator(mAty);
  }

  private MainActivity mAty;
  private BaseFragment selectedFragment;
  private ArrayList<BaseFragment> frCached = new ArrayList<>(5);

  private MainAtyNavigator(MainActivity mAty) {
    this.mAty = mAty;
  }

  public void showHome() {
    final HomeFragment fragment = HomeFragment.create(null);
    transform((selectedFragment = fragment));
  }

  public void showActivityFr() {
    final ActivityFragment fragment = ActivityFragment.create(null);
    transform((selectedFragment = fragment));
  }

  public void showSelectedFragment() {
    if (selectedFragment == null) {
      showHome();
    } else {
      transform(selectedFragment);
    }
  }

  //private void cacheFragment(BaseFragment fragment) {
  //  if (!frCached.contains(fragment)) {
  //    frCached.add(fragment);
  //  }
  //}

  private void transform(BaseFragment fragment) {
    //cacheFragment(fragment);
    mAty.getSupportFragmentManager()
        .beginTransaction()
        .replace(mAty.frPlaceHolderId(), fragment, fragment.getClass().getCanonicalName())
        .commit();
  }
}
