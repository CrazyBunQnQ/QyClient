package com.i7676.qyclient.functions.main.activity.pastactivity;

import android.support.v4.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/8.
 */

class PastListAtyPresenter  extends BasePresenter<PastListAtyView> implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @Inject
    YNetApiService mYNetApiService;
    @Override
    protected void onWakeUp() {
        super.onWakeUp();
        //toolbarSetup();
        getDataFromsupport();


    }

    private void getDataFromsupport() {
        /**
         * 进行数据的请求
         */
        mYNetApiService.getActivityList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(-> {
//                    if (reqResult.getRet()== 0){
//                       // List<ActivitiesEntity> data= JSONArray.parseArray(listReqResult.getData().toString(),ActivitiesEntity.class);
////                        userPlayedHistory = mYNetApiService.getUserPlayedGames(params)
////                                .subscribeOn(Schedulers.io())
////                                .observeOn(AndroidSchedulers.mainThread())
////                                .map(reqResult -> {
////                                    if (reqResult.getRet() == 0) {
////                                        return reqResult.getData();
////                                    } else {
////                                        throw new NullPointerException();
////                                    }
////                                })
//                       return reqResult.getData();
//
//                    }else if (reqResult.getRet()== ServerConstans.RESPONSE_DATA_IS_NULL){
//                        getView().loadCompleted();
//                    }
//                });
        .subscribe(reqResult -> {
            if (reqResult.getRet()==0){
                List<ActivitiesEntity> data= JSONArray.parseArray(reqResult.getData().toString(),ActivitiesEntity.class);
                getView().addList(data);
            }else if (reqResult.getRet()== ServerConstans.RESPONSE_DATA_IS_NULL) {
                        getView().loadCompleted();

            }
        });

    }

    @Override
    public void onRefresh() {
        getDataFromsupport();

    }

    @Override
    public void onLoadMoreRequested() {

    }

//    private void toolbarSetup() {
//        getView().showActionBar();
//        getView().setActionBarTitle("活动");
//        getView().setActionBarBackground(ColorConstants.PRIMARY_COLOR);
//
//    }
}
