package com.i7676.qyclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.i7676.qyclient.fragments.GameFragment;
import com.roughike.bottombar.BottomBar;

public class MainActivity extends AppCompatActivity {

  private Toolbar mToolbar;
  private BottomBar mBottomBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setTitle("");
    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    setSupportActionBar(mToolbar);

    mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.contentView, new GameFragment(), GameFragment.class.getName())
        .commit();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    return true;
  }
}
