package com.i7676.qyclient.functions.main.profile;

import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.ProfileMenuEntity;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;
import java.util.ArrayList;

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

    private static final ArrayList<ProfileMenuEntity> MENUS = new ArrayList<ProfileMenuEntity>() {
        {
            add(new ProfileMenuEntity(R.drawable.cc_star_dev, "0", "平台积分", "", false));
            add(new ProfileMenuEntity(R.drawable.cc_money_dev, "0", "剩余H币", "", false));
            add(new ProfileMenuEntity(R.drawable.set_account, "账号设置", "", "设置昵称等", true));
            add(new ProfileMenuEntity(R.drawable.set_phone, "绑定手机", "", "未绑定", true));
            add(new ProfileMenuEntity(R.drawable.set_firend, "我的好友", "", "找好友聊聊天", true));
            add(new ProfileMenuEntity(R.drawable.set_icon, "充值中心", "", "查看历史记录", true));
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
            getView().setupFunctionPanel(MENUS);
        }
    }

    private void doSignOff() {
        if (QyClient.curUser == null) return;
        QyClient.curUser = null;
        CREATE_FLAG = true;
        getView().doSignOff();
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
