package com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail;

import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/10/17.
 */
@Layout(R.id.activity_activites_detail)
public interface PastDetailView  extends BaseView {

    void  getRankingFragment();
    void  getOnListFragment();
}
