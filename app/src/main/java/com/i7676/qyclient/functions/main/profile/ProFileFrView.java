package com.i7676.qyclient.functions.main.profile;

import com.i7676.qyclient.entity.ProfileMenuEntity;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */

public interface ProfileFrView extends BaseView {

    void hideToolbar();

    void showToolbar();

    void showLoginAty();

    void doSignOff();

    void setupUserInfo(UserEntity userEntity);

    void setupFunctionPanel(List<ProfileMenuEntity> menuEntities);
}
