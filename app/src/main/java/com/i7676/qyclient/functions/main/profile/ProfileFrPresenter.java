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
            setGroupid("1");
            setNickname("点我登录~");
            setUsername("saulala");
            setToken(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6IjRmMWcyM2ExMmFhIn0.eyJpc3MiOiJodHRwOlwvXC9oNS43Njc2LmNvbSIsImF1ZCI6Imh0dHA6XC9cL2g1Ljc2NzYuY29tIiwianRpIjoiNGYxZzIzYTEyYWEiLCJpYXQiOjE0NzQ0NTMyNTIsIm5iZiI6MTQ3NDQ1MzU1MiwiZXhwIjoxNDc0NTM5NjUyLCJkYXRhIjoiamlhb2ppZSJ9.9OKZeykODI51P4PzJJUYmw4nTtBy0ystfke5GO3BdgQ");
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
