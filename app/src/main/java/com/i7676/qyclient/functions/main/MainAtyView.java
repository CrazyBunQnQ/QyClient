package com.i7676.qyclient.functions.main;

import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface MainAtyView extends BaseView {

  void setupCategoryPopupWindow(List<CategoryEntity> items);

  void invalidateAtyOptionsMenu();

  void setBottomBarSelectedIndex(int index);

  void showBottomBar();

  void hideBottomBar();
}
