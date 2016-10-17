package com.i7676.qyclient.functions.main.adapters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.HiCardEntity;
import com.i7676.qyclient.util.ValueMapper;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class HiCardAdapter extends BaseQuickAdapter<HiCardEntity> {

    public HiCardAdapter(int layoutResId, List<HiCardEntity> data) {
        super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, HiCardEntity hiCardEntity) {
        // 文本
        baseViewHolder.setText(R.id.tv_user_nick, hiCardEntity.getNickname())
            .setText(R.id.tv_msg_content, hiCardEntity.getContent())
            .setText(R.id.tv_date, ValueMapper.formatTimestamp(hiCardEntity.getCreat_dt()))
            .setText(R.id.tv_comments, "  " + hiCardEntity.getComnum())
            .setText(R.id.tv_likes, "  " + hiCardEntity.getLikenum());

        // 头像
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_avatar)).setImageUrlAndAuthorInfo(hiCardEntity.getAvatar(),
            null);

        // 图片资源解析
        List<String> tempImgs = new ArrayList<>();
        List<String> imgs = new ArrayList<>();
        try {
            final String imgsStr = hiCardEntity.getImg();
            tempImgs.addAll(JSONArray.parseArray(imgsStr, String.class));
            for (String img : tempImgs) {
                String temp = hiCardEntity.getImg_prefix() + img;
                imgs.add(temp);
            }
        } catch (Exception e) {
            Logger.e(">>> 图片解析异常..");
        }
        // 图片
        NonScrollableRecyclerView mRecyclerView =
            ((NonScrollableRecyclerView) baseViewHolder.getConvertView()
                .findViewById(R.id.rv_imgs));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(suitableLayoutManager(imgs.size()));
        mRecyclerView.setAdapter(new ImageAdapter(R.layout.item_hi_card_images, imgs));
    }

    private RecyclerView.LayoutManager suitableLayoutManager(int imgSize) {
        if (imgSize > 1 && imgSize <= 4) {
            return new GridLayoutManager(mContext, 2);
        } else if (imgSize > 4 && imgSize <= 9) {
            return new GridLayoutManager(mContext, 3);
        } else {
            // out of range   imgSize <=1 && imgSize > 9
            return new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        }
    }

    private class ImageAdapter extends BaseQuickAdapter<String> {

        ImageAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override protected void convert(BaseViewHolder baseViewHolder, String s) {
            ((AutoLoadImageView) baseViewHolder.getConvertView()).setImageUrlAndAuthorInfo(s, null);
        }
    }

    public void clear() {
        this.mData.clear();
        this.notifyDataSetChanged();
    }
}
