package com.i7676.qyclient.functions.login.sign;

import com.i7676.qyclient.R;
import com.i7676.qyclient.api.wechat.WXAPIEventHandlerImp;
import com.i7676.qyclient.api.wechat.WXUserInfoResponse;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.login.sign.adapter.SignWayAdapter;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/20.
 */

public class SignInFrPresenter extends BasePresenter<SignInFrView>
    implements SignWayAdapter.FkItemClickListener {

    static final int SIGN_IN_WITH_QQ = 0;
    static final int SIGN_IN_WITH_WX = 1;
    static final int SIGN_IN_WITH_ZFB = 2;

    @Inject WXAPIEventHandlerImp wxapiEventHandlerImp;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        getView().setActionBarTitle("登录");
        setupSignInWay();
    }

    private void setupSignInWay() {
        ArrayList<SignWayEntity> signWays = new ArrayList<SignWayEntity>() {
            {
                add(new SignWayEntity(R.drawable.ic_login_qq, "QQ登录", SIGN_IN_WITH_QQ));
                add(new SignWayEntity(R.drawable.ic_login_wx, "微信登录", SIGN_IN_WITH_WX));
                add(new SignWayEntity(R.drawable.ic_login_zfb, "支付宝登录", SIGN_IN_WITH_ZFB));
            }
        };
        getView().render3rdPartySignInWay(signWays);
    }

    @Override public void onItemClick(int position, SignWayEntity signWayEntity) {
        Logger.i(">>> [" + position + "] way 2: " + signWayEntity.getText());
        switch (signWayEntity.getType()) {
            case SIGN_IN_WITH_QQ:
                doSignInWithQQ();
                break;
            case SIGN_IN_WITH_WX:
                doSignInWithWx();
                break;
            case SIGN_IN_WITH_ZFB:
                doSignInWithZFB();
                break;
        }
    }

    private void doSignInWithQQ() {
    }

    private void doSignInWithWx() {
        //getView().go2Web("http://h5.7676.com/mapiindex.php?m=members&c=loginapi&a=wxLogin");
        wxapiEventHandlerImp.setWXUserInfoCallback(mWXUserInfoCallback);
        wxapiEventHandlerImp.loginAndRegister();
    }

    private void doSignInWithZFB() {
    }

    private retrofit2.Callback<WXUserInfoResponse> mWXUserInfoCallback =
        new Callback<WXUserInfoResponse>() {
            @Override public void onResponse(Call<WXUserInfoResponse> call,
                Response<WXUserInfoResponse> response) {
                Logger.i(">>> " + call + ", " + response.body());
            }

            @Override public void onFailure(Call<WXUserInfoResponse> call, Throwable t) {
                Logger.i(">>> " + call + ", " + t.getMessage());
            }
        };
}
