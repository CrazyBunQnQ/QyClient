package com.i7676.qyclient.function.main.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.function.main.BaseActivity;
import com.i7676.qyclient.function.h5game.PlayGameActivity;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.function.main.entity.GameEntity;
import com.i7676.qyclient.function.main.presenter.GamePresenter;
import com.i7676.qyclient.function.main.view.GameView;
import com.i7676.qyclient.util.ValueMapper;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.orhanobut.logger.Logger;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
@Layout(R.layout.fragment_game) public class GameFragment
    extends BaseFragment<GamePresenter, GameView> implements GameView {

  private static final String TAG = GameFragment.class.getSimpleName();

  private static List<String> IMAGE_URLS = new ArrayList<String>() {
    {
      add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
      add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
      add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
    }
  };

  private View rootView;
  private FlyBanner mBanner;
  private ObservableScrollView mScrollView;

  // 最新上线，网络游戏，小游戏，专题游戏
  private NonScrollableRecyclerView categoryList;
  private NonScrollableRecyclerView contentList;

  // toolbar 透明度计算
  private int transparentColor = 0x00FF6F00;// 16740096
  private int primaryColor = 0xFFFF6F00;// 4294930176
  private int totalOffset = 0x7F000000;
  private int offset = 0x1000000;

  @Override protected void initView(View rootView) {
    this.rootView = rootView;

    mBanner = (FlyBanner) rootView.findViewById(R.id.banner);
    mBanner.setImagesUrl(IMAGE_URLS);

    mScrollView = (ObservableScrollView) rootView.findViewById(R.id.rootScroll);
    mScrollView.smoothScrollTo(0, 0);

    initCategoryList();
    initContentList();
  }

  @NonNull @Override public GamePresenter providePresenter() {
    return new GamePresenter(getContext());
  }

  @Override public void initViews() {

  }

  //********************************************************************** Start of category

  private class CategoryEntity {
    private String imageURL;
    private String categoryText;

    public CategoryEntity(String imageURL, String categoryText) {
      this.imageURL = imageURL;
      this.categoryText = categoryText;
    }

    public String getImageURL() {
      return imageURL;
    }

    public String getCategoryText() {
      return categoryText;
    }
  }

  private ArrayList<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>() {
    {
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", "最新上线"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", "网络游戏"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", "小游戏"));
      add(new CategoryEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", "专题游戏"));
    }
  };

  private void initCategoryList() {
    categoryList = (NonScrollableRecyclerView) rootView.findViewById(R.id.categoryList);
    categoryList.setLayoutManager(new GridLayoutManager(getContext(), 4));
    categoryList.setHasFixedSize(true);
    categoryList.setAdapter(new CategoryAdapter(R.layout.item_game_category, categoryEntities));
  }

  private class CategoryAdapter extends BaseQuickAdapter<CategoryEntity> {

    public CategoryAdapter(int layoutResId, List<CategoryEntity> data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, CategoryEntity categoryEntity) {
      ((AutoLoadImageView) baseViewHolder.getConvertView()
          .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(categoryEntity.getImageURL(),
          null);
      baseViewHolder.setText(R.id.category_text, categoryEntity.getCategoryText());
    }
  }

  //********************************************************************** End of category

  //********************************************************************** Start of GameCard

  @Override public void renderGameCards(final List<GameEntity> gameEntities) {

    if (gameEntities == null || gameEntities.size() <= 0) return;

    fakeCards.clear();

    final String cards[] = { "最新上线", "热门小游戏", "热门网络游戏" };

    for (int i = 0; i < cards.length; i++) {
      final int index = i;
      GameCardEntity gameCardEntity = new GameCardEntity(cards[i], new ArrayList<GameEntity>() {
        {
          for (int j = index * 5; j < (index + 1) * 5; j++) {
            add(gameEntities.get(j));
          }
        }
      });
      fakeCards.add(gameCardEntity);
    }
    mGameCardAdapter.notifyDataSetChanged();
  }

  private List<GameCardEntity> fakeCards = new ArrayList<>();
  private GameCardAdapter mGameCardAdapter;

  private class GameCardEntity {
    private String type;
    private List<GameEntity> gameEntities;

    public GameCardEntity(String type, List<GameEntity> gameEntities) {
      this.type = type;
      this.gameEntities = gameEntities;
    }

    public List<GameEntity> getGameEntities() {
      return gameEntities;
    }

    public String getType() {
      return type;
    }
  }

  private void initContentList() {
    contentList = (NonScrollableRecyclerView) rootView.findViewById(R.id.contentList);
    contentList.setHasFixedSize(true);
    contentList.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    mGameCardAdapter = new GameCardAdapter(R.layout.item_game_card, fakeCards);
    contentList.setAdapter(mGameCardAdapter);

    mScrollView.setmScrollChangedListener((l, r, oldl, oldr) -> {
      Logger.i(">>> {l:" + l + ",r:" + r + ",oldl:" + oldl + ",oldr:" + oldr + "}");
      if (mToolbarAgent != null) {
        boolean scrollDown = r - oldr > 0;
        if (r <= 0) {
          mToolbarAgent.setBgColor(transparentColor);
        } else if (r > 0 && r < 360) {
          float percent = r / 360f;
          int tempColor = new Float(totalOffset * percent).intValue();
          Log.d(TAG, "percent:" + percent + "%, tempColor:" + tempColor);
          // 下滑
          if (scrollDown) {
            mToolbarAgent.setBgColor(transparentColor + calcColorOffset(tempColor, offset));
          }
          // 上拉
          else {
            mToolbarAgent.setBgColor(transparentColor - calcColorOffset(tempColor, offset));
          }
        } else if (r >= 360) {
          mToolbarAgent.setBgColor(primaryColor);
        }
      }
    });
  }

  private class GameCardAdapter extends BaseQuickAdapter<GameCardEntity> {

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
      gameList.setLayoutManager(new LinearLayoutManager(this.mContext, innerListType, false));
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
  }

  private class GotoGame implements View.OnClickListener {

    private String gameUrl;

    public GotoGame(String gameUrl) {
      this.gameUrl = gameUrl;
    }

    @Override public void onClick(View v) {
      Bundle args = new Bundle();
      args.putString(PlayGameActivity.GAME_URL, gameUrl);
      startActivity(PlayGameActivity.buildIntent(getContext(), args));
    }
  }

  //********************************************************************** End of GameCard

  @Override protected void initHostToolbar() {
    super.initHostToolbar();
    mToolbarAgent.setTitleText("主  页");
    mToolbarAgent.setBgColor(transparentColor);
  }

  @Override protected void inject() {
    ((QyClient) (getActivity().getApplication())).getComponent().inject(getPresenter());
  }

  @Override public void onResume() {
    super.onResume();
    ((BaseActivity) getActivity()).getBottomBar().selectTabAtPosition(0);
  }

  /**
   * 四舍五入运算
   *
   * @param tempColor 被整除的数
   * @param mod 模
   */
  private int calcColorOffset(int tempColor, int mod) {
    // 进位标识
    int carryFlag = 0;
    // 余数
    int x = tempColor % mod;
    // 余数占模的百分比
    float modPercent = new Float(x) / mod;
    // 5入
    if (modPercent > 0.5f) {
      ++carryFlag;
    }
    return ((tempColor / mod) + carryFlag) * mod;
  }
}
