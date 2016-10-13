package com.i7676.qyclient.functions.main.gift.giftdetail;

import android.util.Log;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/9.
 */

public class GiftDetailPresenter extends BasePresenter<GiftDetailView> {

        //private String giftbid= new Test().getDetail().getBid();

    @Inject
    YNetApiService  mYnetApiService;


    @Override
    protected void onWakeUp() {
        super.onWakeUp();

        //从云端得到数据
        getGiftDetaildata();

    }

    public void getGiftDetaildata() {
        HashMap<String,String> params =  new HashMap<>();
        params.put("bid","50");
        params.put("token", QyClient.curUser.getToken());

//        mYnetApiService.getGiftDetail(params).subscribe(testReqResult -> {
//
//            testReqResult.getData().getDetail()
//
//        });
        mYnetApiService.getGiftDetail(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(testReqResult -> {
         if (testReqResult.getRet()== ServerConstans.SUCCESS){
//             Test.Gift gift = testReqResult.getData().getGift();
//             Log.e("11111",gift.toString());
//            Test.Detail detail = testReqResult.getData().getDetail();
//             String bid = detail.getBid();
//             Log.e("bid",bid.toString());
//             String catname = detail.getCatname();
//             int consume = detail.getConsume();
//             String icon = detail.getIcon();
//             String introduce = detail.getIntroduce();
//             int remain = detail.getRemain();
            getView().addGiftitem(testReqResult.getData());



         }else {
             throw  new NullPointerException( "error："+ testReqResult.getRet());
         }
//
        },(throwable -> {
            Log.e("111111","11111");
            Logger.e(">>> onError:" + throwable.getMessage());
        }),()->{
            Logger.i(">>> onComplement");
        });


//        mYnetApiService.getGiftDetail(params).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(request ->{
//                    if (request.getRet()== ServerConstans.SUCCESS){
//                           // return request.getData();
//                        Log.e("111111111",request.getData().toString());
//                      //  ReqResult<GiftDetailEntity> giftDetailEntity= JSON.parseObject(request,>(){
//                        /**
//                         * Javabean bean = JSON.parseObject(arg0.result,
//                         Javabean.class);
//                         Data data = bean.getData();
//                         com.example.fastjson.Javabean.Data.Detail detail = data
//                         .getDetail();
//                         String ss = detail.getGameId();
//
//                         tv.setText(ss);
//                         */
//                    GiftDetailEntity entity= JSON.parseObject(request.getData().toString(),GiftDetailEntity.class);
//
//////                    int  ss = entity.getGameId();
//////                        int gameId = entity.getGameId();
////                        String catname = entity.getCatname();
////                        int bid = entity.getBid();
////                        int consume = entity.getConsume();
//                        JSON.
//
//                        String jj   = entity.getCatname();
//                        Log.e("2222222",jj.toString()+"");
//                        }
//                       JSONObject job= JSON.parseObject(String.valueOf(request.getData().toString().equals("detail")));
//                        Log.e("22222",job.getString("catname"));
//                    else {
//                        throw  new NullPointerException( "error："+ request.getRet());
//                    }
//                });
//




        //




    }
}
