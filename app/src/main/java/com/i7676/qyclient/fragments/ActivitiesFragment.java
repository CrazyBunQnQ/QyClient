package com.i7676.qyclient.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.BaseActivity;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
@Layout(R.layout.fragment_activities) public class ActivitiesFragment
    extends ToolbarInteractorFragment {

  private RecyclerView mRecyclerView;

  @Override protected void initView(View rootView) {
    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activities_recyclerView);
    mRecyclerView.setAdapter(
        new TestAdapter(getContext(), R.layout.item_activities_list, Arrays.asList("1", "2", "3")));
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }

  @Override protected void initHostToolbar() {
    super.initHostToolbar();
    mToolbarAgent.setTitleText("活  动");
  }

  @Override public void onResume() {
    super.onResume();
    ((BaseActivity) getActivity()).getBottomBar().selectTabAtPosition(1);
  }

  private class TestAdapter extends BaseQuickAdapter<String> {
    public TestAdapter(Context context, int layoutResId, List<String> data) {
      super(context, layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, String s) {

    }
  }
}
