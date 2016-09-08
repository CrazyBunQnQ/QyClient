package com.i7676.qyclient.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.interfaces.ToolbarAgent;

/**
 * Created by Administrator on 2016/9/8.
 */

@Layout(R.layout.fragment_login_reg_fgtpass) public class LoginRegOrFgtpassFragment
    extends ToolbarInteractorFragment {

  // 实例
  public static LoginRegOrFgtpassFragment create(Bundle args, ToolbarAgent toolbarAgent) {
    final LoginRegOrFgtpassFragment instance = new LoginRegOrFgtpassFragment();
    instance.setArguments(args);
    instance.registerToolbarAgent(toolbarAgent);
    return instance;
  }

  // 类型区分
  public static final String RENDER_TYPE = "render_type";
  public static final String RENDER_TYP_REGISTER = "register";
  public static final String RENDER_TYPE_FGTPASS = "forget_password";

  // 页面title
  private static final String TITLE_TEXTS_REGISTER = "手机注册";
  private static final String TITLE_TEXTS_FGTPASS = "找回密码";
  // 注册页面需要的提示文本
  private static final String REGISTER_TEXTS[] = {
      "请输入手机号码", "请输入验证码", "获取验证码", "请输入密码", "立即注册并登陆"
  };
  // 忘记页面需要的提示文本
  private static final String FGTPASS_TEXTS[] = {
      "请输入手机号码", "请输入验证码", "获取验证码", "请输入新密码", "更改密码"
  };

  // widgets
  private EditText mPhoneNum;
  private EditText mVCode;
  private Button btnGetVCode;
  private EditText mPassword;
  private Button btnSubmit;

  @Override protected void initView(View view) {
    mPhoneNum = (EditText) view.findViewById(R.id.et_phoneNum);
    mVCode = (EditText) view.findViewById(R.id.et_vCode);
    btnGetVCode = (Button) view.findViewById(R.id.btn_get_vCode);
    mPassword = (EditText) view.findViewById(R.id.et_password);
    btnSubmit = (Button) view.findViewById(R.id.btn_submit);
  }

  @Override public void onResume() {
    super.onResume();
    // 设置View的基本显示
    renderViews();
  }

  private void renderViews() {
    Bundle args = getArguments();
    if (args == null) {
      throw new IllegalAccessError("Arguments bundle is null!!");
    }

    String mRenderType = args.getString(RENDER_TYPE);
    if (null == mRenderType || "".equals(mRenderType.trim())) {
      throw new IllegalAccessError("Don't fucking summon this fragment without right arguments!!");
    }

    String hintsAndTexts[];
    switch (mRenderType) {
      default:
      case RENDER_TYP_REGISTER:
        this.mToolbarAgent.setTitleText(TITLE_TEXTS_REGISTER);
        hintsAndTexts = REGISTER_TEXTS;
        break;
      case RENDER_TYPE_FGTPASS:
        this.mToolbarAgent.setTitleText(TITLE_TEXTS_FGTPASS);
        hintsAndTexts = FGTPASS_TEXTS;
        break;
    }
    mPhoneNum.setHint(hintsAndTexts[0]);
    mVCode.setHint(hintsAndTexts[1]);
    btnGetVCode.setText(hintsAndTexts[2]);
    mPassword.setHint(hintsAndTexts[3]);
    btnSubmit.setText(hintsAndTexts[4]);

    // 注册时间监听
    registerViewsListeners(mRenderType);
  }

  private void registerViewsListeners(String mRenderType) {

  }
}
