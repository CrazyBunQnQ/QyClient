package com.i7676.qyclient.function.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.PopupWindow;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.function.BaseActivity;
import com.i7676.qyclient.function.main.adapters.CategoryAdapter;
import com.i7676.qyclient.function.main.di.DaggerMainActivityComponent;
import com.i7676.qyclient.function.main.di.MainActivityComponent;
import com.i7676.qyclient.function.main.di.MainActivityModule;
import com.i7676.qyclient.function.main.entity.CategoryEntity;
import com.i7676.qyclient.function.main.presenter.MainActivityPresenter;
import com.i7676.qyclient.function.main.view.MainActivityView;
import com.roughike.bottombar.BottomBar;
import java.util.List;
import javax.inject.Inject;

@Layout(R.layout.activity_main) public class MainActivity
    extends BaseActivity<MainActivityPresenter, MainActivityView> implements MainActivityView {

  private Toolbar mToolbar;
  private BottomBar mBottomBar;
  private MainActivityComponent mainActivityComponent;

  public MainActivityComponent getMainActivityComponent() {
    return mainActivityComponent;
  }

  public Toolbar getmToolbar() {
    return mToolbar;
  }

  public BottomBar getmBottomBar() {
    return mBottomBar;
  }

  private boolean opMenuVisibility = true;
  @Inject PopupWindow mPopupWindow;
  @Inject CategoryAdapter mCategoryAdapter;

  @Override public void initViews() {
    initToolbar();
    initBottomBar();
    initInject();
  }

  private void initInject() {
    mainActivityComponent = DaggerMainActivityComponent.builder()
        .mainActivityModule(new MainActivityModule(this))
        .qyClientComponent(((QyClient) getApplication()).getComponent())
        .build();

    mainActivityComponent.inject(this);
  }

  private void initToolbar() {
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setTitle("");
    setSupportActionBar(mToolbar);
  }

  private void initBottomBar() {
    mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
    mBottomBar.setDefaultTab(R.id.bottom_game);
  }

  @Override public void renderCategoryItem(List<CategoryEntity> items) {
    mCategoryAdapter.setNewData(items);
  }

  @Override public void setToolbarTitle(String title) {
    mToolbar.setTitle(title);
  }

  @Override public void setToolbarBackground(int color) {
    mToolbar.setBackgroundColor(color);
  }

  @Override public void setToolbarOpMenuVisibility(boolean visibility) {
    opMenuVisibility = visibility;
    invalidateOptionsMenu();
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

  public int getTransformLayoutId() {
    return R.id.contentView;
  }

  @NonNull @Override public MainActivityPresenter providePresenter() {
    return new MainActivityPresenter(this);
  }
}
