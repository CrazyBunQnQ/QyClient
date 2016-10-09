package com.i7676.qyclient.functions.main.profile.detail.telbind;

import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/10/8.
 */

interface TelBindFraView extends BaseView {

    String getPhoneText();

    String getCaptchaText();

    void msg2User(String msg);

    void captchaBtnCountDown();

    void clearEditTextInputs();

    void abortCaptchaCountDown();
}
