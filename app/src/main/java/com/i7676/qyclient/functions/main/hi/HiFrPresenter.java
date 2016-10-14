package com.i7676.qyclient.functions.main.hi;

import android.graphics.Color;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.exception.ServerException;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.rx.RxUtil;
import com.orhanobut.logger.Logger;
import java.util.HashMap;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/10/14.
 */

public class HiFrPresenter extends BasePresenter<HiFrView> {

    @Inject YNetApiService mYNetApiService;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        if (QyClient.curUser == null) {
            getView().showToast("请先登录.");
        } else {
            setupToolbar();
            requestDataFromClouds();
        }
    }

    private void setupToolbar() {
        getView().setTitleText("Hi动态");
        getView().showToolbar();
        getView().hideSearchView();
        getView().setToolbarBackgroundColor(Color.parseColor("#FF7F00"));
    }

    private void requestDataFromClouds() {
        final HashMap<String, String> params = new HashMap<>();
        mYNetApiService.getHiIndex(params).compose(RxUtil.networkTransform()).subscribe(
            // next
            cardEntities -> {
                getView().setupCards(cardEntities);
            },
            // error
            error -> {
                // FIXME 需要统一提示
                getView().showToast(((ServerException) error).getMessage());
            },
            // completed
            () -> {
                Logger.e(">>> completed.");
            });
    }
}
