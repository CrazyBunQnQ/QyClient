package com.i7676.qyclient.functions.main.home;

import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface HomeFrView extends BaseView {

    // 首页 Banner 区分位
    int BANNER_TYPE_H5 = 0;
    int BANNER_TYPE_APP = 1;
    int BANNER_TYPE_OTHERS = 2;

    void setupTopBanner(List<String> bannerImgURLs);

    void setupRCMDBanner(List<String> bannerImgURLs);

    void setupFstGCards(List<GameCardEntity> fstGCards);

    void setupSndGCards(List<GameCardEntity> sndGCards);

    void setupCategory(List<CategoryEntity> categoryEntities);

    void setupGameGrid(GameEntity gameEntity);

    void setActionBarTitle(String titleText);

    void setupUserPlayedHistory(List<RankingGameEntity> gameEntities);

    void showActionBar();

    void setActionBarBackground(int color);

    void setBottomBarIndex(int index);

    void showOptionsMenu();

    void showDialog2User(String msg);

    void toast2User(String msg);

    void closeDialog();

    void renderGameRanking(int showCategoryType, ArrayList<RankingGameEntity> data);

    void serverFuckedUp();

    void go2PlayH5Game(String url);
}
