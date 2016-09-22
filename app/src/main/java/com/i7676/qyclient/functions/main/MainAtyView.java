package com.i7676.qyclient.functions.main;

import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface MainAtyView extends BaseView {

    int TAB_INDEX_HOME = 0;
    int TAB_INDEX_ACTIVITY = 1;
    int TAB_INDEX_HI = 2;
    int TAB_INDEX_GIFT = 3;
    int TAB_INDEX_PROFILE = 4;

    void setupCategoryPopupWindow(List<CategoryEntity> items);

    void setTitle(String titleText);

    void setToolbarBkg(int color);

    void hideActionBar();

    void showActionBar();

    void hideOptionsMenu();

    void showOptionsMenu();

    void showHomeFr();

    void setBottomBarIndex(int index);

    void showBottomBar();

    void hideBottomBar();
}
