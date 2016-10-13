package com.i7676.qyclient.functions.main.gift;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.GiftEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.GiftListAdapter;
import com.i7676.qyclient.functions.main.gift.giftdetail.GiftDetailActivity;
import com.i7676.qyclient.widgets.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
@Layout(R.layout.fragment_gifts)
public class GiftFragment extends BaseFragment<GiftFtPresenter,GiftFrView> implements  GiftFrView {



    public static GiftFragment create(@Nullable Bundle args) {
        final GiftFragment fragment = new GiftFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private RecyclerView mRecyclerView;
    private GiftListAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    List<GiftEntity>list;


    @Override
    protected void initRootViews(View rootView) {
        initInject();


        mRecyclerView= (RecyclerView)rootView.findViewById(R.id.rv_gift_list);
        mAdapter= new GiftListAdapter(R.layout.item_gifts_list,new ArrayList<>());
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.gift_swipeResh);
       // mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        mAdapter.setOnLoadMoreListener(getPresenter());

        mSwipeRefreshLayout.setOnRefreshListener(getPresenter());

        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(SpacesItemDecoration(DensityUtil.dip2px(ge, 8.0f)));
       // mRecyclerView.addItemDecoration(new GameListActivity.SpacesItemDecoration(DensityUtil.dip2px(, 8.0f)));
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                if (QyClient.curUser == null){

                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                    return;
                }
              else{
                     //  判断领取礼包



                    // 跳转到 详情界面
                        Toast.makeText(getActivity(), "你好啊", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), GiftDetailActivity.class);
                       intent.putExtra("bid",list.get(i).getBid());
                        startActivity(intent);
                    }

            }
        });



    }

    private void initInject() {
        ((MainActivity) getActivity()).getAtyComponent().inject(this);
        ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @Override
    public void addList(List<GiftEntity>giftEntities) {
        mRecyclerView.setVisibility(View.VISIBLE);
        //mEmptyView.setVisibility(View.GONE);

        mAdapter.addData(giftEntities);
        list= giftEntities;
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
    public void loadCompleted() {
        mAdapter.loadComplete();

    }

    @Override
    public void setbutton() {

    }

    @NonNull
    @Override
    public GiftFtPresenter providePresenter() {
        return new GiftFtPresenter();
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
