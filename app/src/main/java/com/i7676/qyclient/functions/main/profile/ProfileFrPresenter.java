package com.i7676.qyclient.functions.main.profile;

import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ProfileEntity;
import com.i7676.qyclient.entity.ProfileMenuEntity;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.rx.DefaultSubscriber;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ProfileFrPresenter extends BasePresenter<ProfileFrView>
    implements View.OnClickListener {

    @Inject YNetApiService mYNetApiService;

    private static boolean CREATE_FLAG = true;
    private static final UserEntity DEFAULT_USER = new UserEntity() {
        {
            setUserid("1024");
            setNickname("点击头像登录~");
            setUsername("QYUser");
            setAvatar("http://h5.7676.com/phpsso_server/statics/images/member/nophoto.gif");
        }
    };

    // 平台积分
    //private int points = 0;
    // 剩余h币
    //private int friendsNum = 0;
    // 好友数量
    // 电话绑定
    private boolean isTelBound = false;

    @Override protected void onWakeUp() {
        super.onWakeUp();

        getView().hideToolbar();

        if (CREATE_FLAG && QyClient.curUser == null) {
            getView().showLoginAty();
            CREATE_FLAG = false;
        } else {
            getView().setupUserInfo(QyClient.curUser == null ? DEFAULT_USER : QyClient.curUser);
            checkTelBindStatus();
        }
    }

    private void checkTelBindStatus() {
        if (QyClient.curUser == null) {
            buildMenus();
        } else {
            mYNetApiService.getProfileInfo(QyClient.curUser.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<ReqResult<ProfileEntity>>() {
                    @Override public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override public void onNext(ReqResult<ProfileEntity> response) {
                        super.onNext(response);
                        if (response.getRet() == ServerConstans.SUCCESS) {
                            // 手机绑定状态
                            isTelBound = (response.getData().getMobile() != 0);
                        }
                        buildMenus();
                    }
                });
        }
    }

    private void buildMenus() {
        final ArrayList<ProfileMenuEntity> menuEntities = new ArrayList<>();
        menuEntities.add(
            new ProfileMenuEntity(ProfileConstants.MENU_POINTS, R.drawable.cc_star_dev, "0", "平台积分",
                "", false));
        menuEntities.add(
            new ProfileMenuEntity(ProfileConstants.MENU_H, R.drawable.cc_money_dev, "0", "剩余H币", "",
                false));
        menuEntities.add(
            new ProfileMenuEntity(ProfileConstants.MENU_ACCOUNT, R.drawable.set_account, "账号设置", "",
                "设置昵称等", true));
        menuEntities.add(
            new ProfileMenuEntity(ProfileConstants.MENU_TEL_BIND, R.drawable.set_phone, "绑定手机", "",
                isTelBound ? "已绑定" : "未绑定", true));
        menuEntities.add(
            new ProfileMenuEntity(ProfileConstants.MENU_FRIENDS, R.drawable.set_firend, "我的好友", "",
                "找好友聊聊天", false));
        menuEntities.add(
            new ProfileMenuEntity(ProfileConstants.MENU_RECHARGE, R.drawable.set_icon, "充值中心", "",
                "查看历史记录", false));
        getView().setupFunctionPanel(menuEntities);
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
            case R.id.tv_contact_cs:
                break;
            case R.id.tv_about_us:
                break;
            default:
                if (QyClient.curUser == null) getView().showLoginAty();
                break;
        }
    }
}
