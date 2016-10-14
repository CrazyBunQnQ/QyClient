package com.i7676.qyclient.functions.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.home.list.GameListActivity;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import com.roughike.bottombar.OnTabSelectListener;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MainAtyPresenter extends BasePresenter<MainAtyView>
    implements OnTabSelectListener, SearchView.OnQueryTextListener {

    @Inject YNetApiService mYNetApiService;
    @Inject MainAtyNavigator navigator;

    // Activity的生命周期被Presenter来接管了
    @Override protected void onCreate() {
        super.onCreate();
    }

    @Override protected void onWakeUp() {
        super.onWakeUp();
        //reqCategoryData();
        getView().clearFocus();
        navigator.showSelectedFragment();
    }

    private void reqCategoryData() {
        mYNetApiService.getCategory()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map(reqResult -> {
                if (reqResult.getRet() == 0) {
                    return reqResult.getData();
                } else {
                    throw new NullPointerException("ERROR: MainAtyPresenter#reqCategoryData");
                }
            })
            .subscribe(getView()::setupCategoryPopupWindow);
    }

    @Override public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.bottom_home:
                navigator.showHomeFr();
                break;
            case R.id.bottom_activities:
                navigator.showActivityFr();
                break;
            case R.id.bottom_hi:
                navigator.showHi();
                break;
            case R.id._bottom_gifts:
                navigator.showGiftFr();
                break;
            case R.id.bottom_profile:
                navigator.showProfileFr();
                break;
        }
    }

    @Override public boolean onQueryTextSubmit(String query) {
        if (TextUtils.isEmpty(query)) {
            query = MainActivity.DEFAULT_QUERY_TEXT;
        }

        final Bundle args = new Bundle();
        args.putString(GameListActivity.TITLE_TEXT_TAG, "搜索结果");
        args.putInt(GameListActivity.TAG_TYPE, GameListActivity.SEARCH_TASK);
        args.putString(GameListActivity.SEARCH_KEYWORD_TAG, query);
        showGameListAty(args);
        return true;
    }

    public void showGameListAty(Bundle args) {
        navigator.showGameList(args);
    }

    @Override public boolean onQueryTextChange(String newText) {
        return false;
    }
}
