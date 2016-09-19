package com.i7676.qyclient.functions.main.navigation;

import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.MainAtyView;
import com.i7676.qyclient.functions.main.activity.ActivityFragment;
import com.i7676.qyclient.functions.main.home.HomeFragment;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MainAtyNavigator {

  public static MainAtyNavigator create(MainActivity mAty) {
    return new MainAtyNavigator(mAty);
  }

  private MainActivity mAty;
  private BaseFragment selectedFragment;
  private int tabIndex;
  private HashMap<Integer, BaseFragment> frCached = new HashMap<>(5);

  private MainAtyNavigator(MainActivity mAty) {
    this.mAty = mAty;
  }

  public void showHome() {
    tabIndex = MainAtyView.TAB_INDEX_HOME;
    transform(tabIndex, selectedFragment =
        (frCached.get(tabIndex) != null ? frCached.get(tabIndex) : HomeFragment.create(null)));
  }

  public void showActivityFr() {
    tabIndex = MainAtyView.TAB_INDEX_ACTIVITY;
    transform(tabIndex, selectedFragment =
        (frCached.get(tabIndex) != null ? frCached.get(tabIndex) : ActivityFragment.create(null)));
  }

  public void showSelectedFragment() {
    if (selectedFragment == null) {
      showHome();
    } else {
      transform(tabIndex, selectedFragment);
    }
  }

  private void cacheFragment(int index, BaseFragment fragment) {
    if (frCached.get(index) == null) {
      frCached.put(index, fragment);
    }
  }

  private void transform(int index, BaseFragment fragment) {
    cacheFragment(index, fragment);
    mAty.getSupportFragmentManager()
        .beginTransaction()
        .replace(mAty.frPlaceHolderId(), fragment, fragment.getClass().getCanonicalName())
        .commit();
  }
}
