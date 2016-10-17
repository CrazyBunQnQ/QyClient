package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.profile.ProfileConstants;
import com.i7676.qyclient.functions.main.profile.detail.account.AccountFragment;
import com.i7676.qyclient.functions.main.profile.detail.friends.FriendsFragment;
import com.i7676.qyclient.functions.main.profile.detail.recharge.RechargeFragment;
import com.i7676.qyclient.functions.main.profile.detail.telbind.TelBindFragment;

/**
 * Created by Administrator on 2016/10/8.
 */

class MenuDetailAtyNavigator {

    private MenuDetailActivity mAty;

    MenuDetailAtyNavigator(Context context) {
        this.mAty = (MenuDetailActivity) context;
    }

    void showAccountFra() {
        transform(ProfileConstants.MENU_ACCOUNT, AccountFragment.create(null));
    }

    void showFriendsFra() {
        transform(ProfileConstants.MENU_FRIENDS, FriendsFragment.create(null));
    }

    void showRechargeFra() {
        transform(ProfileConstants.MENU_RECHARGE, RechargeFragment.create(null));
    }

    void showTelBind() {
        transform(ProfileConstants.MENU_TEL_BIND, TelBindFragment.create(null));
    }

    private void transform(int menuId, BaseFragment fragment) {
        mAty.renderToolbarOptionMenus(menuId);
        mAty.getSupportFragmentManager().beginTransaction()
            // 添加Fragment
            .replace(mAty.getFrPlaceHolder(), fragment, fragment.getClass().getSimpleName())
            // 提交
            .commit();
    }
}
