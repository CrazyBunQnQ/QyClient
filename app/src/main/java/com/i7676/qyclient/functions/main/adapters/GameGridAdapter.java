package com.i7676.qyclient.functions.main.adapters;

import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.h5game.PlayGameActivity;
import com.i7676.qyclient.util.ValueMapper;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */

public class GameGridAdapter extends BaseQuickAdapter<RankingGameEntity> {
    public GameGridAdapter(int layoutResId, List<RankingGameEntity> data) {
        super(layoutResId, data);
        openLoadAnimation(SLIDEIN_BOTTOM);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, RankingGameEntity gameEntity) {
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_game_logo)).setImageUrlAndAuthorInfo(gameEntity.getGameIco(),
            null);
        baseViewHolder.setText(R.id.tv_game_name, gameEntity.getCatname())
            .setText(R.id.tv_game_type, ValueMapper.parseIsWebGame(gameEntity.getIswebgame()))
            .setText(R.id.tv_game_online_count, "|  " + gameEntity.getGameSize() + "人");

        GotoGame mGotoGame = new GotoGame(gameEntity.getHref());

        baseViewHolder.setOnClickListener(R.id.img_game_logo, mGotoGame);
        baseViewHolder.setOnClickListener(R.id.btn_game_start, mGotoGame);
    }

    private class GotoGame implements View.OnClickListener {

        private String gameUrl;

        GotoGame(String gameUrl) {
            this.gameUrl = gameUrl;
        }

        @Override public void onClick(View v) {
            Bundle args = new Bundle();
            args.putString(PlayGameActivity.GAME_URL, gameUrl);
            mContext.startActivity(PlayGameActivity.buildIntent(mContext, args));
        }
    }
}
