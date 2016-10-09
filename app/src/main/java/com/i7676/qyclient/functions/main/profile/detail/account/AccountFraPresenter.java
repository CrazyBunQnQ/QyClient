package com.i7676.qyclient.functions.main.profile.detail.account;

import android.text.TextUtils;
import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/8.
 */

public class AccountFraPresenter extends BasePresenter<AccountFraView>
    implements View.OnClickListener {

    @Inject YNetApiService mYNetApiService;

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeNickName:
                getView().showChangeNickNameView();
                break;
            case R.id.changePassword:
                getView().showChangePasswordView();
                break;
            case R.id.btn_submit_mdfNk:
                doModifyNickname();
                break;
            case R.id.btn_submit_mdfPwd:
                doModifyPassword();
                break;
        }
    }

    private void doModifyNickname() {
        String nicknameText = getView().getNicknameText();
        if (TextUtils.isEmpty(nicknameText)) {
            getView().msg2User("昵称不能为空");
            return;
        }

        mYNetApiService.modifyNickname(QyClient.curUser.getToken(), nicknameText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // next
                response -> {
                    getView().msg2User(response.getData());
                    if (response.getRet() == ServerConstans.SUCCESS) {
                        QyClient.curUser.setNickname(nicknameText);
                        getView().showNickname(nicknameText);
                        getView().modifyLocalAccountInfo(QyClient.curUser);
                    }
                },
                // error
                throwable -> {
                    getView().msg2User("网络错误，请稍后再试");
                    getView().dismissDialog();
                },
                // completed
                () -> {
                    getView().dismissDialog();
                });
    }

    private void doModifyPassword() {
        String originPwd = getView().getOriginPasswordText();
        String newPwd = getView().getNewPasswordText();
        String newPwdConfirmed = getView().getNewPasswordConfirmedText();

        if (TextUtils.isEmpty(originPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(
            newPwdConfirmed)) {
            getView().msg2User("请输入完整信息再提交");
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("newpass", newPwdConfirmed);
        params.put("oldpass", originPwd);
        params.put("token", QyClient.curUser.getToken());

        mYNetApiService.modifyPassword(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // next
                response -> {
                    getView().msg2User(response.getData());
                },
                // error
                throwable -> {
                    getView().msg2User("网络错误，请稍后再试");
                    getView().dismissDialog();
                },
                // completed
                () -> {
                    getView().dismissDialog();
                });
    }
}
