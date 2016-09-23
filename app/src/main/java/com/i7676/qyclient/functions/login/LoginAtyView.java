package com.i7676.qyclient.functions.login;

import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/9/20.
 */

/*package*/ public interface LoginAtyView extends BaseView {
    int LOG_SIGN_IN = 0;
    int LOG_FORGET_PASSWORD = 1;
    int LOG_REGISTER = 2;

    // ************************************************** Toolbar
    void setTitle(String titleText);
}
