package com.i7676.qyclient.function.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import com.i7676.qyclient.R;
import com.i7676.qyclient.function.main.fragments.LoginMainFragment;
import com.roughike.bottombar.BottomBar;

/**
 * Created by Administrator on 2016/8/31.
 */

public class LoginActivity extends BaseActivity {

  private static final String TAG = LoginActivity.class.getName();

  public static final Intent getIntent(Context from) {
    return new Intent(from, LoginActivity.class);
  }

  // widgets
  private Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitleTextColor(Color.WHITE);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(v -> {
      LoginActivity.this.onBackPressed();
    });
    setSupportActionBar(toolbar);

    getSupportFragmentManager().beginTransaction()
        .add(R.id.container, LoginMainFragment.create(this))
        .addToBackStack(LoginMainFragment.class.getSimpleName())
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
    if (getSupportFragmentManager().getFragments().size() <= 1
        || getSupportFragmentManager().getFragments().get(1) == null) {
      this.finish();
    } else {
      super.onBackPressed();
    }
  }
}
