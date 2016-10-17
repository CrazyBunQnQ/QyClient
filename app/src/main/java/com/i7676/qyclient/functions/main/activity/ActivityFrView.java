package com.i7676.qyclient.functions.main.activity;

import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface ActivityFrView extends BaseView {
    void setupActivityData(List<ActivitiesEntity> activities);

    void setActionBarTitle(String titleText);

    void showActionBar();

    void setActionBarBackground(int color);

    void setBottomBarIndex(int index);

    void hideSearchView();

    void showEnty();
}
