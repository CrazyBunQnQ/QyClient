package com.i7676.qyclient.functions.main.activity;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.MainAtyView;
import com.i7676.qyclient.util.ColorConstants;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.i7676.qyclient.api.ServerConstans.RESPONSE_DATA_IS_NULL;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ActivityFrPresenter extends BasePresenter<ActivityFrView> {

    @Inject YNetApiService mYNetApiService;
    private Subscription actySubscription;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        toolbarSetup();
        initActivityFrData();


    }

    @Override
    protected void onSleep() {
        super.onSleep();
        doUnsubscribe(actySubscription);
    }

    private void initActivityFrData() {
        //        Observable.from(new String[] { "1", "2", "3" })
        //            .observeOn(AndroidSchedulers.mainThread())
        //            .toList()
        //            .subscribe(getView()::setupActivityData);

        actySubscription=  mYNetApiService.getCurrentAcitivyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(request -> {
                if (request.getRet() == 0) {
                    List<ActivitiesEntity> data =
                        JSONArray.parseArray(request.getData().toString(), ActivitiesEntity.class);
                    getView().setupActivityData(data);
                } else if (request.getRet() == RESPONSE_DATA_IS_NULL) {
                    //getView().showEnty(request.getData().toString()+"" );
                    Log.e("1111111", request.toString());
                }
            });
    }

    private void toolbarSetup() {
        getView().showActionBar();
        getView().setActionBarTitle("活动");
        getView().setActionBarBackground(ColorConstants.PRIMARY_COLOR);
        getView().setBottomBarIndex(MainAtyView.TAB_INDEX_ACTIVITY);
        getView().hideSearchView();
    }
}
