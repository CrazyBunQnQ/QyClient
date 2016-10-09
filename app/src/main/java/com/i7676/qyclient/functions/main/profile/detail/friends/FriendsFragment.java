package com.i7676.qyclient.functions.main.profile.detail.friends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.profile.detail.account.AccountFragment;

/**
 * Created by Administrator on 2016/10/8.
 */

public class FriendsFragment extends BaseFragment<FriendsFraPresenter, FriendsFraView>
    implements FriendsFraView {

    public static FriendsFragment create(@Nullable Bundle args) {
        final FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override protected void initRootViews(View rootView) {

    }

    @NonNull @Override public FriendsFraPresenter providePresenter() {
        return null;
    }
}
