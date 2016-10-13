package com.i7676.qyclient.functions.main.activity.pastactivity;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BaseActivity;
import com.i7676.qyclient.functions.main.adapters.PastActivityListAdapter;
import com.i7676.qyclient.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
@Layout(R.layout.activity_pastactivity)
public class PastListActivity  extends BaseActivity<PastListAtyPresenter,PastListAtyView> implements  PastListAtyView {


    private RecyclerView mRecyclerView;
    private PastActivityListAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private  Toolbar mToolbar;
   // private View mEmptyView;


    private PastListAtyComponent pastListComponent;

    @Override
    public void initViews() {
        setupInject();
        mRecyclerView= (RecyclerView) findViewById(R.id.past_recyclerView);
        mSwipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.activities_Sresh);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // mAdapter = new GameListAtyAdapter(R.layout.item_game_list, new ArrayList<>());
        //mAdapter.setEmptyView(emptyView);
        mAdapter= new PastActivityListAdapter(R.layout.item_activities_list,new ArrayList<>());
//        mAdapter.setOnLoadMoreListener(getPresenter());
//
//        mSwipeRefreshLayout.setOnRefreshListener(getPresenter());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new PastListActivity.SpacesItemDecoration(DensityUtil.dip2px(this, 8.0f)));

    }

    private void setupInject() {
//
        pastListComponent= DaggerPastListAtyComponent.builder().qyClientComponent(((QyClient)getApplication()).getClientComponent())
                .build();
        pastListComponent.inject(getPresenter());
    }

    @Override
    public void addList(List<ActivitiesEntity> activitiesEntities) {
        mRecyclerView.setVisibility(View.VISIBLE);
      //  mEmptyView.setVisibility(View.GONE);
        mAdapter.addData(activitiesEntities);
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
        this.closeProgressDialog();

    }

    @Override
    public void showEmpty(String msg) {

    }

    @Override
    public void loadCompleted() {
        mAdapter.loadComplete();
        Snackbar.make(mToolbar, "无更多数据", Snackbar.LENGTH_LONG).show();
    }

//    @Override
//    public void setActionBarTitle(String titleText) {
//       // PA.getPresenter().getView().setTitle(titleText);
//      // mToolbar.setTitle(titleText)
//        getPresenter().getView().setActionBarTitle(titleText);
//    }
//
//    @Override
//    public void showActionBar() {
//      // getPresenter().getView().showActionBar();
//    }
//
//    @Override
//    public void setActionBarBackground(int color) {
//        getPresenter().getView().setActionBarBackground(color);
//    }


    @NonNull
    @Override
    public PastListAtyPresenter providePresenter() {
        return new PastListAtyPresenter();
    }

    /**
     *  自定义的分割线
     */

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                             RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildAdapterPosition(view) == 0) outRect.top = space;
        }
    }
}
