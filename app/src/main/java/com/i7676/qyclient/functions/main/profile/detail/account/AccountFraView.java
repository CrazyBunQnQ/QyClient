package com.i7676.qyclient.functions.main.profile.detail.account;

import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/10/8.
 */

interface AccountFraView extends BaseView {

    void dismissDialog();

    void msg2User(String msg);

    void showChangeNickNameView();

    void showChangePasswordView();

    // 修改密码
    String getOriginPasswordText();

    String getNewPasswordText();

    String getNewPasswordConfirmedText();

    // 修改昵称
    String getNicknameText();

    void showNickname(String nickname);

    void modifyLocalAccountInfo(UserEntity entity);
}
