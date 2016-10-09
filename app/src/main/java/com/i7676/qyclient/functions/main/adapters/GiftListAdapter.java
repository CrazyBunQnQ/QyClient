package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.GiftEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public class GiftListAdapter  extends BaseQuickAdapter<GiftEntity> {

    private BaseViewHolder butGift_get;


    public GiftListAdapter(int layoutResId, List<GiftEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }
    public void clear() {
        getData().clear();
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GiftEntity giftEntity) {
        baseViewHolder.setText(R.id.tv_gift_name,giftEntity.getBname());
        baseViewHolder.setText(R.id.tv_gift_introduce,giftEntity.getIntroduce());
      //  baseViewHolder.setText(R.id.activity_title,activitiesEntity.getTitle());

        ((AutoLoadImageView)baseViewHolder.getConvertView()
                .findViewById(R.id.img_gift_logo)).setImageUrlAndAuthorInfo(giftEntity.getIcon(),
                null);
//        baseViewHolder.setOnItemClickListener(R.id.gift_btnGet, (parent, view, position, id) -> {
//            if (QyClient.curUser == null) {
//                ((MainActivity) mContext).showLogin();
//                return;
//            }
//
//
//        });

    baseViewHolder.addOnClickListener(R.id.gift_btnGet);





    }

}
