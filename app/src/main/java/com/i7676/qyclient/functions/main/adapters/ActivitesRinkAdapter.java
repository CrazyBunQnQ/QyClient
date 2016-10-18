package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.RankingActyEnty;
import com.i7676.qyclient.widgets.AutoLoadImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23.
 */

public class ActivitesRinkAdapter extends BaseQuickAdapter<RankingActyEnty>{
    public ActivitesRinkAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public void clear() {
        getData().clear();
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankingActyEnty rankingActyEnty) {
        baseViewHolder.setText(R.id.tv_ranking_nick, rankingActyEnty.getName());
      baseViewHolder.setText(R.id.tv_ranking_position, rankingActyEnty.getSortid());
         baseViewHolder .setText(R.id.tv_ranking_score,rankingActyEnty.getStrval());

        ((AutoLoadImageView) baseViewHolder.getConvertView()
                .findViewById(R.id.iv_rankong_icon)).setImageUrlAndAuthorInfo(rankingActyEnty.getAvatar(),
                null);

    }
}
