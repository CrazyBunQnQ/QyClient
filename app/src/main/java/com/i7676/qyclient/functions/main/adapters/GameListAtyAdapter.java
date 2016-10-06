package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

/**
 * Created by HCol on 2016/10/5.
 */

public class GameListAtyAdapter extends BaseQuickAdapter<RankingGameEntity> {
    public GameListAtyAdapter(int layoutResId, List<RankingGameEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }

    public void clear() {
        getData().clear();
        notifyDataSetChanged();
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, RankingGameEntity gameEntity) {
        baseViewHolder.setText(R.id.tv_game_name, gameEntity.getCatname())
            .setText(R.id.tv_played_count, gameEntity.getGameSize() + " 人在玩");

        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_game_logo)).setImageUrlAndAuthorInfo(gameEntity.getGameIco(),
            null);

        GotoGame gotoGame = new GotoGame(mContext, gameEntity.getHref());

        baseViewHolder.setOnClickListener(R.id.img_game_logo, gotoGame)
            .setOnClickListener(R.id.btn_start_game, gotoGame);
    }
}
