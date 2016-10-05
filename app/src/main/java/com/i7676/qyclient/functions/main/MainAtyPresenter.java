package com.i7676.qyclient.functions.main;

import android.support.annotation.IdRes;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
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
                break;
            case R.id._bottom_gifts:
                break;
            case R.id.bottom_profile:
                navigator.showProfileFr();
                break;
        }
    }

    @Override public boolean onQueryTextSubmit(String query) {
        if (TextUtils.isEmpty(query)) return false;
        navigator.goSearch(query);
        return true;
    }

    @Override public boolean onQueryTextChange(String newText) {
        return false;
    }
}
