package com.i7676.qyclient.functions.main.profile.detail.telbind;

import android.text.TextUtils;
import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.util.RegexUtils;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/8.
 */

public class TelBindFraPresenter extends BasePresenter<TelBindFraView>
    implements View.OnClickListener {

    @Inject YNetApiService mYNetApiService;

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                final String phoneText1 = getView().getPhoneText();
                final String captcha = getView().getCaptchaText();
                if (TextUtils.isEmpty(phoneText1) || TextUtils.isEmpty(captcha)) {
                    getView().msg2User("请输入完整信息");
                    break;
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("token", QyClient.curUser.getToken());
                params.put("mobile", phoneText1);
                params.put("code", captcha);

                mYNetApiService.telBind(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        // next
                        stringReqResult -> {
                            getView().msg2User(stringReqResult.getData());
                        },
                        // error
                        throwable -> {
                            getView().msg2User("绑定失败，请稍后再试");
                            getView().clearEditTextInputs();
                        },
                        // completed
                        () -> {
                            getView().clearEditTextInputs();
                        });
                break;
            case R.id.btn_get_vCode:
                final String phoneText = getView().getPhoneText();
                if (!RegexUtils.isMobilePhoneNumber(phoneText)) {
                    getView().msg2User("手机号码格式不正确，请重新输入");
                    break;
                }
                // 发送获取验证码请求
                mYNetApiService.getCaptcha(phoneText)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getRet() == ServerConstans.SUCCESS) {
                            getView().captchaBtnCountDown();
                        } else {
                            getView().msg2User(response.getData());
                        }
                    }, throwable -> {
                        getView().msg2User("获取验证码失败，请稍后再试");
                    }, () -> {
                    });
                break;
        }
    }

    @Override protected void onSleep() {
        getView().abortCaptchaCountDown();
        super.onSleep();
    }
}
