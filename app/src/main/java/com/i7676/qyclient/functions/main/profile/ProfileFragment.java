package com.i7676.qyclient.functions.main.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/20.
 *
 * 主页-个人信息
 */
@Layout(R.layout.fragment_profile) public class ProfileFragment
    extends BaseFragment<ProfileFrPresenter, ProfileFrView> implements ProfileFrView {

    public static ProfileFragment create(Bundle args) {
        final ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Views
    private AutoLoadImageView imgAvatar;
    private AutoLoadImageView imgUserBackground;
    private ImageView imgSignOff;
    private TextView tvUserId;
    private TextView tvUserNickname;
    private NonScrollableRecyclerView gameHistoryList;
    private NonScrollableRecyclerView functionList;
    private TextView tvContactCS;
    private TextView tvAboutUs;

    @Inject CategoryAdapter categoryAdapter;
    @Inject MainAtyNavigator navigator;

    @Override protected void initRootViews(View rootView) {
        imgAvatar = (AutoLoadImageView) rootView.findViewById(R.id.img_avatar);
        imgUserBackground = (AutoLoadImageView) rootView.findViewById(R.id.img_user_bg);
        imgSignOff = (ImageView) rootView.findViewById(R.id.img_sign_off);
        tvUserId = (TextView) rootView.findViewById(R.id.tv_user_id);
        tvUserNickname = (TextView) rootView.findViewById(R.id.tv_user_nick);
        gameHistoryList = (NonScrollableRecyclerView) rootView.findViewById(R.id.rv_game_history);
        functionList = (NonScrollableRecyclerView) rootView.findViewById(R.id.rv_function);
        tvContactCS = (TextView) rootView.findViewById(R.id.tv_contact_cs);
        tvAboutUs = (TextView) rootView.findViewById(R.id.tv_about_us);

        imgAvatar.setOnClickListener(getPresenter());
        imgSignOff.setOnClickListener(getPresenter());
        tvContactCS.setOnClickListener(getPresenter());
        tvAboutUs.setOnClickListener(getPresenter());

        gameHistoryList.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        gameHistoryList.setAdapter(categoryAdapter);

        functionList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        functionList.setAdapter(categoryAdapter);
    }

    @Override protected void setupInject() {
        ((MainActivity) getActivity()).getAtyComponent().inject(this);
    }

    @NonNull @Override public ProfileFrPresenter providePresenter() {
        return new ProfileFrPresenter();
    }

    @Override public void hideToolbar() {
        ((MainActivity) getActivity()).getPresenter().getView().hideActionBar();
    }

    @Override public void showToolbar() {
        ((MainActivity) getActivity()).getPresenter().getView().showActionBar();
    }

    @Override public void showLoginAty() {
        navigator.showLoginAty();
    }

    @Override public void showHomeFr() {
        ((MainActivity) getActivity()).getPresenter().getView().showHomeFr();
    }

    @Override public void setupUserInfo(UserEntity userEntity) {
        imgUserBackground.setImageUrlAndAuthorInfo(userEntity.getAvatar(), null,
            R.mipmap.default_avatar);
        imgAvatar.setImageUrlAndAuthorInfo(userEntity.getAvatar(), null, R.mipmap.default_avatar);
        tvUserId.setText("ID:767688" + userEntity.getUserid());
        tvUserNickname.setText(userEntity.getNickname());
    }

    @Override public void setupGameHistory() {

    }

    @Override public void hideGameHistory() {
        gameHistoryList.setVisibility(View.GONE);
    }

    @Override public void showGameHistory() {
        gameHistoryList.setVisibility(View.VISIBLE);
    }

    @Override public void setupFunctionPanel() {

    }
}
