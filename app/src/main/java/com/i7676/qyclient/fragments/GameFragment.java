package com.i7676.qyclient.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.BaseActivity;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
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
@Layout(R.layout.fragment_game) public class GameFragment extends ToolbarInteractorFragment {

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

  private ArrayList<GameCardEntity> fakeCards = new ArrayList<GameCardEntity>() {
    {
      add(new GameCardEntity("最新上线", new ArrayList<GameEntity>() {
        {
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", false, "诛仙", "角色",
              "399.6M", "81521", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", true, "天堂2：血盟",
              "角色", "443.8M", "75210", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", true, "校花的贴身高手",
              "角色", "201.8M", "73958", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", true, "口袋妖怪重制",
              "角色", "206.6M", "73722", 0x00000001));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", true, "迷城物语",
              "角色", "175.1M", "73687", 0x00000002));
        }
      }));
      add(new GameCardEntity("热门网络游戏", new ArrayList<GameEntity>() {
        {
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", false, "诛仙", "角色",
              "399.6M", "81521", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", true, "天堂2：血盟",
              "角色", "443.8M", "75210", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", true, "校花的贴身高手",
              "角色", "201.8M", "73958", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", true, "口袋妖怪重制",
              "角色", "206.6M", "73722", 0x00000001));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", true, "迷城物语",
              "角色", "175.1M", "73687", 0x00000002));
        }
      }));
      add(new GameCardEntity("热门小游戏", new ArrayList<GameEntity>() {
        {
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", false, "诛仙", "角色",
              "399.6M", "81521", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132435.gif", true, "天堂2：血盟",
              "角色", "443.8M", "75210", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132444.gif", true, "校花的贴身高手",
              "角色", "201.8M", "73958", 0x00000000));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132433.gif", true, "口袋妖怪重制",
              "角色", "206.6M", "73722", 0x00000001));
          add(new GameEntity("http://cdn-img.easyicon.net/png/11324/1132448.gif", true, "迷城物语",
              "角色", "175.1M", "73687", 0x00000002));
        }
      }));
    }
  };

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

  private class GameEntity {
    // logo
    private String logoURL;
    // 礼物标签{true:有,false:没有}
    private boolean hasGift;
    // 游戏名称
    private String name;
    // 游戏类别
    private String category;
    // 游戏文件大小
    private String fileSize;
    // 游戏排名数值
    private String rankNum;
    // 0:无浮动,1:上升,2:下降
    private int rankFlag;

    public GameEntity(String logoURL, boolean hasGift, String name, String category,
        String fileSize, String rankNum, int rankFlag) {
      this.logoURL = logoURL;
      this.hasGift = hasGift;
      this.name = name;
      this.category = category;
      this.fileSize = fileSize;
      this.rankNum = rankNum;
      this.rankFlag = rankFlag;
    }

    public String getLogoURL() {
      return logoURL;
    }

    public boolean hasGift() {
      return hasGift;
    }

    public String getName() {
      return name;
    }

    public String getCategory() {
      return category;
    }

    public String getFileSize() {
      return fileSize;
    }

    public String getRankNum() {
      return rankNum;
    }

    public int getRankFlag() {
      return rankFlag;
    }
  }

  private void initContentList() {
    contentList = (NonScrollableRecyclerView) rootView.findViewById(R.id.contentList);
    contentList.setHasFixedSize(true);
    contentList.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    contentList.setAdapter(new GameCardAdapter(R.layout.item_game_card, fakeCards));

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
            .findViewById(R.id.img_game_logo)).setImageUrlAndAuthorInfo(gameEntity.getLogoURL(),
            null);
        // gift
        baseViewHolder.setVisible(R.id.img_game_gift_tag, gameEntity.hasGift());
        // name
        baseViewHolder.setText(R.id.tv_game_text, gameEntity.getName());
        // info
        String gameInfo = gameEntity.getCategory() + " | " + gameEntity.getFileSize();
        baseViewHolder.setText(R.id.tv_game_info, gameInfo);
        // rank info
        TextView rankInfo = (TextView) baseViewHolder.convertView.findViewById(R.id.tv_rank_info);
        int resId;
        String color;
        switch (gameEntity.getRankFlag()) {
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
        rankInfo.setText(gameEntity.getRankNum());
        rankInfo.setTextColor(Color.parseColor(color));
        Drawable rankInfoTag = mContext.getResources().getDrawable(resId);
        rankInfo.setCompoundDrawablesWithIntrinsicBounds(null, null, rankInfoTag, null);
      }
    }
  }

  //********************************************************************** End of GameCard

  @Override protected void initHostToolbar() {
    super.initHostToolbar();
    mToolbarAgent.setTitleText("主  页");
    mToolbarAgent.setBgColor(transparentColor);
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
