package com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.childfragment.pastrankFragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.RankingActyEnty;
import com.i7676.qyclient.functions.BaseFragment;


import com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.PastDetailActivity;
import com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.PastDetailComponent;
import com.i7676.qyclient.functions.main.adapters.ActivitesRinkAdapter;
import com.i7676.qyclient.widgets.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
@Layout(R.layout.fragment_activities_ranklist)
public class PastRankFragment  extends BaseFragment<PastRankPresenter,PastRankView> implements  PastRankView{


    private PastDetailComponent mpastDetailComponent;
    private TextView tvrink,tvScore,tvnick;
    private RecyclerView mRecyclerView;
    private ActivitesRinkAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void initRootViews(View rootView) {
        // setInject();
        tvrink= (TextView) rootView.findViewById(R.id.tv_activites_rink);
        tvScore= (TextView) rootView.findViewById(R.id.tv_score);
        tvnick = (TextView) rootView.findViewById(R.id.tv_activites_nick);
        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.mRecyview);
        mSwipeRefreshLayout =(SwipeRefreshLayout) rootView.findViewById(R.id.rank_sr); //下啦刷新
        mAdapter= new ActivitesRinkAdapter(R.layout.item_franking_list,new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(getPresenter());
        mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


    }

    @NonNull
    @Override
    public PastRankPresenter providePresenter() {
        return new PastRankPresenter();
    }

    @Override
    protected void setupInject() {


//
        ((PastDetailActivity)getActivity()).getmPastDettailComponent().inject(getPresenter());



    }

    @Override
    public void addlist(List<RankingActyEnty> rankingAcyList) {
        mRecyclerView.setVisibility(View.VISIBLE);
        // mEmptyView.setVisibility(View.GONE);
        mAdapter.addData(rankingAcyList);
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(Boolean.FALSE);

    }

    @Override
    public void clearList() {

    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void loadCompleted() {
        mAdapter.loadComplete();



    }
}

