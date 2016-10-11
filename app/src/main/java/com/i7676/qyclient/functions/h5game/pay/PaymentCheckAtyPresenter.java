package com.i7676.qyclient.functions.h5game.pay;

import android.os.Bundle;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.WftUnifiedResponseEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.orhanobut.logger.Logger;
import com.switfpass.pay.MainApplication;
import com.switfpass.pay.bean.RequestMsg;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/10.
 */

class PaymentCheckAtyPresenter extends BasePresenter<PaymentCheckAtyView> {

    private Bundle args;
    @Inject YNetApiService mYNetApiService;
    private boolean payFlag = true;

    void resetPayFlag() {
        payFlag = true;
    }

    PaymentCheckAtyPresenter(Bundle args) {
        this.args = args;
    }

    @Override protected void onWakeUp() {

        Logger.e(">>> PaymentCheckAtyPresenter::onWakeUp");

        super.onWakeUp();
        if (payFlag) {
            payFlag = false;
            makePreOrder();
        } else {
            getView().payResults("付完了！！！！");
        }
    }

    @Override protected void onCreate() {

        Logger.e(">>> PaymentCheckAtyPresenter::onCreate");

        super.onCreate();
    }

    @Override protected void onDestroy() {

        Logger.e(">>> PaymentCheckAtyPresenter::onDestroy");

        super.onDestroy();
    }

    @Override protected void onSleep() {

        Logger.e(">>> PaymentCheckAtyPresenter::onSleep");

        super.onSleep();
    }

    /**
     * 向服务器端发起威富通统一预下单请求,待服务器端完成预下单之后，直接唤醒 native 支付
     */
    private void makePreOrder() {
        getView().loadProgressDialog("加载中...");
        final HashMap<String, String> params = new HashMap<>();
        final Set<String> keys = args.keySet();
        final Iterator<String> it = keys.iterator();
        for (; it.hasNext(); ) {
            String target = it.next();
            params.put(target, (args.get(target) == null ? "" : args.get(target).toString()));
        }
        mYNetApiService.postWFTUnified(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultSubscriber<ReqResult<WftUnifiedResponseEntity>>() {

                @Override public void onError(Throwable e) {
                    super.onError(e);
                    getView().payResults("威富通支付失败: 请求统一下单接口失败.");
                }

                @Override public void onCompleted() {
                    super.onCompleted();
                }

                @Override public void onNext(ReqResult<WftUnifiedResponseEntity> response) {
                    super.onNext(response);
                    if (response.getRet() == ServerConstans.SUCCESS) {
                        final RequestMsg msg = new RequestMsg();
                        msg.setTokenId(response.getData().getToken_id());
                        msg.setTradeType(MainApplication.PAY_WX_WAP);
                        msg.setOutTradeNo(response.getData().getTransno());
                        getView().go2Payment(msg);
                    } else {
                        getView().payResults("威富通支付失败: 请求统一下单接口失败.");
                    }
                }
            });
    }
}
