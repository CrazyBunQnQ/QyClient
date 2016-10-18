package com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.childfragment.pastonlistfragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.RankingActyEnty;
import com.i7676.qyclient.functions.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/17.
 */

public class OnListPresenter  extends BasePresenter<OnListView>  implements BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener {


    @Inject
    YNetApiService mYnetApiService;
    private int pageNum = 1;
    private int pageSize = 10;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();
        initOnListData();
    }

    private void initOnListData() {
        Map<String, String> params = new HashMap<>();
        params.put("gid", 56 + "");
        params.put("actid", 29 + "");
        params.put("page", pageNum + "");
        params.put("size", pageSize + "");

        mYnetApiService.getActyivityRanking(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(req -> {
                            if (req.getRet() == ServerConstans.SUCCESS) {
                                Logger.e("<<<<<req", "req" + req.getData().toString());
                                List<RankingActyEnty> data = JSON.parseArray(req.getData().toString(), RankingActyEnty.class);
                                getView().addlist(data);
                                Logger.e("data", "" + data.toString());
                            } else if (req.getRet() == ServerConstans.RESPONSE_DATA_IS_NULL) {
                                Logger.e("<<<<<req", "数据为空");
                            }


                        }, throwable -> {
                            Logger.e(">>> onError:" + throwable.getMessage());

                        }
                        , () -> {
                            // oncomplete

                        });
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
