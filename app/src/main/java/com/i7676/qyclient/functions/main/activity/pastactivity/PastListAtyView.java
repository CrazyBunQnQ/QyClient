package com.i7676.qyclient.functions.main.activity.pastactivity;

import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public interface PastListAtyView extends BaseView {



     void addList(List<ActivitiesEntity> activitiesEntities);

    void clearList();

    void showDialog(String msg);

    void closeDialog();

    void showEmpty(String msg);

    void loadCompleted();

//
//    void setActionBarTitle(String titleText);
//
//    void showActionBar();
//
//    void setActionBarBackground(int color);
}
