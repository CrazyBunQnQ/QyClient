package com.i7676.qyclient.functions.login.rof;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.util.RandomUtils;

/**
 * Created by Administrator on 2016/9/23.
 *
 * 登录-快速注册
 */

@Layout(R.layout.fragment_quick_register) public class QuickRegFragment
    extends BaseFragment<QuickRegFrPresenter, QuickRegFrView> implements QuickRegFrView {

    public static QuickRegFragment create(Bundle args) {
        final QuickRegFragment fragment = new QuickRegFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private EditText etAccount;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private Button btnRegisterAndSignIn;

    @Override protected void initRootViews(View rootView) {
        etAccount = (EditText) rootView.findViewById(R.id.et_account);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);
        etPasswordConfirm = (EditText) rootView.findViewById(R.id.et_password_confirm);
        btnRegisterAndSignIn = (Button) rootView.findViewById(R.id.btn_register_and_signIn);

        String generateAccountText = RandomUtils.getRandomNumbersAndLetters(6);
        etAccount.setText(generateAccountText);

        btnRegisterAndSignIn.setOnClickListener(getPresenter());
    }

    @Override protected void setupInject() {
        ((LoginActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public QuickRegFrPresenter providePresenter() {
        return new QuickRegFrPresenter();
    }

    @Override public void setActionBarTitle(String actionBarTitle) {
        ((LoginActivity) getActivity()).getPresenter().getView().setTitle(actionBarTitle);
    }

    @Override public String getAccountText() {
        return etAccount.getText().toString();
    }

    @Override public String getPasswordText() {
        return etPassword.getText().toString();
    }

    @Override public String getPasswordConfirmText() {
        return etPasswordConfirm.getText().toString();
    }

    @Override public void report2User(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override public void signInSuccess() {
        getActivity().finish();
    }
}
