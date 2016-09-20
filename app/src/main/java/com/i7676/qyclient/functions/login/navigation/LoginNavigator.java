package com.i7676.qyclient.functions.login.navigation;

import android.util.SparseArray;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.functions.login.sign.SignInFragment;
import com.i7676.qyclient.functions.main.MainAtyView;

/**
 * Created by Administrator on 2016/9/20.
 */

public class LoginNavigator {

  public static LoginNavigator create(LoginActivity loginActivity) {
    return new LoginNavigator(loginActivity);
  }

  private LoginActivity loginActivity;
  private BaseFragment selectedFragment;
  private int tabIndex;
  private SparseArray<BaseFragment> frCached = new SparseArray<>(5);

  private LoginNavigator(LoginActivity loginActivity) {
    this.loginActivity = loginActivity;
  }

  public void showSignIn() {
    tabIndex = MainAtyView.TAB_INDEX_HOME;
    transform(tabIndex, selectedFragment =
        (frCached.get(tabIndex) != null ? frCached.get(tabIndex) : SignInFragment.create(null)));
  }

  public void showSelectedFragment() {
    if (selectedFragment == null) {
      showSignIn();
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
    loginActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(loginActivity.getFrPlaceHolderResId(), fragment,
            fragment.getClass().getCanonicalName())
        .commit();
  }
}
