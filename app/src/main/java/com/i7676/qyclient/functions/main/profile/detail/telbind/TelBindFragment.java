package com.i7676.qyclient.functions.main.profile.detail.telbind;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.profile.detail.MenuDetailActivity;

/**
 * Created by Administrator on 2016/10/8.
 */
@Layout(R.layout.fragment_profile_tel_bind) public class TelBindFragment
    extends BaseFragment<TelBindFraPresenter, TelBindFraView> implements TelBindFraView {

    public static TelBindFragment create(@Nullable Bundle args) {
        final TelBindFragment fragment = new TelBindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Button btnSubmit;
    private EditText etPhone;
    private EditText etCaptcha;
    private Button btnGetVCode;

    // Members
    private static final int CAPTCHA_BTN_UPDATE = 1;
    private static int CAPTCHA_COUNT_DOWN_TIME; // 秒
    private boolean isAbort = false;

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

    @Override protected void initRootViews(View rootView) {
        getActivity().setTitle("手机绑定");

        btnSubmit = (Button) rootView.findViewById(R.id.btn_submit);
        etPhone = (EditText) rootView.findViewById(R.id.et_phoneNum);
        etCaptcha = (EditText) rootView.findViewById(R.id.et_vCode);
        btnGetVCode = (Button) rootView.findViewById(R.id.btn_get_vCode);

        btnSubmit.setOnClickListener(getPresenter());
        btnGetVCode.setOnClickListener(getPresenter());
    }

    @Override protected void setupInject() {
        super.setupInject();
        ((MenuDetailActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public TelBindFraPresenter providePresenter() {
        return new TelBindFraPresenter();
    }

    @Override public String getPhoneText() {
        return etPhone.getText().toString();
    }

    @Override public String getCaptchaText() {
        return etCaptcha.getText().toString();
    }

    @Override public void msg2User(String msg) {
        ((MenuDetailActivity) getActivity()).getPresenter().getView().msg2User(msg);
    }

    @Override public void captchaBtnCountDown() {
        CAPTCHA_COUNT_DOWN_TIME = 60;
        btnGetVCode.setEnabled(false);
        btnGetVCode.setBackgroundColor(Color.GRAY);

        final Thread mThread = new Thread() {
            @Override public void run() {
                super.run();
                while (--CAPTCHA_COUNT_DOWN_TIME > 0 && !isAbort) {
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

    @Override public void clearEditTextInputs() {
        this.etCaptcha.setText("");
        this.etPhone.setText("");
    }

    @Override public void abortCaptchaCountDown() {
        isAbort = true;
    }
}
