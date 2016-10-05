package com.i7676.qyclient.functions.login.rof;

import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/9/20.
 */

/*package*/ interface RoFView extends BaseView {

    void setActionBarTitle(String actionBarTitle);

    void setupWidgetsHint(String hints[]);

    String getPhoneNumberText();

    String getPasswordText();

    String getCaptchaText();

    void clearEditTexts();

    void captchaBtnCountDown();

    void report2User(String msg);

    void doSignInUp(String accountInfo, String password);

    void freeze();
}
