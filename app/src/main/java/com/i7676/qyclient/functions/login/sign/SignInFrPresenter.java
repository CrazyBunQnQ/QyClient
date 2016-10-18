package com.i7676.qyclient.functions.login.sign;

import android.accounts.NetworkErrorException;
import android.os.Handler;
import android.os.Message;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import com.i7676.qyclient.api.wechat.WXUserInfoResponse;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.login.sign.adapter.SignWayAdapter;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/20.
 */

public class SignInFrPresenter extends BasePresenter<SignInFrView>
    implements SignWayAdapter.FkItemClickListener {

    static final int SIGN_IN_WITH_QQ = 0;
    static final int SIGN_IN_WITH_WX = 1;
    static final int SIGN_IN_WITH_ZFB = 2;

    @Inject WXAPIEventHandlerImp wxapiEventHandlerImp;
    @Inject YNetApiService mYNetApiService;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        getView().setActionBarTitle("登录");
        getView().closeDialog();
        setupSignInWay();
    }

    private void setupSignInWay() {
        ArrayList<SignWayEntity> signWays = new ArrayList<SignWayEntity>() {
            {
                add(new SignWayEntity(R.drawable.ic_login_qq, "QQ登录", SIGN_IN_WITH_QQ));
                add(new SignWayEntity(R.drawable.ic_login_wx, "微信登录", SIGN_IN_WITH_WX));
                add(new SignWayEntity(R.drawable.ic_login_zfb, "支付宝登录", SIGN_IN_WITH_ZFB));
            }
        };
        getView().render3rdPartySignInWay(signWays);
    }

    @Override public void onItemClick(int position, SignWayEntity signWayEntity) {
        Logger.i(">>> [" + position + "] way 2: " + signWayEntity.getText());
        switch (signWayEntity.getType()) {
            case SIGN_IN_WITH_QQ:
                doSignInWithQQ();
                break;
            case SIGN_IN_WITH_WX:
                doSignInWithWx();
                break;
            case SIGN_IN_WITH_ZFB:
                doSignInWithZFB();
                break;
        }
    }

    private void doSignInWithQQ() {
    }

    private void doSignInWithWx() {
        getView().showDialog2User("加载中...");
        wxapiEventHandlerImp.setWXUserInfoCallback(mWXUserInfoCallback);
        wxapiEventHandlerImp.loginAndRegister();
    }

    private void doSignInWithZFB() {
    }

    private WXAPIEventHandlerImp.NetCallback<WXUserInfoResponse> mWXUserInfoCallback =
        new WXAPIEventHandlerImp.NetCallback<WXUserInfoResponse>() {
            @Override public void onResponse(WXUserInfoResponse wxUserInfoResponse) {
                Logger.i(">>> " + wxUserInfoResponse);
                UIHandler.sendEmptyMessage(DIALOG_TAG_WX);
                // 向服务端发送微信用户信息
                mYNetApiService.wxSignIn(wxUserInfoResponse.openid, wxUserInfoResponse.nickname,
                    wxUserInfoResponse.headimgurl)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultSubscriber<ReqResult<UserEntity>>() {
                        @Override public void onCompleted() {
                            getView().storeUser(QyClient.curUser);
                            getView().signUpSuccess();
                        }

                        @Override public void onError(Throwable e) {
                            if (e instanceof NetworkErrorException) {
                                getView().signUpFailed(e.getMessage());
                            } else {
                                Logger.e(">>> " + e.getMessage());
                                getView().signUpFailed("微信登录失败,请稍后再试...");
                            }
                        }

                        @Override public void onNext(ReqResult<UserEntity> reqResult) {
                            if (reqResult.getRet() == 0) {
                                QyClient.curUser = reqResult.getData();
                                Logger.i(QyClient.curUser.toString());
                            } else {
                                onError(new NetworkErrorException(
                                    "Request error[" + reqResult.getRet() + "]"));
                            }
                        }
                    });
            }

            @Override public void onFailure(Throwable e) {
                Logger.i(">>> " + e.getMessage());
                UIHandler.sendEmptyMessage(DIALOG_TAG_CLOSE);
            }
        };

    private static final int DIALOG_TAG_WX = 0;
    private static final int DIALOG_TAG_CLOSE = 1;

    private Handler UIHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DIALOG_TAG_WX:
                    getView().showDialog2User("微信一键登录成功,请稍后...");
                    break;
                case DIALOG_TAG_CLOSE:
                    getView().closeDialog();
                    break;
            }
        }
    };
}
