package com.i7676.qyclient.functions.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.activity.detail.ActyDetaiActivity;
import com.i7676.qyclient.functions.main.activity.pastactivity.PastListActivity;
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
    private Button but_query;
    private List<ActivitiesEntity> list;

    @Override protected void initRootViews(View rootView) {
        activityRecyclerView = (RecyclerView) rootView.findViewById(R.id.activities_recyclerView);
        activityRecyclerView.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        addFootView();
        //增加尾部
        //activityFrAdapter.addFooterView(R.layout.activities_recyview_footer,0);
        activityRecyclerView.setAdapter(activityFrAdapter);
        //addFootView();
        activityRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view,
                int i) {
                Toast.makeText(getActivity(), "你好啊", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ActyDetaiActivity.class);
                intent.putExtra("id", list.get(i).getId());
                intent.putExtra("description", list.get(i).getDescription());
                startActivity(intent);
            }
        });
    }

    private void addFootView() {
        View FootView =
            LayoutInflater.from(getActivity()).inflate(R.layout.activities_recyview_footer, null);
        // View FootView= Layinflate(R.layout.activities_recyview_footer, null);
        //  View FootView = getLayoutInflater(getActivity().).inflate(R.layout.activities_recyview_footer, (ViewGroup) activityRecyclerView.getParent(), false);
        //  ((Button) FootView.findViewById(R.id.btn_activity_query)
        but_query = (Button) FootView.findViewById(R.id.btn_activity_query);
        // final View customLoading = getLayoutInflater().inflate(R.layout.custom_loading, (ViewGroup) mRecyclerView.getParent(), false);
        but_query.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PastListActivity.class);
            startActivity(intent);
        });
        activityFrAdapter.addHeaderView(FootView);
    }

    @Override protected void setupInject() {
        ((MainActivity) getActivity()).getAtyComponent().inject(this);
        ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public ActivityFrPresenter providePresenter() {
        return new ActivityFrPresenter();
    }

    //    @Override public void setupActivityData(List<ActivitiesEntity> activities) {
    //        activityFrAdapter.setNewData(activities);
    //    }

    @Override public void setupActivityData(List<ActivitiesEntity> activities) {
        activityFrAdapter.addData(activities);
        list = activities;
    }

    @Override public void setActionBarTitle(String titleText) {
        ((MainActivity) getActivity()).setTitle(titleText);
    }

    @Override public void showActionBar() {
        ((MainActivity) getActivity()).showActionBar();
    }

    @Override public void setActionBarBackground(int color) {
        ((MainActivity) getActivity()).setToolbarBkg(color);
    }

    @Override public void setBottomBarIndex(int index) {
        ((MainActivity) getActivity()).setBottomBarIndex(index);
    }

    @Override public void hideSearchView() {
        ((MainActivity) getActivity()).hideSearchView();
    }

    @Override public void showEnty() {

    }
}
