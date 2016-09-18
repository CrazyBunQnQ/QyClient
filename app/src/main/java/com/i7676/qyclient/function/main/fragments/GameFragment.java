package com.i7676.qyclient.function.main.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.constants.ColorConstants;
import com.i7676.qyclient.function.main.adapters.CategoryAdapter;
import com.i7676.qyclient.function.main.adapters.GameCardAdapter;
import com.i7676.qyclient.function.main.entity.GameCardEntity;
import com.i7676.qyclient.function.main.entity.GameEntity;
import com.i7676.qyclient.function.main.presenter.GamePresenter;
import com.i7676.qyclient.function.main.view.GameView;
import com.i7676.qyclient.net.EgretApiService;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.orhanobut.logger.Logger;
import com.recker.flybanner.FlyBanner;
import com.roughike.bottombar.BottomBar;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/8/30.
 */
@Layout(R.layout.fragment_game) public class GameFragment
    extends BaseFragment<GamePresenter, GameView> implements GameView {

  public static GameFragment create() {
    final GameFragment gameFragment = new GameFragment();
    return gameFragment;
  }

  private FlyBanner mBanner;
  private ObservableScrollView mScrollView;

  @Inject EgretApiService mEgretApiService;
  @Inject Toolbar mToolbar;
  @Inject BottomBar bottomBar;
  @Inject CategoryAdapter categoryAdapter;
  @Inject GameCardAdapter mGameCardAdapter;
  private NonScrollableRecyclerView categoryList;
  private NonScrollableRecyclerView contentList;

  @Override public void initViews(View rootView) {
    mBanner = (FlyBanner) rootView.findViewById(R.id.banner);

    mScrollView = (ObservableScrollView) rootView.findViewById(R.id.rootScroll);
    mScrollView.smoothScrollTo(0, 0);
    mScrollView.setmScrollChangedListener(new GameFragmentScrollChangedListener(mToolbar));

    categoryList = (NonScrollableRecyclerView) rootView.findViewById(R.id.categoryList);
    categoryList.setLayoutManager(new GridLayoutManager(getContext(), 4));
    categoryList.setHasFixedSize(true);
    categoryList.setAdapter(categoryAdapter);

    contentList = (NonScrollableRecyclerView) rootView.findViewById(R.id.contentList);
    contentList.setHasFixedSize(true);
    contentList.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    contentList.setAdapter(mGameCardAdapter);
  }

  @Override public void renderBanner(List<String> imgURLs) {
    mBanner.setImagesUrl(imgURLs);
  }

  @Override public void renderGameCards(List<GameEntity> gameEntities) {
    if (gameEntities == null || gameEntities.size() <= 0) return;
    mGameCardAdapter.clear();
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
      mGameCardAdapter.add(index, gameCardEntity);
    }
    mGameCardAdapter.notifyDataSetChanged();
  }

  @NonNull @Override public GamePresenter providePresenter() {
    return new GamePresenter(mEgretApiService, getContext(), mToolbar, bottomBar);
  }

  public static class GameFragmentScrollChangedListener
      implements ObservableScrollView.OnScrollChangedListener {
    private Toolbar mToolbar;

    public GameFragmentScrollChangedListener(Toolbar mToolbar) {
      this.mToolbar = mToolbar;
    }

    @Override public void onScrollChanged(int l, int r, int oldl, int oldr) {
      Logger.i(">>> {l:" + l + ",r:" + r + ",oldl:" + oldl + ",oldr:" + oldr + "}");
      if (mToolbar != null) {
        boolean scrollDown = r - oldr > 0;
        if (r <= 0) {
          mToolbar.setBackgroundColor(ColorConstants.TRANSPARENT);
        } else if (r > 0 && r < 360) {
          float percent = r / 360f;
          int tempColor = Float.valueOf(ColorConstants.TOTAL_OFFSET * percent).intValue();
          //Log.d(TAG, "percent:" + percent + "%, tempColor:" + tempColor);
          // 下滑
          if (scrollDown) {
            mToolbar.setBackgroundColor(
                ColorConstants.TRANSPARENT + calcColorOffset(tempColor, ColorConstants.OFFSET));
          }
          // 上拉
          else {
            mToolbar.setBackgroundColor(
                ColorConstants.TRANSPARENT - calcColorOffset(tempColor, ColorConstants.OFFSET));
          }
        } else if (r >= 360) {
          mToolbar.setBackgroundColor(ColorConstants.PRIMARY_COLOR);
        }
      }
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
      float modPercent = x / mod;
      // 5入
      if (modPercent > 0.5f) {
        ++carryFlag;
      }
      return ((tempColor / mod) + carryFlag) * mod;
    }
  }
}
