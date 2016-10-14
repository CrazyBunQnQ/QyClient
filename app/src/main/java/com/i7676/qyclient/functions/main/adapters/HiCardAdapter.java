package com.i7676.qyclient.functions.main.adapters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.HiCardEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
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
            .setText(R.id.tv_date, hiCardEntity.getCreat_dt())
            .setText(R.id.tv_comments, "  " + hiCardEntity.getComnum())
            .setText(R.id.tv_likes, "  " + hiCardEntity.getLikenum());

        // 头像
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_avatar)).setImageUrlAndAuthorInfo(hiCardEntity.getAvatar(),
            null);

        // 图片
        NonScrollableRecyclerView mRecyclerView =
            ((NonScrollableRecyclerView) baseViewHolder.getConvertView()
                .findViewById(R.id.rv_imgs));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(suitableLayoutManager(3));
        mRecyclerView.setAdapter(
            new ImageAdapter(R.layout.item_hi_card_images, new ArrayList<String>()));
    }

    private RecyclerView.LayoutManager suitableLayoutManager(int imgSize) {
        if (imgSize == 1) {
            return new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        } else if (imgSize > 1 && imgSize <= 4) {
            return new GridLayoutManager(mContext, 2);
        } else if (imgSize > 4 && imgSize <= 9) {
            return new GridLayoutManager(mContext, 3);
        } else {
            // Otherwise   imgSize > 9
            return null;
        }
    }

    private class ImageAdapter extends BaseQuickAdapter<String> {

        public ImageAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override protected void convert(BaseViewHolder baseViewHolder, String s) {
            ((AutoLoadImageView) baseViewHolder.getConvertView()).setImageUrlAndAuthorInfo(s, null);
        }
    }
}
