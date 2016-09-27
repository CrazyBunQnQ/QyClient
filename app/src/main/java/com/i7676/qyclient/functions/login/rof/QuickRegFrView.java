package com.i7676.qyclient.functions.login.rof;

import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/9/23.
 */

public interface QuickRegFrView extends BaseView {

    void setActionBarTitle(String actionBarTitle);

    String getAccountText();

    String getPasswordText();

    String getPasswordConfirmText();

    void showToast2User(String msg);

    void signInSuccess();

    void showDialog2User(String msg);

    void closeDialog();

    void signInFailed(String msg);
}
