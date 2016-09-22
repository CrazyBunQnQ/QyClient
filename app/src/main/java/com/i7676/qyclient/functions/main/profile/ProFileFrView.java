package com.i7676.qyclient.functions.main.profile;

import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseView;

/**
 * Created by Administrator on 2016/9/20.
 */

public interface ProfileFrView extends BaseView {

    void hideToolbar();

    void showToolbar();

    void showLoginAty();

    void showHomeFr();

    void setupUserInfo(UserEntity userEntity);

    void setupGameHistory();

    void hideGameHistory();

    void showGameHistory();

    void setupFunctionPanel();
}
