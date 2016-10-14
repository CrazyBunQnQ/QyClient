package com.i7676.qyclient.functions;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * Created by Administrator on 2016/10/14.
 */

public interface OneHasToolbarAtyView extends BaseView {

    void showToolbar();

    void hideToolbar();

    //void showOptionMenus();
    //
    //void hideOptionMenus();
    //
    //void showOptionMenuById(@MenuRes int menuId);
    //
    //void hideOptionMenuById(@MenuRes int menuId);
    //
    //void showSearchView();
    //
    //void hideSearchView();

    void setTitleText(String titleText);

    void setupToolbarPrimaryColor();

    void setToolbarBackgroundColor(int color);

    void setTitleTextColor(@ColorInt int color);

    void setupToolbarIconAndEvent(@DrawableRes int drawableRes, View.OnClickListener clickListener);
}
