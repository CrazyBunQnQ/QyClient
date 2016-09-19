package com.i7676.qyclient.functions.main.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.util.ValueMapper;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import java.util.List;

/**
 * Created by HCol on 2016/9/18.
 */
public class GameCardAdapter extends BaseQuickAdapter<GameCardEntity> {

  public void clear() {
    this.mData.clear();
  }

  private int innerListType = LinearLayoutManager.VERTICAL;
  private int innerListLayoutResId = R.layout.item_game_list_vertical;

  public GameCardAdapter(int layoutResId, List<GameCardEntity> data) {
    super(layoutResId, data);
  }

  @Override protected void convert(BaseViewHolder baseViewHolder, GameCardEntity gameCardEntity) {
    baseViewHolder.setText(R.id.tv_card_head_text, gameCardEntity.getType());
    renderGameList(baseViewHolder.convertView, gameCardEntity.getGameEntities());
  }

  private void renderGameList(View rootView, List<GameEntity> gameEntities) {
    NonScrollableRecyclerView gameList =
        (NonScrollableRecyclerView) rootView.findViewById(R.id.rv_card_content_list);
    gameList.setHasFixedSize(true);
    gameList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), innerListType, false));
    gameList.setAdapter(new ListAdapter(innerListLayoutResId, gameEntities));
  }

  /**
   * ListAdapter for inner game list
   */
  class ListAdapter extends BaseQuickAdapter<GameEntity> {

    public ListAdapter(int layoutResId, List<GameEntity> data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, GameEntity gameEntity) {
      // rank tag
      baseViewHolder.setText(R.id.tv_rank_tag, (baseViewHolder.getLayoutPosition() + 1) + "");
      // game logo
      ((AutoLoadImageView) baseViewHolder.getConvertView()
          .findViewById(R.id.img_game_logo)).setImageUrlAndAuthorInfo(gameEntity.getIcon(), null);
      // gift
      baseViewHolder.setVisible(R.id.img_game_gift_tag, true);
      // name
      baseViewHolder.setText(R.id.tv_game_text, gameEntity.getName());
      // info
      String gameInfo =
          gameEntity.getType() + " | " + ValueMapper.parsePayType(gameEntity.getPayType());
      baseViewHolder.setText(R.id.tv_game_info, gameInfo);
      // rank info
      TextView rankInfo = (TextView) baseViewHolder.convertView.findViewById(R.id.tv_rank_info);
      int resId;
      String color;
      switch (0) {
        // 无浮动
        case 0:
        default:
          color = "#757575";
          resId = R.drawable.ic_settings_ethernet_grey_600_18dp;
          break;
        // 上升
        case 1:
          color = "#E53935";
          resId = R.drawable.ic_arrow_upward_red_600_18dp;
          break;
        // 下降
        case 2:
          color = "#43A047";
          resId = R.drawable.ic_arrow_downward_green_600_18dp;
          break;
      }
      rankInfo.setText(String.valueOf(gameEntity.getPlayed()));
      rankInfo.setTextColor(Color.parseColor(color));
      Drawable rankInfoTag = mContext.getResources().getDrawable(resId);
      rankInfo.setCompoundDrawablesWithIntrinsicBounds(null, null, rankInfoTag, null);

      baseViewHolder.setText(R.id.btn_game_download, "开始");

      GotoGame gotoGame = new GotoGame(gameEntity.getUrl());

      // item 点击事件
      baseViewHolder.setOnClickListener(R.id.img_game_logo, gotoGame);
      baseViewHolder.setOnClickListener(R.id.tv_game_text, gotoGame);
      baseViewHolder.setOnClickListener(R.id.tv_game_info, gotoGame);
      baseViewHolder.setOnClickListener(R.id.btn_game_download, gotoGame);
    }
  }

  private class GotoGame implements View.OnClickListener {

    private String gameUrl;

    public GotoGame(String gameUrl) {
      this.gameUrl = gameUrl;
    }

    @Override public void onClick(View v) {
      //Bundle args = new Bundle();
      //args.putString(PlayGameActivity.GAME_URL, gameUrl);
      //startActivity(PlayGameActivity.buildIntent(getContext(), args));
    }
  }
}