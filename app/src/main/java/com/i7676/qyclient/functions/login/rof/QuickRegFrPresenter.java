package com.i7676.qyclient.functions.login.rof;

import android.accounts.NetworkErrorException;
import android.text.TextUtils;
import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.login.LoginConstants;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.orhanobut.logger.Logger;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/23.
 */

public class QuickRegFrPresenter extends BasePresenter<QuickRegFrView>
    implements View.OnClickListener {

    @Inject YNetApiService mYNetApiService;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        renderViews();
    }

    private void renderViews() {
        getView().setActionBarTitle("快速注册");
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_and_signIn:
                quickRegister();
                break;
        }
    }

    private void quickRegister() {
        String accountText = getView().getAccountText();
        String passwordText = getView().getPasswordText();
        String passwordConfirmText = getView().getPasswordConfirmText();

        // 数据校验
        if (TextUtils.isEmpty(accountText)) {
            getView().report2User("请输入账号信息");
            return;
        }
        if (TextUtils.isEmpty(passwordText)) {
            getView().report2User("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(passwordConfirmText)) {
            getView().report2User("请再次输入密码");
            return;
        }
        if (!passwordConfirmText.equals(passwordText)) {
            getView().report2User("两次密码不一致,请从新输入");
            return;
        }

        // 构造表单参数
        HashMap<String, String> params = new HashMap<>();
        params.put("username", accountText);
        params.put("password", passwordText);
        params.put("type", String.valueOf(LoginConstants.REGISTER_TYPE_QUICK));
        // 发送注册
        mYNetApiService.register(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultSubscriber<ReqResult<String>>() {
                @Override public void onNext(ReqResult<String> reqResult) {
                    if (reqResult.getRet() == 0) {
                        getView().report2User("注册成功,正在登陆中...");
                    } else {
                        onError(new NetworkErrorException(
                            "[" + reqResult.getRet() + "]" + reqResult.getData()));
                    }
                }

                @Override public void onError(Throwable e) {
                    getView().report2User("注册失败:" + e.getMessage());
                }

                @Override public void onCompleted() {
                    signInUp(accountText, passwordConfirmText);
                }
            });
    }

    private void signInUp(String accountText, String passwordConfirmText) {
        mYNetApiService.login(accountText, passwordConfirmText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultSubscriber<ReqResult<UserEntity>>() {
                @Override public void onCompleted() {
                    getView().signInSuccess();
                }

                @Override public void onError(Throwable e) {
                    if (e instanceof NetworkErrorException) {
                        getView().report2User(e.getMessage());
                    } else {
                        Logger.e(">>> " + e.getMessage());
                        getView().report2User("服务器异常，请稍后再试");
                    }
                }

                @Override public void onNext(ReqResult<UserEntity> reqResult) {
                    if (reqResult.getRet() == 0) {
                        QyClient.curUser = reqResult.getData();
                        Logger.i(QyClient.curUser.toString());
                    } else {
                        onError(
                            new NetworkErrorException("Request error[" + reqResult.getRet() + "]"));
                    }
                }
            });
    }
}
