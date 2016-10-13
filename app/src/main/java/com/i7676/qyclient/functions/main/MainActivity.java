package com.i7676.qyclient.functions.main;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.functions.BaseActivity;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.util.ViewUtil;
import com.orhanobut.logger.Logger;
import com.roughike.bottombar.BottomBar;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
@Layout(R.layout.activity_main) public class MainActivity
    extends BaseActivity<MainAtyPresenter, MainAtyView> implements MainAtyView {

    public static String DEFAULT_QUERY_TEXT = "倚天屠龙";

    // Views
    private Toolbar mToolbar;
    private BottomBar mBottomBar;
    private PopupWindow mCategoryPopupWindow;
    //private EditText etSearch;
    private SearchView mSearchView;
    @Inject CategoryAdapter mCategoryAdapter;

    // Dagger
    private MainAtyComponent atyComponent;

    // Members
    private boolean opMenuVisibility = true;

    @Override public void initViews() {
        // 进行依赖注入
        initInject();
        // 初始化布局视图元素
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(getPresenter());

        //etSearch = (EditText) findViewById(R.id.et_search);
        mSearchView = (SearchView) findViewById(R.id.mSearchView);
        mSearchView.setQueryHint(DEFAULT_QUERY_TEXT);
        mSearchView.clearFocus();
        mSearchView.setOnQueryTextListener(getPresenter());

        mCategoryPopupWindow = ViewUtil.createPopWindow(this, mCategoryAdapter);
    }

    private void initInject() {
        atyComponent = DaggerMainAtyComponent.builder()
            .mainAtyModule(new MainAtyModule(this))
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();

        atyComponent.inject(getPresenter());
        atyComponent.inject(this);
    }

    @NonNull @Override public MainAtyPresenter providePresenter() {
        return new MainAtyPresenter();
    }

    public int getFrPlaceHolderResId() {
        return R.id.contentView;
    }

    public MainAtyComponent getAtyComponent() {
        return atyComponent;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.category:
                if (mCategoryPopupWindow.isShowing()) {
                    mCategoryPopupWindow.dismiss();
                } else {
                    mCategoryPopupWindow.showAsDropDown(mToolbar);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.category).setVisible(opMenuVisibility);
        if (!opMenuVisibility && mCategoryPopupWindow != null && mCategoryPopupWindow.isShowing()) {
            mCategoryPopupWindow.dismiss();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override public void setupCategoryPopupWindow(List<CategoryEntity> items) {
        mCategoryAdapter.setNewData(items);
    }

    @Override public void setTitle(String titleText) {
        mToolbar.setTitle(titleText);
    }

    @Override public void setToolbarBkg(int color) {
        mToolbar.setBackgroundColor(color);
    }

    @Override public void hideActionBar() {
        getSupportActionBar().hide();
        //this.etSearch.setVisibility(View.GONE);
        this.mSearchView.setVisibility(View.GONE);
    }

    @Override public void showActionBar() {
        getSupportActionBar().show();
        //this.etSearch.setVisibility(View.VISIBLE);
        this.mSearchView.setVisibility(View.VISIBLE);
    }

    @Override public void hideOptionsMenu() {
        closeOptionsMenu();
        opMenuVisibility = false;
        invalidateOptionsMenu();
    }

    @Override public void showSearchView() {
        this.mSearchView.setVisibility(View.VISIBLE);
    }

    @Override public void hideSearchView() {
        this.mSearchView.setVisibility(View.GONE);
    }

    @Override public void showOptionsMenu() {
        opMenuVisibility = true;
        invalidateOptionsMenu();
    }

    @Override public void closePopupWindowIfOpening() {
        if (mCategoryPopupWindow.isShowing()) mCategoryPopupWindow.dismiss();
    }

    @Override public void showHomeFr() {
        getPresenter().navigator.showHomeFr();
    }

    @Override public void setBottomBarIndex(int index) {
        mBottomBar.selectTabAtPosition(index);
    }

    @Override public void showBottomBar() {
        mBottomBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideBottomBar() {
        mBottomBar.setVisibility(View.GONE);
    }

    @Override public void showDialog2User(String msg) {
        this.showProgressDialog(msg);
    }

    @Override public void toast2User(String msg, int duration) {
        this.showToast2User(msg, duration);
    }

    @Override public void closeDialog() {
        this.closeProgressDialog();
    }

    @Override public String getKeywords() {
        return mSearchView.getQuery().toString();
    }

    @Override public void goSearch(String keywords) {
    }

    @Override public void showLogin() {
        getPresenter().navigator.showLoginAty();
    }

    @Override public void clearFocus() {
        try {
            this.getCurrentFocus().clearFocus();
        } catch (Exception e) {
            Logger.e(">>> clearFocus failed.");
        }
    }

    @Override public void onBackPressed() {
        if (mCategoryPopupWindow != null && mCategoryPopupWindow.isShowing()) {
            mCategoryPopupWindow.dismiss();
            return;
        }
        super.onBackPressed();
    }
}
