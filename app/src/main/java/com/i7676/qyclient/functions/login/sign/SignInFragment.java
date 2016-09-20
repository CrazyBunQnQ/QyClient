package com.i7676.qyclient.functions.login.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.AccountEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;

/**
 * Created by Administrator on 2016/9/20.
 */
@Layout(R.layout.fragment_login_main) public class SignInFragment
    extends BaseFragment<SignInFrPresenter, SignInFrView>
    implements SignInFrView, View.OnClickListener {

  public static SignInFragment create(Bundle args) {
    final SignInFragment fragment = new SignInFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private EditText etAccount;
  private EditText etPassword;
  private Button btnSignIn;
  private Button btnRegister;

  private TextView tvForgetPwd;
  private TextView tvQuickReg;

  private NonScrollableRecyclerView m3rdPartySignInWay;

  @Override protected void initRootViews(View rootView) {
    etAccount = (EditText) rootView.findViewById(R.id.et_account);
    etPassword = (EditText) rootView.findViewById(R.id.et_password);

    btnSignIn = (Button) rootView.findViewById(R.id.btn_signIn);
    btnRegister = (Button) rootView.findViewById(R.id.btn_register);

    tvForgetPwd = (TextView) rootView.findViewById(R.id.tv_forgetPwd);
    tvQuickReg = (TextView) rootView.findViewById(R.id.tv_quickRegister);

    m3rdPartySignInWay = (NonScrollableRecyclerView) rootView.findViewById(R.id.rv_signIn_way);

    btnSignIn.setOnClickListener(this);
    btnRegister.setOnClickListener(this);
    tvForgetPwd.setOnClickListener(this);
    tvQuickReg.setOnClickListener(this);
  }

  @NonNull @Override public SignInFrPresenter providePresenter() {
    return new SignInFrPresenter();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_signIn:
        AccountEntity signInAccount = buildAccountInfo();
        getPresenter().doSignIn(signInAccount);
        break;
    }
  }

  private AccountEntity buildAccountInfo() {
    String accountInfo = etAccount.getText().toString();
    String passwordInfo = etPassword.getText().toString();

    if (checkValidity(accountInfo, passwordInfo)) {
      final AccountEntity entity = new AccountEntity();
      entity.setAccount(accountInfo);
      entity.setPassword(passwordInfo);
      return entity;
    }
    return null;
  }

  private boolean checkValidity(String accInfo, String pwdInfo) {
    return !TextUtils.isEmpty(accInfo) && !TextUtils.isEmpty(pwdInfo);
  }
}
