package com.i7676.qyclient.functions.login;

import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/9/20.
 */
public interface LoginAtyView extends BaseView {
    void setTitle(String titleText);

    void showSignInFr();

    void showForgetPasswordFr();

    void showRegisterFr();

    void showQuickRegFr();

    void showDialog2User(String msg);

    void closeDialog();

    void signUpSuccess();

    void signUpFailed(String msg);

    void storeUser(UserEntity userEntity);
}
