package com.i7676.qyclient.functions.main.profile.detail.friends;

import android.text.TextUtils;
import android.view.View;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.exception.ServerException;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.rx.RxUtil;
import com.orhanobut.logger.Logger;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/10/8.
 */

public class FriendsFraPresenter extends BasePresenter<FriendsFraView>
    implements View.OnClickListener {

    @Inject YNetApiService mYNetApiService;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        initFriends();
    }

    void initFriends() {
        mYNetApiService.getFriendsIndex(QyClient.curUser.getToken())
            .compose(RxUtil.networkTransform())
            .subscribe(
                // next
                friendEntities -> {
                    getView().setupFriends(friendEntities);
                },
                // error
                error -> {
                    getView().showToast(error.getMessage());
                },
                // completed
                () -> {
                });
    }

    @Override public void onClick(View v) {
        final String keyword = getView().getInputText();
        if (TextUtils.isEmpty(keyword)) {
            getView().showToast("无效字符");
        } else {
            doSearch(keyword);
        }
    }

    /**
     * 因为搜索出来的用户，字段不全，小心使用，详情请参考接口文档。
     */
    void doSearch(String keyword) {
        mYNetApiService.searchFriends(QyClient.curUser.getToken(), keyword)
            .compose(RxUtil.networkTransform())
            .subscribe(
                // next
                retObj -> {
                    getView().showSearchResult(retObj);
                },
                // error
                error -> {
                    getView().showToast(((ServerException) error).getMessage());
                },
                // completed
                () -> {
                    Logger.i(">>> completed@FriendsFraPresenter#searchFriends");
                });
    }
}
