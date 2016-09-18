package com.i7676.qyclient.function.main.fragments;

import android.support.annotation.NonNull;
import android.view.View;

import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.function.main.presenter.ActivitiesPresenter;
import com.i7676.qyclient.function.main.view.ActivitiesView;

/**
 * Created by Administrator on 2016/9/1.
 */
@Layout(R.layout.fragment_activities)
public class ActivitiesFragment
        extends BaseFragment<ActivitiesPresenter, ActivitiesView> implements ActivitiesView {
    @Override
    public void renderHistories(String item) {

    }

    @Override
    public void initViews(View rootView) {

    }

    @NonNull
    @Override
    public ActivitiesPresenter providePresenter() {
        return null;
    }

//    @NonNull
//    @Override
//    public ActivitiesPresenter providePresenter() {
//        return new ActivitiesPresenter();
//    }
//
//    @Override
//    protected void inject() {
//
//    }
//
//    @Override
//    public void renderHistories(String item) {
//        if (!mAdapter.getData().contains(item)) mAdapter.add(0, item);
//    }
//
//    private RecyclerView mRecyclerView;
//    private TestAdapter mAdapter;
//
//    @Override
//    protected void initHostToolbar() {
//        super.initHostToolbar();
//        mToolbarAgent.setTitleText("活  动");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        ((BaseActivity) getActivity()).getBottomBar().selectTabAtPosition(1);
//        mToolbarAgent.opMenuVisibility(false);
//    }
//
//    @Override
//    public void initViews(View rootView) {
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activities_recyclerView);
//        mAdapter = new TestAdapter(R.layout.item_activities_list, null);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(
//                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//    }
//
//    private class TestAdapter extends BaseQuickAdapter<String> {
//        public TestAdapter(int layoutResId, List<String> data) {
//            super(layoutResId, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder baseViewHolder, String s) {
//
//        }
//    }
}
