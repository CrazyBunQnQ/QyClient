package com.i7676.qyclient.functions.main.activity.detail.childFragment.RankingFragment;

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
import com.i7676.qyclient.functions.main.activity.detail.ActyDetaiActivity;
import com.i7676.qyclient.functions.main.activity.detail.ActyDetailComponent;
import com.i7676.qyclient.functions.main.adapters.ActivitesRinkAdapter;
import com.i7676.qyclient.widgets.MyDecoration;

import java.util.ArrayList;
import java.util.List;

@Layout(R.layout.fragment_activities_ranklist)
public class RankingListFrgment  extends BaseFragment<RanckPresenter,RankActyView> implements RankActyView {
    private TextView  tvrink,tvScore,tvnick;

    private RecyclerView mRecyclerView;
    private ActivitesRinkAdapter mAdapter;
    private ActyDetailComponent mActyDetailcomponent; //
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

    @Override
    protected void setupInject() {
        ((ActyDetaiActivity)getActivity()).getAtyComponent().inject(getPresenter());




    }


    @NonNull
    @Override
    public RanckPresenter providePresenter() {
        return new RanckPresenter();
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
this.clearList();
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void closeDialog() {
        this.closeDialog();

    }

    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void loadCompleted() {
        mAdapter.loadComplete();
       // Snackbar.make(mToolbar, "无更多数据", Snackbar.LENGTH_LONG).show();

    }
}