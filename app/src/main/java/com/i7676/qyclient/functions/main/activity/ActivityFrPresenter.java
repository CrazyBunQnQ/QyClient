package com.i7676.qyclient.functions.main.activity;

import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.MainAtyView;
import com.i7676.qyclient.util.ColorConstants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ActivityFrPresenter extends BasePresenter<ActivityFrView> {

    @Override protected void onWakeUp() {
        super.onWakeUp();
        toolbarSetup();

        initActivityFrData();
    }

    private void initActivityFrData() {
        Observable.from(new String[] { "1", "2", "3" })
            .observeOn(AndroidSchedulers.mainThread())
            .toList()
            .subscribe(getView()::setupActivityData);
    }

    private void toolbarSetup() {
        getView().showActionBar();
        getView().setActionBarTitle("活动");
        getView().setActionBarBackground(ColorConstants.PRIMARY_COLOR);
        getView().setBottomBarIndex(MainAtyView.TAB_INDEX_ACTIVITY);
        getView().hideOptionsMenu();
    }
}
