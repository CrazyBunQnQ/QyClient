package com.i7676.qyclient.functions.main.activity.detail.childFragment.detaialfragment;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/18.
 */

public class ActivityDetailPresenter  extends BasePresenter<ActivityDetailView> {


    @Inject
    YNetApiService mYNetApiService;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();
        initDetailData();

    }

    private void initDetailData() {

        Map<String,String> params= new HashMap<>();
        params.put("actid","29");
        params.put("token", QyClient.curUser.getToken());

        mYNetApiService.getActyivityDetail(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(activtyresult ->{
                    if (activtyresult.getRet()== ServerConstans.SUCCESS){
                        Logger.e(">>>> next"+ activtyresult.getData().toString());
                        //   getView().getDetailFragment(activtyresult.getData().toString());
                        getView().getTextdetail(activtyresult.getData().getDescription());
                        getView().goActivity(activtyresult.getData().getHref());
                        Logger.e(">>>");

                    }
                },(throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                }),()->{
                    Logger.e(">>>>compelete");
                });


    }
}
