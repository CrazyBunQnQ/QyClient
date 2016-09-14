package com.i7676.qyclient.function.main;

import android.support.v7.app.AppCompatActivity;
import com.i7676.qyclient.function.main.fragments.ToolbarAgent;
import com.roughike.bottombar.BottomBar;

/**
 * Created by Administrator on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity implements ToolbarAgent {
  @Override public void setBgColor(int bgColor) {
    // empty
  }

  @Override public void setTitleText(String titleText) {
    // empty
  }

  @Override public void opMenuVisibility(boolean visibility) {
    // empty
  }

  public abstract BottomBar getBottomBar();
}
