package com.i7676.qyclient.functions.main.navigation;

import android.os.Bundle;
import android.util.SparseArray;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.MainAtyView;
import com.i7676.qyclient.functions.main.activity.ActivityFragment;
import com.i7676.qyclient.functions.main.gift.GiftFragment;

import com.i7676.qyclient.functions.main.hi.HiFragment;
import com.i7676.qyclient.functions.main.home.HomeFragment;
import com.i7676.qyclient.functions.main.home.list.GameListActivity;
import com.i7676.qyclient.functions.main.profile.ProfileFragment;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MainAtyNavigator {

    public static MainAtyNavigator create(MainActivity mAty) {
        return new MainAtyNavigator(mAty);
    }

    private MainActivity mAty;
    private BaseFragment selectedFragment;
    private int tabIndex = -1;
    private SparseArray<BaseFragment> frCached = new SparseArray<>(5);

    private MainAtyNavigator(MainActivity mAty) {
        this.mAty = mAty;
    }

    public void showHomeFr() {
        if (tabIndex == MainAtyView.TAB_INDEX_HOME) return;

        tabIndex = MainAtyView.TAB_INDEX_HOME;
        transform(tabIndex, selectedFragment =
            (frCached.get(tabIndex) != null ? frCached.get(tabIndex) : HomeFragment.create(null)));
    }

    public void showHi() {
        if (tabIndex == MainAtyView.TAB_INDEX_HI) return;

        tabIndex = MainAtyView.TAB_INDEX_HI;
        transform(tabIndex, selectedFragment =
            (frCached.get(tabIndex) != null ? frCached.get(tabIndex) : HiFragment.create(null)));
    }

    public void showActivityFr() {
        if (tabIndex == MainAtyView.TAB_INDEX_ACTIVITY) return;

        tabIndex = MainAtyView.TAB_INDEX_ACTIVITY;
        transform(tabIndex, selectedFragment =
            (frCached.get(tabIndex) != null ? frCached.get(tabIndex)
                : ActivityFragment.create(null)));
    }


    /**
     * 礼包
     */
    public void showGiftFr() {
        if (tabIndex == MainAtyView.TAB_INDEX_GIFT) return;

        tabIndex = MainAtyView.TAB_INDEX_GIFT;
        transform(tabIndex, selectedFragment =
            (frCached.get(tabIndex) != null ? frCached.get(tabIndex) : GiftFragment.create(null)));
    }

    public void showLoginAty() {
        mAty.startActivity(LoginActivity.buildIntent(mAty, null));
    }

    public void showGameList(Bundle args) {
        mAty.closePopupWindowIfOpening();
        mAty.startActivity(GameListActivity.buildIntent(mAty, args));
    }

    public void showProfileFr() {
        if (tabIndex == MainAtyView.TAB_INDEX_PROFILE) return;

        tabIndex = MainAtyView.TAB_INDEX_PROFILE;
        transform(tabIndex, selectedFragment =
            (frCached.get(tabIndex) != null ? frCached.get(tabIndex)
                : ProfileFragment.create(null)));
    }

    public void showSelectedFragment() {
        if (selectedFragment == null) {
            showHomeFr();
        } else {
            transform(tabIndex, selectedFragment);
        }
    }

    private void cacheFragment(int index, BaseFragment fragment) {
        if (frCached.get(index) == null) {
            frCached.put(index, fragment);
        }
    }

    private void transform(int index, BaseFragment fragment) {
        mAty.renderToolbarOptionMenus(index);
        //cacheFragment(index, fragment);
        mAty.getSupportFragmentManager()
            .beginTransaction()
            .replace(mAty.getFrPlaceHolderResId(), fragment, fragment.getClass().getCanonicalName())
            .commit();
    }
}
