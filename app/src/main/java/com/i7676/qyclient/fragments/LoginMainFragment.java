package com.i7676.qyclient.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.BaseActivity;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.interfaces.ToolbarAgent;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
@Layout(R.layout.fragment_login_main) public class LoginMainFragment
    extends ToolbarInteractorFragment {

  public static final LoginMainFragment create(ToolbarAgent toolbarAgent) {
    final LoginMainFragment fragment = new LoginMainFragment();
    fragment.registerToolbarAgent(toolbarAgent);
    return fragment;
  }

  // QQ
  private static final String QQ_API_ID = "1105660746";
  private Tencent mTencent;
  private IUiListener mQQListener = new IUiListener() {
    @Override public void onComplete(Object o) {
      reportLogger(o.toString());
    }

    @Override public void onError(UiError uiError) {
      reportLogger("onError");
    }

    @Override public void onCancel() {
      reportLogger("onCancel");
    }
  };

  private void reportLogger(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
  }

  private void qqLogin() {
    if (mTencent == null) {
      mTencent = Tencent.createInstance(QQ_API_ID, getContext());
    }
    if (!mTencent.isSessionValid()) {
      mTencent.login(this, "all", mQQListener);
    }
  }

  // WeiXin
  private static final String WX_APP_ID = "wx2534d167763b1f30";
  private static final String WX_APP_SECRET = "";
  private IWXAPI api;

  private void wxLogin() {
    if (api == null) {
      //api注册
      api = WXAPIFactory.createWXAPI(getContext(), WX_APP_ID, true);
      api.registerApp(WX_APP_ID);
    }
    SendAuth.Req req = new SendAuth.Req();
    //授权读取用户信息
    req.scope = "snsapi_userinfo";
    //自定义信息
    req.state = "wechat_sdk_demo_test";
    //向微信发送请求
    api.sendReq(req);
  }

  private NonScrollableRecyclerView mSignInWayList;

  private Button register;
  private TextView fogetPassword;

  @Override public void onResume() {
    super.onResume();
    mToolbarAgent.setTitleText("注册登录");
  }

  @Override protected void initView(View view) {
    ArrayList<SignWayEntity> signWays = new ArrayList<SignWayEntity>() {
      {
        add(new SignWayEntity(R.drawable.ic_login_qq, "QQ登录"));
        add(new SignWayEntity(R.drawable.ic_login_wx, "微信登录"));
        add(new SignWayEntity(R.drawable.ic_login_zfb, "支付宝登录"));
      }
    };
    mSignInWayList = (NonScrollableRecyclerView) view.findViewById(R.id.rv_signIn_way);
    mSignInWayList.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mSignInWayList.setAdapter(new SignWayAdapter(R.layout.item_signin_way, signWays));
    mSignInWayList.setHasFixedSize(true);

    // 手机注册按钮
    register = (Button) view.findViewById(R.id.btn_register);
    register.setOnClickListener(v -> {
      Bundle args = new Bundle();
      args.putString(LoginRegOrFgtpassFragment.RENDER_TYPE,
          LoginRegOrFgtpassFragment.RENDER_TYP_REGISTER);
      add2BackStack(args);
    });
    // 忘记密码按钮
    fogetPassword = (TextView) view.findViewById(R.id.btn_fgtpass);
    fogetPassword.setOnClickListener(v -> {
      Bundle args = new Bundle();
      args.putString(LoginRegOrFgtpassFragment.RENDER_TYPE,
          LoginRegOrFgtpassFragment.RENDER_TYPE_FGTPASS);
      add2BackStack(args);
    });
  }

  private On3rdPartyLogin on3rdPartyLogin = new On3rdPartyLogin();

  private class SignWayAdapter extends BaseQuickAdapter<SignWayEntity> {

    public SignWayAdapter(int layoutResId, List<SignWayEntity> data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, SignWayEntity signWayEntity) {
      baseViewHolder.setImageResource(R.id.img_sin_logo, signWayEntity.getResId());
      baseViewHolder.setText(R.id.tv_sin_text, signWayEntity.getText());
      baseViewHolder.getConvertView().setOnClickListener(on3rdPartyLogin);
    }
  }

  private class On3rdPartyLogin implements View.OnClickListener {
    @Override public void onClick(View v) {
      String text = (String) ((TextView) v.findViewById(R.id.tv_sin_text)).getText();
      if ("QQ登录".equals(text)) {
        qqLogin();
      } else if ("微信登录".equals(text)) {
        wxLogin();
      }
    }
  }

  private class SignWayEntity {
    private int resId;
    private String text;

    public SignWayEntity(int resId, String text) {
      this.resId = resId;
      this.text = text;
    }

    public int getResId() {
      return resId;
    }

    public String getText() {
      return text;
    }
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    Tencent.onActivityResultData(requestCode, resultCode, data, mQQListener);
    super.onActivityResult(requestCode, resultCode, data);
  }

  private void add2BackStack(Bundle args) {
    getActivity().getSupportFragmentManager().beginTransaction()
        // 添加Fragment
        .add(R.id.container, LoginRegOrFgtpassFragment.create(args, ((BaseActivity) getActivity())))
        // 设置进回退栈里
        .addToBackStack(LoginRegOrFgtpassFragment.class.getSimpleName() + ":" + args.getString(
            LoginRegOrFgtpassFragment.RENDER_TYPE))
        // 提交
        .commit();
  }
}
