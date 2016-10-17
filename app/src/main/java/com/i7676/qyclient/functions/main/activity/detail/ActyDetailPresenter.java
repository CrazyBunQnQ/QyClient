package com.i7676.qyclient.functions.main.activity.detail;

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

public class ActyDetailPresenter extends BasePresenter<ActyDetailView> {

    @Inject
    YNetApiService mYNetApiService;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();

        onAcitivityDetailData(); // 加载活动详情界面的数据

    }

    private void onAcitivityDetailData() {

        Map<String,String> params= new HashMap<>();
        params.put("actid","29");
        params.put("token", QyClient.curUser.getToken());

        mYNetApiService.getActyivityDetail(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(activtyresult ->{
                    if (activtyresult.getRet()== ServerConstans.SUCCESS){
                       Logger.e(">>>> next"+ activtyresult.getData().toString());
                     //   getView().getDetailFragment(activtyresult.getData().toString());

                    }
                },(throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                }),()->{
                    Logger.e(">>>>compelete");
                });


    }
}
