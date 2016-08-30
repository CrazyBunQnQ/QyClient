package com.i7676.qyclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.i7676.qyclient.R;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.recker.flybanner.FlyBanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class GameFragment extends Fragment {

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

    ((ScrollView) rootView.findViewById(R.id.rootScroll)).smoothScrollTo(0, 0);
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
}
