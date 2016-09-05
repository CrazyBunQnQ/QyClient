package com.i7676.qyclient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.i7676.qyclient.fragments.ActivitiesFragment;
import com.i7676.qyclient.fragments.ToolbarInteractorFragment;
import com.i7676.qyclient.fragments.GameFragment;
import com.roughike.bottombar.BottomBar;

public class MainActivity extends BaseActivity {

  private Toolbar mToolbar;

  private BottomBar mBottomBar;

  // fragments
  private GameFragment gameFragment;
  private ActivitiesFragment activitiesFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setTitle("");
    setSupportActionBar(mToolbar);
    mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
    mBottomBar.setDefaultTab(R.id.bottom_game);
    mBottomBar.setOnTabSelectListener(tabId -> {
      switch (tabId) {
        case R.id.bottom_game:
        default:
          if (gameFragment == null) {
            gameFragment = new GameFragment();
            gameFragment.registerToolbarAgent(this);
          }
          transactionCommit(gameFragment);
          break;
        case R.id.bottom_activities:
          if (activitiesFragment == null) {
            activitiesFragment = new ActivitiesFragment();
            activitiesFragment.registerToolbarAgent(this);
          }
          transactionCommit(activitiesFragment);
          break;
        case R.id.bottom_hi:
          break;
        case R.id._bottom_gifts:
          break;
        case R.id.bottom_profile:
          Intent mIntent = new Intent(this, LoginActivity.class);
          startActivity(mIntent);
          break;
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    return true;
  }

  @Override public void setBgColor(int color) {
    mToolbar.setBackgroundColor(color);
  }

  @Override public void setTitleText(String titleText) {
    mToolbar.setTitle(titleText);
    mToolbar.setTitleTextColor(Color.WHITE);
  }

  private void transactionCommit(ToolbarInteractorFragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.contentView, fragment, fragment.getClass().getName())
        .commit();
  }
}
