package com.i7676.qyclient.functions.login.rof;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.login.LoginActivity;

/**
 * Created by Administrator on 2016/9/20.
 *
 * R. Register
 * F. Forget
 *
 * RoF. Register or Forget password
 *
 * 登录-忘记密码 或 手机注册
 */
@Layout(R.layout.fragment_login_reg_fgtpass) public class RoFFragment
    extends BaseFragment<RoFPresenter, RoFView> implements RoFView {

    // 类型区分
    public static final String RENDER_TYPE = "render_type";
    public static final String RENDER_TYPE_REGISTER = "register";
    public static final String RENDER_TYPE_FORGET_PASSWORD = "forget_password";

    // Views
    private EditText mPhoneNum;
    private EditText mVCode;
    private Button btnGetVCode;
    private EditText mPassword;
    private Button btnSubmit;

    // Members
    private static final int CAPTCHA_BTN_UPDATE = 1;
    private static int CAPTCHA_COUNT_DOWN_TIME; // 秒

    private Handler UIHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CAPTCHA_BTN_UPDATE:
                    if (CAPTCHA_COUNT_DOWN_TIME <= 0) {
                        btnGetVCode.setEnabled(true);
                        btnGetVCode.setBackgroundColor(
                            getResources().getColor(R.color.colorPrimary));
                        btnGetVCode.setText("获取验证码");
                    } else {
                        btnGetVCode.setText(CAPTCHA_COUNT_DOWN_TIME + "S");
                    }
                    break;
            }
        }
    };

    public static RoFFragment create(Bundle args) {
        if (args == null) throw new NullPointerException(">>> \"args\" can not be null");
        final RoFFragment fFragment = new RoFFragment();
        fFragment.setArguments(args);
        return fFragment;
    }

    @Override protected void initRootViews(View rootView) {
        mPhoneNum = (EditText) rootView.findViewById(R.id.et_phoneNum);
        mVCode = (EditText) rootView.findViewById(R.id.et_vCode);
        btnGetVCode = (Button) rootView.findViewById(R.id.btn_get_vCode);
        mPassword = (EditText) rootView.findViewById(R.id.et_password);
        btnSubmit = (Button) rootView.findViewById(R.id.btn_submit);

        btnGetVCode.setOnClickListener(getPresenter());
        btnSubmit.setOnClickListener(getPresenter());
    }

    @NonNull @Override public RoFPresenter providePresenter() {
        return new RoFPresenter(getArguments());
    }

    @Override protected void setupInject() {
        ((LoginActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @Override public void setActionBarTitle(String actionBarTitle) {
        ((LoginActivity) getActivity()).getPresenter().getView().setTitle(actionBarTitle);
    }

    @Override public void setupWidgetsHint(String[] hints) {
        mPhoneNum.setHint(hints[0]);
        mVCode.setHint(hints[1]);
        btnGetVCode.setText(hints[2]);
        mPassword.setHint(hints[3]);
        btnSubmit.setText(hints[4]);
    }

    @Override public String getAccountText() {
        return mPhoneNum.getText().toString();
    }

    @Override public String getPasswordText() {
        return mPassword.getText().toString();
    }

    @Override public String getCaptchaText() {
        return mVCode.getText().toString();
    }

    @Override public void captchaBtnCountDown() {
        CAPTCHA_COUNT_DOWN_TIME = 60;
        btnGetVCode.setEnabled(false);
        btnGetVCode.setBackgroundColor(Color.GRAY);

        final Thread mThread = new Thread() {
            @Override public void run() {
                super.run();
                while (--CAPTCHA_COUNT_DOWN_TIME > 0) {
                    try {
                        Thread.sleep(1000);
                        UIHandler.sendEmptyMessage(CAPTCHA_BTN_UPDATE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mThread.start();
    }

    @Override public void report2User(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override public void signInSuccess() {
        getActivity().finish();
    }
}
