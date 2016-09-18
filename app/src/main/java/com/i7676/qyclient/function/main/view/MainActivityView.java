package com.i7676.qyclient.function.main.view;

import com.i7676.qyclient.function.main.entity.CategoryEntity;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public interface MainActivityView extends BaseView {

  void renderCategoryItem(List<CategoryEntity> items);

  //******************************************************** Toolbar
  void setToolbarTitle(String title);

  void setToolbarBackground(int color);

  void setToolbarOpMenuVisibility(boolean visibility);
}
