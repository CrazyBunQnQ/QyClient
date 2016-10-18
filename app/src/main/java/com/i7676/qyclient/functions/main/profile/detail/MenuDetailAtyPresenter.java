package com.i7676.qyclient.functions.main.profile.detail;

import android.os.Bundle;
import com.i7676.qyclient.functions.OneHasToolbarAtyPresenter;
import com.i7676.qyclient.functions.main.profile.ProfileConstants;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MenuDetailAtyPresenter extends OneHasToolbarAtyPresenter<MenuDetailAtyView> {
    private MenuDetailAtyNavigator navigator;
    private Bundle args;

    MenuDetailAtyPresenter(Bundle args, MenuDetailAtyNavigator navigator) {
        this.navigator = navigator;
        this.args = args;
    }

    @Override protected void onWakeUp() {
        super.onWakeUp();
        if (args == null) {
            Logger.e(">>> fucked up.");
        } else {
            initWakeUp();
        }
    }

    private void initWakeUp() {
        final int showTag = args.getInt(MenuDetailActivity.SHOW_TAG);
        switch (showTag) {
            case ProfileConstants.MENU_ACCOUNT:
                navigator.showAccountFra();
                break;
            case ProfileConstants.MENU_FRIENDS:
                navigator.showFriendsFra();
                break;
            case ProfileConstants.MENU_RECHARGE:
                //navigator.showRechargeFra();
                break;
            case ProfileConstants.MENU_TEL_BIND:
                navigator.showTelBind();
                break;
            default:
                Logger.e(">>> fucked up AGAIN :) smart dude.");
                break;
        }
    }
}
