package com.i7676.qyclient.functions.main.activity.detail;

import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/10/9.
 */

public interface  ActyDetailView   extends BaseView {

    void getDetailFragment( ActivitiesEntity activitiesEntities);

     void  getRankingFragment();

}