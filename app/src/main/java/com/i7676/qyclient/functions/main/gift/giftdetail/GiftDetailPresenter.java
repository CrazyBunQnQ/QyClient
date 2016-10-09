package com.i7676.qyclient.functions.main.gift.giftdetail;

import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/10/9.
 */

public class GiftDetailPresenter extends BasePresenter<GiftDetailView> {



    @Inject
    YNetApiService  mYnetApiService;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();

        //从云端得到数据
        getGiftDetaildata();

    }

    public void getGiftDetaildata() {



        //




    }
}
