package com.i7676.qyclient;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.fragments.ActivitiesFragment;
import com.i7676.qyclient.fragments.GameFragment;
import com.i7676.qyclient.fragments.ToolbarInteractorFragment;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.roughike.bottombar.BottomBar;
import java.util.ArrayList;
import java.util.List;

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
          startActivity(LoginActivity.getIntent(this));
          break;
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    return true;
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    menu.findItem(R.id.category).setVisible(opMenuVisibility);
    if (!opMenuVisibility && mPopupWindow != null && mPopupWindow.isShowing()) {
      mPopupWindow.dismiss();
    }
    return super.onPrepareOptionsMenu(menu);
  }

  // ***************************************************************************    temporary
  private ArrayList<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>() {
    {
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", "最新上线"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", "网络游戏"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", "小游戏"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", "专题游戏"));
    }
  };

  private class CategoryEntity {
    private String imageURL;
    private String categoryText;

    public CategoryEntity(String imageURL, String categoryText) {
      this.imageURL = imageURL;
      this.categoryText = categoryText;
    }

    public String getImageURL() {
      return imageURL;
    }

    public String getCategoryText() {
      return categoryText;
    }
  }

  private class CategoryAdapter extends BaseQuickAdapter<CategoryEntity> {

    public CategoryAdapter(int layoutResId, List<CategoryEntity> data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, CategoryEntity categoryEntity) {
      ((AutoLoadImageView) baseViewHolder.getConvertView()
          .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(categoryEntity.getImageURL(),
          null);
      baseViewHolder.setText(R.id.category_text, categoryEntity.getCategoryText());
    }
  }

  // ***************************************************************************    temporary

  private PopupWindow ensurePopWindow(Context ctx) {
    // init content view
    NonScrollableRecyclerView categoryList = new NonScrollableRecyclerView(ctx);
    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
    categoryList.setLayoutParams(lp);
    categoryList.setBackgroundColor(Color.WHITE);
    categoryList.setLayoutManager(new GridLayoutManager(ctx, 4));
    categoryList.setHasFixedSize(true);
    categoryList.setAdapter(new CategoryAdapter(R.layout.item_game_category, categoryEntities));

    // get window height
    Display display = ((AppCompatActivity) ctx).getWindowManager().getDefaultDisplay();
    DisplayMetrics dm = new DisplayMetrics();
    display.getMetrics(dm);
    int height = dm.heightPixels;
    int width = dm.widthPixels;

    // custom window display
    final PopupWindow popupWindow = new PopupWindow(categoryList);
    popupWindow.setWidth(width);
    popupWindow.setHeight(height);
    popupWindow.setBackgroundDrawable(ctx.getResources().getDrawable(R.drawable.pub_popwindow));
    return popupWindow;
  }

  private PopupWindow mPopupWindow;

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (mPopupWindow == null) mPopupWindow = ensurePopWindow(this);
    switch (item.getItemId()) {
      case R.id.category:
        if (mPopupWindow.isShowing()) {
          mPopupWindow.dismiss();
        } else {
          mPopupWindow.showAsDropDown(mToolbar);
        }
        break;
    }
    return true;
  }

  @Override public void setBgColor(int color) {
    mToolbar.setBackgroundColor(color);
  }


  @Override public void setTitleText(String titleText) {
    mToolbar.setTitle(titleText);
    mToolbar.setTitleTextColor(Color.WHITE);
  }

  private boolean opMenuVisibility = true;

  @Override public void opMenuVisibility(boolean visibility) {
    opMenuVisibility = visibility;
    invalidateOptionsMenu();
  }

  @Override public BottomBar getBottomBar() {
    return mBottomBar;
  }

  private void transactionCommit(ToolbarInteractorFragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.contentView, fragment, fragment.getClass().getName())
        .commit();
  }

  @Override public void onBackPressed() {
    if (mPopupWindow != null && mPopupWindow.isShowing()) {
      mPopupWindow.dismiss();
      return;
    }
    super.onBackPressed();
  }
}
