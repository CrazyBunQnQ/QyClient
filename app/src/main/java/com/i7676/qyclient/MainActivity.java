package com.i7676.qyclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.i7676.qyclient.fragments.GameFragment;
import com.roughike.bottombar.BottomBar;

public class MainActivity extends AppCompatActivity implements GameFragment.ToolbarAlphaHelper {

  private Toolbar mToolbar;

  private BottomBar mBottomBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setTitle("");
    setSupportActionBar(mToolbar);

    mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

    GameFragment gameFragment = new GameFragment();
    gameFragment.setToolbarAlphaHelper(this);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.contentView, gameFragment, GameFragment.class.getName())
        .commit();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    return true;
  }

  @Override public void setBgColor(int color) {
    mToolbar.setBackgroundColor(color);
  }
}
