package com.i7676.qyclient.functions.main.hi;

import com.i7676.qyclient.entity.HiCardEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public interface HiFrView extends BaseView {

    void setupCards(List<HiCardEntity> cardEntities);

    void cleanUpCards();

    void go2Login();

    void loadMoreCompleted();

    void showToolbar();

    void showToast(String msg);

    void setTitleText(String titleText);

    void hideSearchView();

    void setToolbarBackgroundColor(int color);
}
