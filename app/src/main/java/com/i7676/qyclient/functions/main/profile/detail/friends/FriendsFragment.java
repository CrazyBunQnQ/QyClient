package com.i7676.qyclient.functions.main.profile.detail.friends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.FriendEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.adapters.FriendsAdapter;
import com.i7676.qyclient.functions.main.profile.detail.MenuDetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
@Layout(R.layout.fragment_friends) public class FriendsFragment
    extends BaseFragment<FriendsFraPresenter, FriendsFraView> implements FriendsFraView {

    public static FriendsFragment create(@Nullable Bundle args) {
        final FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private EditText mSearchInput;
    private ImageButton mSearchSubmit;
    private RecyclerView mRecyclerView;
    private TextView mFriendsCounter;
    private FriendsAdapter mAdapter;

    @Override protected void initRootViews(View rootView) {

        getActivity().setTitle("我的好友");

        mFriendsCounter = (TextView) rootView.findViewById(R.id.tv_friends_counter);
        mSearchInput = (EditText) rootView.findViewById(R.id.search_input);
        mSearchSubmit = (ImageButton) rootView.findViewById(R.id.search_submit);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);

        mSearchInput.setHint("请输入玩家用户或昵称");

        mAdapter = new FriendsAdapter(R.layout.item_friends, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setHasFixedSize(true);

        mSearchSubmit.setOnClickListener(getPresenter());
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }

    @Override public void showSearchResult() {

    }

    @Override public void setupFriends(List<FriendEntity> friendEntities) {
        mFriendsCounter.setText("您当前有 " + friendEntities.size() + " 位好友: ");
        mAdapter.setNewData(friendEntities);
    }

    @Override protected void setupInject() {
        super.setupInject();
        ((MenuDetailActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public FriendsFraPresenter providePresenter() {
        return new FriendsFraPresenter();
    }

    @Override public String getInputText() {
        return mSearchInput.getText().toString();
    }

    @Override public void showToast(String msg) {
        ((MenuDetailActivity) getActivity()).msg2User(msg);
    }
}
