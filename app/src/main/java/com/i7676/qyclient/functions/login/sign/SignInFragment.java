package com.i7676.qyclient.functions.login.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.AccountEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.h5game.PlayGameActivity;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.functions.login.sign.adapter.SignWayAdapter;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import java.util.ArrayList;
import java.util.List;

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

    private RecyclerView m3rdPartySignInWay;
    private SignWayAdapter mSignInWayAdapter;

    @Override protected void initRootViews(View rootView) {
        etAccount = (EditText) rootView.findViewById(R.id.et_account);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);

        btnSignIn = (Button) rootView.findViewById(R.id.btn_signIn);
        btnRegister = (Button) rootView.findViewById(R.id.btn_register);

        tvForgetPwd = (TextView) rootView.findViewById(R.id.tv_forgetPwd);
        tvQuickReg = (TextView) rootView.findViewById(R.id.tv_quickRegister);

        m3rdPartySignInWay = (RecyclerView) rootView.findViewById(R.id.rv_signIn_way);
        m3rdPartySignInWay.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mSignInWayAdapter = new SignWayAdapter(R.layout.item_signin_way, new ArrayList<>());
        mSignInWayAdapter.setFkItemClickListener(getPresenter());
        m3rdPartySignInWay.setAdapter(mSignInWayAdapter);

        btnSignIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);
        tvQuickReg.setOnClickListener(this);
    }

    @Override protected void setupInject() {
        ((QyClient) getActivity().getApplication()).getClientComponent().inject(getPresenter());
    }

    @NonNull @Override public SignInFrPresenter providePresenter() {
        return new SignInFrPresenter();
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signIn:
                AccountEntity signInAccount = buildAccountInfo();
                if (signInAccount == null) break;
                getPresenter().doSignIn(signInAccount);
                break;
            case R.id.btn_register:
                ((LoginActivity) getActivity()).showRegisterFr();
                break;
            case R.id.tv_forgetPwd:
                ((LoginActivity) getActivity()).showForgetPasswordFr();
                break;
            case R.id.tv_quickRegister:
                ((LoginActivity) getActivity()).showQuickRegFr();
                break;
        }
    }

    @Override public void signInSuccess() {
        getActivity().finish();
    }

    @Override public void render3rdPartySignInWay(List<SignWayEntity> signWayEntities) {
        mSignInWayAdapter.setNewData(signWayEntities);
    }

    @Override public void finishAty() {
        getActivity().finish();
    }

    @Override public void setActionBarTitle(String actionBarTitle) {
        ((LoginActivity) getActivity()).getPresenter().getView().setTitle(actionBarTitle);
    }

    @Override public void signInFailed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override public void go2Web(String url) {
        Bundle args = new Bundle();
        args.putString(PlayGameActivity.GAME_URL, url);
        getActivity().startActivity(PlayGameActivity.buildIntent(getContext(), args));
    }

    private AccountEntity buildAccountInfo() {
        String accountInfo = etAccount.getText().toString();
        String passwordInfo = etPassword.getText().toString();

        if (checkValidity(accountInfo, passwordInfo)) {
            final AccountEntity entity = new AccountEntity();
            entity.setAccount(accountInfo);
            entity.setPassword(passwordInfo);
            return entity;
        } else {
            Toast.makeText(getContext(), "请输入用户名和密码", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    private boolean checkValidity(String accInfo, String pwdInfo) {
        return !TextUtils.isEmpty(accInfo) && !TextUtils.isEmpty(pwdInfo);
    }
}
