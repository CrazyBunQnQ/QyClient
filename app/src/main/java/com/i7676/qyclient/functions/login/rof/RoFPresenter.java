package com.i7676.qyclient.functions.login.rof;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.login.LoginConstants;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.i7676.qyclient.util.RegexUtils;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.i7676.qyclient.functions.login.rof.RoFFragment.RENDER_TYPE;
import static com.i7676.qyclient.functions.login.rof.RoFFragment.RENDER_TYPE_FORGET_PASSWORD;
import static com.i7676.qyclient.functions.login.rof.RoFFragment.RENDER_TYPE_REGISTER;

/**
 * Created by Administrator on 2016/9/20.
 */
public class RoFPresenter extends BasePresenter<RoFView> implements View.OnClickListener {

    // 页面title
    private static final String TITLE_TEXTS_REGISTER = "手机注册";
    private static final String TITLE_TEXTS_FORGET_PASSWORD = "找回密码";
    // 注册页面需要的提示文本
    private static final String REGISTER_TEXTS[] = {
        "请输入手机号码", "请输入验证码", "获取验证码", "请输入密码", "立即注册并登陆"
    };
    // 忘记页面需要的提示文本
    private static final String FGTPASS_TEXTS[] = {
        "请输入手机号码", "请输入验证码", "获取验证码", "请输入新密码", "更改密码"
    };

    private Bundle args;
    private String mRenderType;
    @Inject YNetApiService mYNetApiService;

    public RoFPresenter(Bundle args) {
        this.args = args;
    }

    @Override protected void onWakeUp() {
        super.onWakeUp();
        renderViews();
    }

    private void renderViews() {
        mRenderType = args.getString(RENDER_TYPE);
        if (null == mRenderType || "".equals(mRenderType.trim())) {
            throw new IllegalAccessError(
                "Don't fucking summon this fragment without right arguments!!");
        }

        String hintsAndTexts[];
        switch (mRenderType) {
            default:
            case RENDER_TYPE_REGISTER:
                getView().setActionBarTitle(TITLE_TEXTS_REGISTER);
                hintsAndTexts = REGISTER_TEXTS;
                break;
            case RENDER_TYPE_FORGET_PASSWORD:
                getView().setActionBarTitle(TITLE_TEXTS_FORGET_PASSWORD);
                hintsAndTexts = FGTPASS_TEXTS;
                break;
        }
        getView().setupWidgetsHint(hintsAndTexts);
    }

    private void submitEvent() {
        switch (mRenderType) {
            case RENDER_TYPE_REGISTER:
                doRegister();
                break;
            case RENDER_TYPE_FORGET_PASSWORD:
                break;
        }
    }

    private void doRegister() {
        String accountText = getView().getAccountText();
        String passwordText = getView().getPasswordText();
        String captchaText = getView().getCaptchaText();

        // 数据校验
        if (TextUtils.isEmpty(accountText)) {
            getView().report2User("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(passwordText)) {
            getView().report2User("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(captchaText)) {
            getView().report2User("请输入验证码");
            return;
        }
        // 构造表单参数
        HashMap<String, String> params = new HashMap<>();
        params.put("username", accountText);
        params.put("password", passwordText);
        params.put("type", String.valueOf(LoginConstants.REGISTER_TYPE_PHONE));
        params.put("code", captchaText);
        // 发送注册
        mYNetApiService.register(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultSubscriber<ReqResult<String>>() {
                @Override public void onNext(ReqResult<String> reqResult) {
                    if (reqResult.getRet() == 0) {
                        getView().report2User("注册成功");
                    } else {
                        onError(new NetworkErrorException(
                            "[" + reqResult.getRet() + "]" + reqResult.getData()));
                    }
                }

                @Override public void onError(Throwable e) {
                    getView().report2User("注册失败:" + e.getMessage());
                }

                @Override public void onCompleted() {
                    getView().doSignInUp(accountText, passwordText);
                }
            });
    }

    private void getCaptchaCode() {
        String accountText = getView().getAccountText();
        // 空校验
        if (TextUtils.isEmpty(accountText)) {
            getView().report2User("请输入手机号码");
            return;
        }
        // 电话号码校验
        if (!accountText.matches(RegexUtils.PHONE_NUMBER_REGEX)) {
            getView().report2User("请输入正确的手机号码");
            return;
        }
        mYNetApiService.getCaptcha(accountText)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io());
        getView().captchaBtnCountDown();
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_vCode:
                getCaptchaCode();
                break;
            case R.id.btn_submit:
                submitEvent();
                break;
        }
    }
}
