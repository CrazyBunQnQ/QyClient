package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class GameHistoryAdapter extends BaseQuickAdapter<RankingGameEntity> {
    public GameHistoryAdapter(int layoutResId, List<RankingGameEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, RankingGameEntity gameEntity) {
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(gameEntity.getThumb(), null);
        baseViewHolder.setText(R.id.category_text, gameEntity.getCatname());
    }
}
