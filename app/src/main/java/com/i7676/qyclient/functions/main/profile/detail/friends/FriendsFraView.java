package com.i7676.qyclient.functions.main.profile.detail.friends;

import com.i7676.qyclient.entity.FriendEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

interface FriendsFraView extends BaseView {

    String getInputText();

    void showToast(String msg);

    void showSearchResult(List<FriendEntity> friendEntities);

    void setupFriends(List<FriendEntity> friendEntities);
}
