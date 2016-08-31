package com.i7676.qyclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.i7676.qyclient.R;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.recker.flybanner.FlyBanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class GameFragment extends Fragment {

  private static final String TAG = GameFragment.class.getSimpleName();

  private static List<String> IMAGE_URLS = new ArrayList<String>() {
    {
      add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
      add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
      add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
    }
  };

  private NavigationTabStrip mNavigationTabStrip;
  private NonScrollableRecyclerView mGameList;
  private FlyBanner mBanner;
  private ObservableScrollView mScrollView;

  // toolbar 透明度计算
  private int transparentColor = 0x00FF6F00;// 16740096
  private int primaryColor = 0xFFFF6F00;// 4294930176
  private int totalOffset = 0x7F000000;
  private int offset = 0x1000000;
  private ToolbarAlphaHelper toolbarAlphaHelper;

  public void setToolbarAlphaHelper(ToolbarAlphaHelper toolbarAlphaHelper) {
    this.toolbarAlphaHelper = toolbarAlphaHelper;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_game, container, false);
    initViews(rootView);
    return rootView;
  }

  private void initViews(View rootView) {
    mNavigationTabStrip = (NavigationTabStrip) rootView.findViewById(R.id.nts);
    mNavigationTabStrip.setTitles("热力推荐", "最新上线");
    mNavigationTabStrip.setTabIndex(0);

    mBanner = (FlyBanner) rootView.findViewById(R.id.banner);
    mBanner.setImagesUrl(IMAGE_URLS);

    mGameList = (NonScrollableRecyclerView) rootView.findViewById(R.id.gameList);
    mGameList.setLayoutManager(new GridLayoutManager(getContext(), 2));
    ArrayList<GameEntity> games = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      games.add(new GameEntity("", "青游" + i, (i % 2 == 0 ? "网游" : "小游戏"), i + ""));
    }
    mGameList.setAdapter(new GameListAdapter(getContext(), R.layout.item_game_list, games));
    mGameList.setHasFixedSize(true);

    mScrollView = (ObservableScrollView) rootView.findViewById(R.id.rootScroll);
    mScrollView.smoothScrollTo(0, 0);
  }

  @Override public void onResume() {
    super.onResume();
    mScrollView.setmScrollChangedListener((l, r, oldl, oldr) -> {
      Log.d(TAG, "initViews: >>> {l:" + l + ",r:" + r + ",oldl:" + oldl + ",oldr:" + oldr + "}");

      if (toolbarAlphaHelper != null) {
        boolean scrollDown = r - oldr > 0;
        if (r <= 0) {
          toolbarAlphaHelper.setBgColor(transparentColor);
        } else if (r > 0 && r < 360) {
          float percent = r / 360f;
          int tempColor = new Float(totalOffset * percent).intValue();
          Log.d(TAG, "percent:" + percent + "%, tempColor:" + tempColor);
          // 下滑
          if (scrollDown) {
            toolbarAlphaHelper.setBgColor(transparentColor + calcColorOffset(tempColor, offset));
          }
          // 上拉
          else {
            toolbarAlphaHelper.setBgColor(transparentColor - calcColorOffset(tempColor, offset));
          }
        } else if (r >= 360) {
          toolbarAlphaHelper.setBgColor(primaryColor);
        }
      }
    });
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

  private class GameListAdapter extends BaseQuickAdapter<GameFragment.GameEntity> {

    public GameListAdapter(Context context, int layoutResId, List<GameEntity> data) {
      super(context, layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, GameEntity gameEntity) {
      baseViewHolder.setText(R.id.tv_game_online_count, gameEntity.getOnlineCount() + "w人在线");
      baseViewHolder.setText(R.id.tv_game_type, gameEntity.getGameType());
      baseViewHolder.setText(R.id.tv_game_name, gameEntity.getGameName());
    }
  }

  public static class GameEntity {
    private String ImageUrl;
    private String gameName;
    private String gameType;
    private String onlineCount;

    public GameEntity(String imageUrl, String gameName, String gameType, String onlineCount) {
      ImageUrl = imageUrl;
      this.gameName = gameName;
      this.gameType = gameType;
      this.onlineCount = onlineCount;
    }

    public String getImageUrl() {
      return ImageUrl;
    }

    public String getGameName() {
      return gameName;
    }

    public String getGameType() {
      return gameType;
    }

    public String getOnlineCount() {
      return onlineCount;
    }
  }

  public interface ToolbarAlphaHelper {
    void setBgColor(int color);
  }
}
