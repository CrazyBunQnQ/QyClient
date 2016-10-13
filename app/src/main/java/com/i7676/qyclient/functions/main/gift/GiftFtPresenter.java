package com.i7676.qyclient.functions.main.gift;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.GiftEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/8.
 */

public class GiftFtPresenter  extends BasePresenter<GiftFrView> implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    private int pageNum = 1;
    private int pageSize = 10;
    private Bundle args;



    @Inject
    YNetApiService mYNetApiService;

    protected void onWakeUp() {
        super.onWakeUp();
        initGiftData();
        initGiftGet();
    }

    private void initGiftGet() {

        if (QyClient.curUser == null){


              return;

        }else {
        HashMap<String,String> params2= new HashMap<>();
        params2.put("bid","50");
        params2.put("token", QyClient.curUser.getToken());
        mYNetApiService.getGiftDetail(params2).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        requestString ->{
                            if (requestString.getRet()==ServerConstans.SUCCESS){
                                Logger.e(">>> on restring:"+requestString.getData());
                            }
                        },(throwable -> {
                            Logger.e(">>> onError:" + throwable.getMessage());
                        }),()->{

                        }


                );
    }}

    private void initGiftData() {


        HashMap<String, String> params = new HashMap<>();
        params.put("size", pageSize + "");
        params.put("page", pageNum + "");

        mYNetApiService.getGiftList(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(requst -> {
                    if (requst.getRet() == 0) {
                        List<GiftEntity> data = JSONArray.parseArray(requst.getData().toString(), GiftEntity.class);
                        getView().addList(data);
                    } else if (requst.getRet() == ServerConstans.RESPONSE_DATA_IS_NULL) {
                        getView().loadCompleted();
                    }
                });
    }


    @Override
    public void onLoadMoreRequested() {
        pageNum++;
      initGiftData();
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        getView().clearList();
      // initGiftData();
    }
}


