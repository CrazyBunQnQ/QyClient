package com.i7676.qyclient.functions.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.ActivityFrAdapter;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 *
 * 主页-活动
 */
@Layout(R.layout.fragment_activitiy) public class ActivityFragment
    extends BaseFragment<ActivityFrPresenter, ActivityFrView> implements ActivityFrView {

    public static ActivityFragment create(@Nullable Bundle args) {
        final ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Views
    private RecyclerView activityRecyclerView;
    @Inject ActivityFrAdapter activityFrAdapter;

    @Override protected void initRootViews(View rootView) {
        activityRecyclerView = (RecyclerView) rootView.findViewById(R.id.activities_recyclerView);
        activityRecyclerView.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        activityRecyclerView.setAdapter(activityFrAdapter);
    }

    @Override protected void setupInject() {
        ((MainActivity) getActivity()).getAtyComponent().inject(this);
        ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public ActivityFrPresenter providePresenter() {
        return new ActivityFrPresenter();
    }

    @Override public void setupActivityData(List<String> activities) {
        activityFrAdapter.setNewData(activities);
    }

    @Override public void setActionBarTitle(String titleText) {
        ((MainActivity) getActivity()).getPresenter().getView().setTitle(titleText);
    }

    @Override public void showActionBar() {
        ((MainActivity) getActivity()).getPresenter().getView().showActionBar();
    }

    @Override public void setActionBarBackground(int color) {
        ((MainActivity) getActivity()).getPresenter().getView().setToolbarBkg(color);
    }

    @Override public void setBottomBarIndex(int index) {
        ((MainActivity) getActivity()).getPresenter().getView().setBottomBarIndex(index);
    }

    @Override public void hideOptionsMenu() {
        ((MainActivity) getActivity()).getPresenter().getView().hideOptionsMenu();
    }
}
