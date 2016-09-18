package com.i7676.qyclient.function.login;

import android.support.annotation.NonNull;

import com.i7676.qyclient.function.BaseActivity;
import net.grandcentrix.thirtyinch.TiPresenter;

/**
 * Created by Administrator on 2016/8/31.
 */

public class LoginActivity extends BaseActivity {
    @Override
    public void initViews() {

    }

    @NonNull
    @Override
    public TiPresenter providePresenter() {
        return null;
    }

//  private static final String TAG = LoginActivity.class.getName();
//
//  public static final Intent getIntent(Context from) {
//    return new Intent(from, LoginActivity.class);
//  }
//
//  // widgets
//  private Toolbar toolbar;
//
//  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_login);
//
//    toolbar = (Toolbar) findViewById(R.id.toolbar);
//    toolbar.setTitleTextColor(Color.WHITE);
//    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//    toolbar.setNavigationOnClickListener(v -> {
//      LoginActivity.this.onBackPressed();
//    });
//    setSupportActionBar(toolbar);
//
//    getSupportFragmentManager().beginTransaction()
//        .add(R.id.container, LoginMainFragment.create(this))
//        .addToBackStack(LoginMainFragment.class.getSimpleName())
//        .commit();
//  }
//
//  @Override public BottomBar getBottomBar() {
//    return null;
//  }
//
//  @Override public void setTitleText(String titleText) {
//    super.setTitleText(titleText);
//    toolbar.setTitle(titleText);
//  }
//
//  @Override public void onBackPressed() {
//    if (getSupportFragmentManager().getFragments().size() <= 1
//        || getSupportFragmentManager().getFragments().get(1) == null) {
//      this.finish();
//    } else {
//      super.onBackPressed();
//    }
//  }
}
