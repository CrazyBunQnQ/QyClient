package com.i7676.qyclient.functions.main.hi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.HiCardEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.HiCardAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

@Layout(R.layout.fragment_hi) public class HiFragment extends BaseFragment<HiFrPresenter, HiFrView>
    implements HiFrView {

    public static HiFragment create(Bundle args) {
        final HiFragment fragment = new HiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private HiCardAdapter mAdapter;

    @Override protected void initRootViews(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new HiCardAdapter(R.layout.item_hi, new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override protected void setupInject() {
        super.setupInject();
        ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public HiFrPresenter providePresenter() {
        return new HiFrPresenter();
    }

    @Override public void showHiOptions() {

    }

    @Override public void setupCards(List<HiCardEntity> cardEntities) {
        mAdapter.addData(cardEntities);
    }

    @Override public void go2Login() {
        ((MainActivity) getActivity()).showLogin();
    }

    @Override public void showToast(String msg) {
        ((MainActivity) getActivity()).toast2User(msg, Toast.LENGTH_SHORT);
    }

    @Override public void setTitleText(String titleText) {
        ((MainActivity) getActivity()).setTitle(titleText);
    }

    @Override public void hideSearchView() {
        ((MainActivity) getActivity()).hideSearchView();
    }

    @Override public void setToolbarBackgroundColor(int color) {
        ((MainActivity) getActivity()).setToolbarBkg(color);
    }

    @Override public void showToolbar() {
        ((MainActivity) getActivity()).showActionBar();
    }
}
