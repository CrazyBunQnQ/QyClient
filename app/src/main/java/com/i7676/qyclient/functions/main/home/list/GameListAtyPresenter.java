package com.i7676.qyclient.functions.main.home.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.functions.BasePresenter;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HCol on 2016/10/5.
 */

class GameListAtyPresenter extends BasePresenter<GameListAtyView>
    implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject YNetApiService mYNetApiService;
    private Bundle args;

    private int pageNum = 1;
    private int pageSize = 10;

    GameListAtyPresenter(Bundle args) {
        this.args = args;
    }

    @Override protected void onWakeUp() {
        super.onWakeUp();
        int listRenderType = args.getInt(GameListActivity.TAG_TYPE);
        renderTitle();
        switch (listRenderType) {
            case GameListActivity.CATEGORY_TASK:
                requestCategoryGames();
                break;
            case GameListActivity.SEARCH_TASK:
                requestKeywordSearch();
                break;
            default:
                // do nothing...
                break;
        }
    }

    private void renderTitle() {
        String titleText = args.getString(GameListActivity.TITLE_TEXT_TAG);
        if (TextUtils.isEmpty(titleText)) titleText = GameListActivity.DEFAULT_TITLE_TEXT;
        getView().setTitleText(titleText);
    }

    private void requestCategoryGames() {
        HashMap<String, String> params = new HashMap<>();
        params.put("catid", args.getString(GameListActivity.CATEGORY_ID_TAG));
        params.put("token", QyClient.curUser.getToken());
        params.put("size", pageSize + "");
        params.put("page", pageNum + "");
        mYNetApiService.getCategoryGameList(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(resp -> {
                if (resp.getRet() == 0) getView().add2List(resp.getData());
            });
    }

    private void requestKeywordSearch() {
        mYNetApiService.searchByGameName(args.getString(GameListActivity.SEARCH_KEYWORD_TAG))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(resp -> {
                if (resp.getRet() == 0) getView().add2List(resp.getData());
            });
    }

    @Override public void onLoadMoreRequested() {
        pageNum++;
        requestCategoryGames();
    }

    @Override public void onRefresh() {
        pageNum = 1;
        getView().clearList();
        requestCategoryGames();
    }
}
