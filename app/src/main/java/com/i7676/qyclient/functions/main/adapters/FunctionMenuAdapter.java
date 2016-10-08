package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.ProfileMenuEntity;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public class FunctionMenuAdapter extends BaseQuickAdapter<ProfileMenuEntity> {
    public FunctionMenuAdapter(int layoutResId, List<ProfileMenuEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProfileMenuEntity profileMenuEntity) {
        if (!profileMenuEntity.isAvailable()) {
            baseViewHolder.setVisible(R.id.img_available, true)
                .setVisible(R.id.tv_menu_desc, true)
                .setVisible(R.id.tv_menu_desc1, false)
                .setVisible(R.id.img_guide, false)
                .setImageResource(R.id.img_menu_icon, profileMenuEntity.getIcon())
                .setText(R.id.tv_menu_desc, profileMenuEntity.getDesc())
                .setText(R.id.tv_menu_name, profileMenuEntity.getName());
        } else {
            baseViewHolder.setVisible(R.id.img_available, false)
                .setVisible(R.id.tv_menu_desc, false)
                .setVisible(R.id.tv_menu_desc1, true)
                .setVisible(R.id.img_guide, true)
                .setImageResource(R.id.img_menu_icon, profileMenuEntity.getIcon())
                .setText(R.id.tv_menu_desc1, profileMenuEntity.getDesc1())
                .setText(R.id.tv_menu_name, profileMenuEntity.getName());
        }
    }
}
