package com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.childfragment.pastonlistfragment;

import com.i7676.qyclient.entity.RankingActyEnty;
import com.i7676.qyclient.functions.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface OnListView extends BaseView{
    void addlist(List<RankingActyEnty> rankingAcyList);

    void clearList();

    void showDialog(String msg);

    void closeDialog();


    void showEmpty(String msg);

    void loadCompleted();
}
