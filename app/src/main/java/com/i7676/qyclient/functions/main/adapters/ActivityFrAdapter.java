package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import java.util.List;

public class ActivityFrAdapter extends BaseQuickAdapter<String> {
  public ActivityFrAdapter(int layoutResId, List<String> data) {
    super(layoutResId, data);
  }

  @Override protected void convert(BaseViewHolder baseViewHolder, String s) {
    baseViewHolder.setText(R.id.activity_title, "[" + s + "] 六角碎片积分送豪礼");
  }
}