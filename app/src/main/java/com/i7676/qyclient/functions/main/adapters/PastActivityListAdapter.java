package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

public class PastActivityListAdapter  extends BaseQuickAdapter<ActivitiesEntity> {
    public PastActivityListAdapter(int layoutResId, List<ActivitiesEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }
    public void clear() {
        getData().clear();
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ActivitiesEntity activitiesEntity) {
//        baseViewHolder.setText(R.id.tv_game_name, gameEntity.getCatname())
//                .setText(R.id.tv_played_count, gameEntity.getGameSize() + " 人在玩");
//
//        ((AutoLoadImageView) baseViewHolder.getConvertView()
//                .findViewById(R.id.img_game_logo)).setImageUrlAndAuthorInfo(gameEntity.getGameIco(),
//                null);
//
//        GotoGame gotoGame = new GotoGame(mContext, gameEntity.getHref());
//
//        baseViewHolder.setOnClickListener(R.id.img_game_logo, gotoGame)
//                .setOnClickListener(R.id.btn_start_game, gotoGame);
        baseViewHolder.setText(R.id.activity_starttime,activitiesEntity.getStarttime());
        baseViewHolder.setText(R.id.activity_endtime,activitiesEntity.getEndtime());
        baseViewHolder.setText(R.id.activity_title,activitiesEntity.getTitle());

        ((AutoLoadImageView)baseViewHolder.getConvertView()
                .findViewById(R.id.activity_logo)).setImageUrlAndAuthorInfo(activitiesEntity.getThumb(),
                null);


    }
}