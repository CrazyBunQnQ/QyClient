package com.i7676.qyclient.functions.login.rof;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    public static final String RENDER_TYP_REGISTER = "register";
    public static final String RENDER_TYPE_FORGET_PASSWORD = "forget_password";

    // Views
    private EditText mPhoneNum;
    private EditText mVCode;
    private Button btnGetVCode;
    private EditText mPassword;
    private Button btnSubmit;

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
    }

    @NonNull @Override public RoFPresenter providePresenter() {
        return new RoFPresenter(getArguments());
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
}
