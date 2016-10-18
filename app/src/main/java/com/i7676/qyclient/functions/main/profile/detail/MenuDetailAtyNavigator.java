package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import com.i7676.qyclient.functions.BaseFragment;
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
        transform(AccountFragment.create(null));
    }

    void showFriendsFra() {
        transform(FriendsFragment.create(null));
    }

    void showRechargeFra() {
        transform(RechargeFragment.create(null));
    }

    void showTelBind() {
        transform(TelBindFragment.create(null));
    }

    private void transform(BaseFragment fragment) {
        mAty.getSupportFragmentManager().beginTransaction()
            // 添加Fragment
            .replace(mAty.getFrPlaceHolder(), fragment, fragment.getClass().getSimpleName())
            // 提交
            .commit();
    }
}
