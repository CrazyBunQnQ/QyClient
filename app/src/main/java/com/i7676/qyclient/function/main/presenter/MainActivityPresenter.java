package com.i7676.qyclient.function.main.presenter;

import android.support.v4.app.Fragment;
import com.i7676.qyclient.function.main.MainActivity;
import com.i7676.qyclient.function.main.entity.CategoryEntity;
import com.i7676.qyclient.function.main.fragments.GameFragment;
import com.i7676.qyclient.function.main.view.MainActivityView;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/14.
 */
public class MainActivityPresenter extends BasePresenter<MainActivityView> {

  private ArrayList<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>() {
    {
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", "最新上线"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", "网络游戏"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", "小游戏"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", "专题游戏"));
    }
  };

  public MainActivityPresenter(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  private MainActivity mainActivity;

  @Override protected void onWakeUp() {
    super.onWakeUp();
    if (mainActivity.getMainActivityComponent() != null) {
      final GameFragment gameFragment = GameFragment.create();
      mainActivity.getMainActivityComponent().inject(gameFragment);
      transformFragment(gameFragment);
    }
    getView().renderCategoryItem(categoryEntities);
  }

  private void transformFragment(Fragment fragment) {
    mainActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(mainActivity.getTransformLayoutId(), fragment,
            fragment.getClass().getCanonicalName())
        .commit();
  }
}
