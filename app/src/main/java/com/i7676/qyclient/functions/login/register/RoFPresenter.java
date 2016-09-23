package com.i7676.qyclient.functions.login.register;

import android.os.Bundle;
import com.i7676.qyclient.functions.BasePresenter;

import static com.i7676.qyclient.functions.login.register.RoFFragment.RENDER_TYPE;
import static com.i7676.qyclient.functions.login.register.RoFFragment.RENDER_TYPE_FORGET_PASSWORD;
import static com.i7676.qyclient.functions.login.register.RoFFragment.RENDER_TYP_REGISTER;

/**
 * Created by Administrator on 2016/9/20.
 */

/*package*/ class RoFPresenter extends BasePresenter<RoFView> {

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

    public RoFPresenter(Bundle args) {
        this.args = args;
    }

    @Override protected void onWakeUp() {
        super.onWakeUp();
        renderViews();
    }

    private void renderViews() {
        String mRenderType = args.getString(RENDER_TYPE);
        if (null == mRenderType || "".equals(mRenderType.trim())) {
            throw new IllegalAccessError(
                "Don't fucking summon this fragment without right arguments!!");
        }

        String hintsAndTexts[];
        switch (mRenderType) {
            default:
            case RENDER_TYP_REGISTER:
                getView().setActionBarTitle(TITLE_TEXTS_REGISTER);
                hintsAndTexts = REGISTER_TEXTS;
                break;
            case RENDER_TYPE_FORGET_PASSWORD:
                getView().setActionBarTitle(TITLE_TEXTS_FORGET_PASSWORD);
                hintsAndTexts = FGTPASS_TEXTS;
                break;
        }
        // 注册时间监听
        //registerViewsListeners(mRenderType);
        getView().setupWidgetsHint(hintsAndTexts);
    }
}
