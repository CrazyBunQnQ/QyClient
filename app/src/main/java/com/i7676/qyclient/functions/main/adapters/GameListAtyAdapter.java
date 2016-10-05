package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

/**
 * Created by HCol on 2016/10/5.
 */

public class GameListAtyAdapter extends BaseQuickAdapter<GameEntity> {
    public GameListAtyAdapter(int layoutResId, List<GameEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }

    public void clear() {
        getData().clear();
        notifyDataSetChanged();
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, GameEntity gameEntity) {
        baseViewHolder.setText(R.id.tv_game_name, gameEntity.getName())
            .setText(R.id.tv_played, gameEntity.getPlayed());

        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_game_logo)).setImageUrlAndAuthorInfo(gameEntity.getIcon(), null);
    }
}
