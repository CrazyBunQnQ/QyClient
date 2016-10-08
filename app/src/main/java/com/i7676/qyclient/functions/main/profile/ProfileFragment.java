package com.i7676.qyclient.functions.main.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.ProfileMenuEntity;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.FunctionMenuAdapter;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import com.i7676.qyclient.util.SharedPreferencesUtil;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
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
    //private NonScrollableRecyclerView gameHistoryList;
    private RecyclerView functionList;
    private View fucntionListHead;
    private TextView tvContactCS;
    private TextView tvAboutUs;
    private FunctionMenuAdapter mMenuAdapter;

    @Inject MainAtyNavigator navigator;

    @Override protected void initRootViews(View rootView) {
        imgAvatar = (AutoLoadImageView) rootView.findViewById(R.id.img_avatar);
        imgUserBackground = (AutoLoadImageView) rootView.findViewById(R.id.img_user_bg);
        imgSignOff = (ImageView) rootView.findViewById(R.id.img_sign_off);
        tvUserId = (TextView) rootView.findViewById(R.id.tv_user_id);
        tvUserNickname = (TextView) rootView.findViewById(R.id.tv_user_nick);
        functionList = (RecyclerView) rootView.findViewById(R.id.rv_function);
        fucntionListHead = rootView.findViewById(R.id.view_profile_function_head);
        tvContactCS = (TextView) rootView.findViewById(R.id.tv_contact_cs);
        tvAboutUs = (TextView) rootView.findViewById(R.id.tv_about_us);

        imgAvatar.setOnClickListener(getPresenter());
        imgSignOff.setOnClickListener(getPresenter());
        tvContactCS.setOnClickListener(getPresenter());
        tvAboutUs.setOnClickListener(getPresenter());

        mMenuAdapter = new FunctionMenuAdapter(R.layout.item_profile_menu, new ArrayList<>());
        functionList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        functionList.setAdapter(mMenuAdapter);
        functionList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Logger.i(
                    ">>> menu: " + i + "[" + ((ProfileMenuEntity) baseQuickAdapter.getData().get(i))
                        .getName() + "] was clicked.");
            }
        });
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

    @Override public void doSignOff() {
        SharedPreferencesUtil.getInstance(getContext())
            .saveSerializable(QyClient.CURRENT_USER_SP_TAG, null);
        ((MainActivity) getActivity()).getPresenter().getView().showHomeFr();
    }

    @Override public void setupUserInfo(UserEntity userEntity) {
        //imgUserBackground.setImageUrlAndAuthorInfo(userEntity.getAvatar(), null,
        //    R.mipmap.profile_bgk);
        imgAvatar.setImageUrlAndAuthorInfo(userEntity.getAvatar(), null, R.mipmap.default_avatar);
        tvUserId.setText("ID:767688" + userEntity.getUserid());
        tvUserNickname.setText(userEntity.getNickname());
    }

    @Override public void setupFunctionPanel(List<ProfileMenuEntity> menuEntities) {
        mMenuAdapter.setNewData(menuEntities);
    }
}
