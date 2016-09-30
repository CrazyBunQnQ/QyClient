package com.i7676.qyclient.functions.login.sign;

import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseView;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */

/*package*/ interface SignInFrView extends BaseView {

    void render3rdPartySignInWay(List<SignWayEntity> signWayEntities);

    void setActionBarTitle(String actionBarTitle);

    void go2Web(String url);

    void storeUser(UserEntity userEntity);

    void signUpSuccess();

    void signUpFailed(String str);

    void showDialog2User(String msg);

    void closeDialog();
}
