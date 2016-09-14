package com.i7676.qyclient.function.main.view;

import android.app.Fragment;

/**
 * Created by Administrator on 2016/9/14.
 */
public interface MainActivityView extends BaseView {

  void fragmentCommit(Fragment fragment);

  //******************************************************** Toolbar
  void setToolbarTitle(String title);

  void setToolbarBackground(int color);

  void setToolbarOpMenuVisibility(boolean visibility);
}
