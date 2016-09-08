package com.i7676.qyclient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import com.i7676.qyclient.fragments.LoginMainFragment;
import com.roughike.bottombar.BottomBar;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */

public class LoginActivity extends BaseActivity {

  private static final String TAG = LoginActivity.class.getName();

  public static final Intent getIntent(Context from) {
    return new Intent(from, LoginActivity.class);
  }

  // QQ
  private static final String QQ_API_ID = "1105660746";
  private Tencent mTencent;

  // WeiXin
  private static final String WX_APP_ID = "wx2534d167763b1f30";
  private static final String WX_APP_SECRET = "";
  private IWXAPI api;

  // widgets
  private Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("注册登录");
    toolbar.setTitleTextColor(Color.WHITE);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    setSupportActionBar(toolbar);

    getSupportFragmentManager().beginTransaction()
        .add(R.id.container, new LoginMainFragment(), LoginMainFragment.class.getSimpleName())
        .commit();
  }

  @Override public BottomBar getBottomBar() {
    return null;
  }

  @Override public void setTitleText(String titleText) {
    super.setTitleText(titleText);
    toolbar.setTitle(titleText);
  }

  @Override public void onBackPressed() {
    List<Fragment> fragments = getSupportFragmentManager().getFragments();
  }
}
