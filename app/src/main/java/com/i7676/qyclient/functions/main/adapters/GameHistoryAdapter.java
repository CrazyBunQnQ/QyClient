package com.i7676.qyclient.functions.main.adapters;

import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.h5game.PlayGameActivity;
import com.i7676.qyclient.functions.main.MainActivity;
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
            .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(gameEntity.getGameIco(),
            null);
        baseViewHolder.setText(R.id.category_text, gameEntity.getCatname());

        baseViewHolder.setOnClickListener(R.id.category_img, new GotoGame(gameEntity.getHref()));
    }

    private class GotoGame implements View.OnClickListener {

        private String gameUrl;

        GotoGame(String gameUrl) {
            this.gameUrl = gameUrl;
        }

        @Override public void onClick(View v) {
            if (QyClient.curUser == null) {
                ((MainActivity) mContext).showLogin();
                return;
            }

            Bundle args = new Bundle();
            args.putString(PlayGameActivity.GAME_URL,
                gameUrl + "&token=" + QyClient.curUser.getToken());
            mContext.startActivity(PlayGameActivity.buildIntent(mContext, args));
        }
    }
}
