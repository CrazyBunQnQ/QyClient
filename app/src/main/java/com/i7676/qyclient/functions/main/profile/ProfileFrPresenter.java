package com.i7676.qyclient.functions.main.profile;

import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ProfileFrPresenter extends BasePresenter<ProfileFrView>
    implements View.OnClickListener {

    private static boolean CREATE_FLAG = true;
    private static final UserEntity DEFAULT_USER = new UserEntity() {
        {
            setUserid("10086");
            setNickname("点击头像登录~");
            setUsername("QYUser");
            setAvatar("http://h5.7676.com/phpsso_server/statics/images/member/nophoto.gif");
        }
    };

    @Override protected void onWakeUp() {
        super.onWakeUp();

        getView().hideToolbar();

        if (CREATE_FLAG && QyClient.curUser == null) {
            getView().showLoginAty();
            CREATE_FLAG = false;
        } else {
            getView().setupUserInfo(QyClient.curUser == null ? DEFAULT_USER : QyClient.curUser);
            getView().setupGameHistory();
            getView().setupFunctionPanel();
        }
    }

    private void doSignOff() {
        QyClient.curUser = null;
        CREATE_FLAG = true;
        getView().showHomeFr();
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_sign_off:
                doSignOff();
                break;
            default:
                if (QyClient.curUser == null) getView().showLoginAty();
                break;
        }
    }
}
