package com.i7676.qyclient.functions.login.sign;

import com.i7676.qyclient.functions.BaseView;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */

/*package*/ interface SignInFrView extends BaseView {

    void signInSuccess();

    void render3rdPartySignInWay(List<SignWayEntity> signWayEntities);

    void finishAty();

    void setActionBarTitle(String actionBarTitle);
}
