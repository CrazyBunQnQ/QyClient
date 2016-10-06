package com.i7676.qyclient.functions.main.home.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.BaseActivity;
import com.i7676.qyclient.functions.main.adapters.GameListAtyAdapter;
import com.i7676.qyclient.util.DensityUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HCol on 2016/10/5.
 */
@Layout(R.layout.activity_game_list) public class GameListActivity
    extends BaseActivity<GameListAtyPresenter, GameListAtyView> implements GameListAtyView {

    public static final String TAG_TYPE = "TAG_TYPE";
    public static final String TITLE_TEXT_TAG = "TITLE_TEXT_TAG";
    public static final int CATEGORY_TASK = 2;
    // Using if #CATEGORY_TASK
    public static final String CATEGORY_ID_TAG = "CATEGORY_ID_TAG";
    public static final int SEARCH_TASK = 3;
    // Using if #SEARCH_TASK
    public static final String SEARCH_KEYWORD_TAG = "SEARCH_KEYWORD_TAG";
    public static final String DEFAULT_TITLE_TEXT = "列表";

    public static Intent buildIntent(Context context, Bundle args) {
        final Intent mIntent = new Intent(context, GameListActivity.class);
        mIntent.putExtras(args);
        return mIntent;
    }

    private RecyclerView mRecyclerView;
    private GameListAtyAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyView;
    private Toolbar mToolbar;

    private GameListAtyComponent atyComponent;

    @Override public void initViews() {
        setupInject();

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbarLayout);
        mEmptyView = findViewById(R.id.mLayoutEmpty);

        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(clickAsPressBack);

        mAdapter = new GameListAtyAdapter(R.layout.item_game_list, new ArrayList<>());
        //mAdapter.setEmptyView(emptyView);
        mAdapter.setOnLoadMoreListener(getPresenter());

        mSwipeRefreshLayout.setOnRefreshListener(getPresenter());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtil.dip2px(this, 8.0f)));
    }

    private void setupInject() {
        atyComponent = DaggerGameListAtyComponent.builder()
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();

        atyComponent.inject(getPresenter());
    }

    @NonNull @Override public GameListAtyPresenter providePresenter() {
        return new GameListAtyPresenter(getIntent().getExtras());
    }

    @Override public void add2List(List<RankingGameEntity> gameEntities) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mAdapter.addData(gameEntities);
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(Boolean.FALSE);
    }

    @Override public void loadCompleted() {
        mAdapter.loadComplete();
        Snackbar.make(mToolbar, "无更多数据", Snackbar.LENGTH_LONG).show();
    }

    @Override public void clearList() {
        mAdapter.clear();
    }

    @Override public void setTitleText(String titleText) {
        mToolbar.setTitle(titleText);
    }

    @Override public void showDialog(String msg) {
        this.showProgressDialog(msg);
    }

    @Override public void closeDialog() {
        this.closeProgressDialog();
    }

    @Override public void showEmpty(String msg) {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(Boolean.FALSE);

        mRecyclerView.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(msg)) {
            TextView mErrorMsg = (TextView) mEmptyView.findViewById(R.id.tv_error_msg);
            mErrorMsg.setText(msg);
        }

        mEmptyView.setVisibility(View.VISIBLE);
    }

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
