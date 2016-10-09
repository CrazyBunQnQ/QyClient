package com.i7676.qyclient.functions.main.profile.detail.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.profile.detail.MenuDetailActivity;
import com.i7676.qyclient.util.DialogUtils;
import com.i7676.qyclient.util.SharedPreferencesUtil;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/10/8.
 */
@Layout(R.layout.fragment_profile_account) public class AccountFragment
    extends BaseFragment<AccountFraPresenter, AccountFraView> implements AccountFraView {

    public static AccountFragment create(@Nullable Bundle args) {
        final AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private WeakReference<AlertDialog> mOptionalDialog;
    private TextView etNickname;
    private TextView etOriginPassword;
    private TextView etNewPassword;
    private TextView etNewPasswordConfirmed;
    private TextView tvAccount;

    @Override protected void initRootViews(View rootView) {
        tvAccount = (TextView) rootView.findViewById(R.id.tv_account);
        showNickname(QyClient.curUser.getNickname());

        final AutoLoadImageView imgAvatar =
            (AutoLoadImageView) rootView.findViewById(R.id.img_account_avatar);

        imgAvatar.setImageUrlAndAuthorInfo(QyClient.curUser.getAvatar(), null,
            R.mipmap.default_avatar);

        getActivity().setTitle("账号设置");

        rootView.findViewById(R.id.changeNickName).setOnClickListener(getPresenter());
        rootView.findViewById(R.id.changePassword).setOnClickListener(getPresenter());
    }

    @Override protected void setupInject() {
        super.setupInject();
        ((MenuDetailActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @NonNull @Override public AccountFraPresenter providePresenter() {
        return new AccountFraPresenter();
    }

    @Override public void dismissDialog() {
        if (mOptionalDialog != null && mOptionalDialog.get().isShowing()) {
            mOptionalDialog.get().dismiss();
            mOptionalDialog.clear();
        }
    }

    @Override public void msg2User(String msg) {
        ((MenuDetailActivity) getActivity()).msg2User(msg);
    }

    @Override public void showChangeNickNameView() {
        final View changeNkNameView = LayoutInflater.from(getContext())
            .inflate(R.layout.view_profile_account_change_nkname, null, false);
        changeNkNameView.findViewById(R.id.btn_submit_mdfNk).setOnClickListener(getPresenter());

        etNickname = (TextView) changeNkNameView.findViewById(R.id.etNickname);

        mOptionalDialog = new WeakReference<>(
            DialogUtils.showSpaicalViewDialog(getContext(), changeNkNameView, "修改昵称", null, true));
    }

    @Override public void showChangePasswordView() {
        final View changePwdView = LayoutInflater.from(getContext())
            .inflate(R.layout.view_profile_account_change_pwd, null, false);
        changePwdView.findViewById(R.id.btn_submit_mdfPwd).setOnClickListener(getPresenter());

        etOriginPassword = (TextView) changePwdView.findViewById(R.id.etOriginPassword);
        etNewPassword = (TextView) changePwdView.findViewById(R.id.etNewPassword);
        etNewPasswordConfirmed = (TextView) changePwdView.findViewById(R.id.etNewPasswordConfirmed);

        mOptionalDialog = new WeakReference<>(
            DialogUtils.showSpaicalViewDialog(getContext(), changePwdView, "修改密码", null, true));
    }

    @Override public String getOriginPasswordText() {
        return etOriginPassword.getText().toString();
    }

    @Override public String getNewPasswordText() {
        return etNewPassword.getText().toString();
    }

    @Override public String getNewPasswordConfirmedText() {
        return etNewPasswordConfirmed.getText().toString();
    }

    @Override public String getNicknameText() {
        return etNickname.getText().toString();
    }

    @Override public void showNickname(String nickname) {
        tvAccount.setText("当前账号: " + nickname);
    }

    @Override public void modifyLocalAccountInfo(UserEntity entity) {
        SharedPreferencesUtil.getInstance(getContext())
            .saveSerializable(QyClient.CURRENT_USER_SP_TAG, entity);
    }
}
