package com.i7676.qyclient;

import android.support.v7.app.AppCompatActivity;
import com.i7676.qyclient.interfaces.ToolbarAgent;

/**
 * Created by Administrator on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity implements ToolbarAgent {
  @Override public void setBgColor(int bgColor) {

  }

  @Override public void setTitleText(String titleText) {

  }
}
