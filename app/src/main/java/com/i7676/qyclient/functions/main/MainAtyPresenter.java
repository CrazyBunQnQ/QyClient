package com.i7676.qyclient.functions.main;

import android.support.annotation.IdRes;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import com.i7676.qyclient.net.EgretApiService;
import com.roughike.bottombar.OnTabSelectListener;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MainAtyPresenter extends BasePresenter<MainAtyView> implements OnTabSelectListener {

  @Inject EgretApiService mEgretApiService;
  @Inject MainAtyNavigator navigator;

  // Activity的生命周期被Presenter来接管了
  @Override protected void onCreate() {
    super.onCreate();
  }

  @Override protected void onWakeUp() {
    super.onWakeUp();
    reqCategoryData();
    navigator.showSelectedFragment();
  }

  private void reqCategoryData() {
    List<CategoryEntity> categoryEntities = new ArrayList<>();
    categoryEntities.add(
        new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", "最新上线"));
    categoryEntities.add(
        new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", "网络游戏"));
    categoryEntities.add(
        new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", "小游戏"));
    categoryEntities.add(
        new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", "专题游戏"));
    getView().setupCategoryPopupWindow(categoryEntities);
  }

  @Override public void onTabSelected(@IdRes int tabId) {
    switch (tabId) {
      case R.id.bottom_home:
      default:
        navigator.showHome();
        break;
      case R.id.bottom_hi:
        break;
      case R.id._bottom_gifts:
        break;
      case R.id.bottom_profile:
        break;
    }
  }
}
