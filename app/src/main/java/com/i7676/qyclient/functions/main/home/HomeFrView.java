package com.i7676.qyclient.functions.main.home;

import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface HomeFrView extends BaseView {

    void setupTopBanner(List<String> bannerImgURLs);

    void setupRCMDBanner(List<String> bannerImgURLs);

    void setupFstGCards(List<GameCardEntity> fstGCards);

    void setupSndGCards(List<GameCardEntity> sndGCards);

    void setupCategory(List<CategoryEntity> categoryEntities);

    void setActionBarTitle(String titleText);

    void showActionBar();

    void setActionBarBackground(int color);

    void setBottomBarIndex(int index);

    void showOptionsMenu();

    void showDialog2User(String msg);

    void closeDialog();
}
