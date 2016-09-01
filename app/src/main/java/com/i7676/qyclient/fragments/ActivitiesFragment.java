package com.i7676.qyclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.interfaces.ToolbarAgent;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ActivitiesFragment extends BaseFragment {

  private RecyclerView mRecyclerView;

  private ToolbarAgent mToolbarAgent;

  public void registerToolbarAgent(ToolbarAgent mToolbarAgent) {
    this.mToolbarAgent = mToolbarAgent;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_activities, container, false);
    initView(rootView);
    return rootView;
  }

  private void initView(View rootView) {
    if (mToolbarAgent != null) {
      mToolbarAgent.setTitleText("活  动");
      mToolbarAgent.setBgColor(0xFFFF6F00);
    }
    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activities_recyclerView);
    mRecyclerView.setAdapter(
        new TestAdapter(getContext(), R.layout.item_activities_list, Arrays.asList("1", "2", "3")));
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }

  private class TestAdapter extends BaseQuickAdapter<String> {
    public TestAdapter(Context context, int layoutResId, List<String> data) {
      super(context, layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, String s) {

    }
  }
}
