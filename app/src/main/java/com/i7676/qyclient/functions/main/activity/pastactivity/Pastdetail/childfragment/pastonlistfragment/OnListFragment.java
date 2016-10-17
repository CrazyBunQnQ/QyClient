package com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.childfragment.pastonlistfragment;

import android.support.annotation.NonNull;
import android.view.View;

import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/10/17.
 *
 */
@Layout(R.layout.fragment_activities_ranklist)
public class OnListFragment  extends BaseFragment<OnListPresenter,OnListView> {


    @Override
    protected void initRootViews(View rootView) {

    }

    @NonNull
    @Override
    public OnListPresenter providePresenter() {
        return new OnListPresenter();
    }
}
