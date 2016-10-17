package com.i7676.qyclient.functions.main.hi;

import android.graphics.Color;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.ServerConstans;
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

public class HiFrPresenter extends BasePresenter<HiFrView>
    implements BaseQuickAdapter.RequestLoadMoreListener {

    @Inject YNetApiService mYNetApiService;
    private int page = 1;
    private int size = 10;

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

    @Override protected void onSleep() {
        page = 1;
        getView().cleanUpCards();
        super.onSleep();
    }

    private void requestDataFromClouds() {
        final HashMap<String, String> params = new HashMap<>();
        params.put("token", QyClient.curUser.getToken());
        params.put("page", page + "");
        params.put("size", size + "");
        mYNetApiService.getHiIndex(params).compose(RxUtil.networkTransform()).subscribe(
            // next
            cardEntities -> {
                getView().setupCards(cardEntities);
            },
            // error
            error -> {
                if (((ServerException) error).code == ServerConstans.RESPONSE_DATA_IS_NULL) {
                    getView().loadMoreCompleted();
                }
                // FIXME 需要统一提示
                getView().showToast(((ServerException) error).getMessage());
            },
            // completed
            () -> {
                Logger.e(">>> completed.");
            });
    }

    @Override public void onLoadMoreRequested() {
        page++;
        requestDataFromClouds();
    }
}
