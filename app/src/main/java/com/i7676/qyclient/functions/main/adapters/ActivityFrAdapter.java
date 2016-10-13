package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;

import java.util.List;

public class ActivityFrAdapter extends BaseQuickAdapter<ActivitiesEntity> {


  public ActivityFrAdapter(int layoutResId, List<ActivitiesEntity> data) {
    super(layoutResId, data);
  }

  @Override
  protected void convert(BaseViewHolder baseViewHolder, ActivitiesEntity activitiesEntity) {
    baseViewHolder.setText(R.id.activity_title,activitiesEntity.getTitle());
    baseViewHolder.setText(R.id.activity_starttime,activitiesEntity.getStarttime());
    baseViewHolder.setText(R.id.activity_endtime,activitiesEntity.getEndtime());
    ((AutoLoadImageView)baseViewHolder.getConvertView()
            .findViewById(R.id.activity_logo)).setImageUrlAndAuthorInfo(activitiesEntity.getThumb(),
            null);
    baseViewHolder.addOnClickListener(R.id.btn_activity_detail);
    // 点击跳转活动详情


  }
}