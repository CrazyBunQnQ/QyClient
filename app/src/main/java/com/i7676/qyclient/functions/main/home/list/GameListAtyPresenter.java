package com.i7676.qyclient.functions.main.home.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.exception.ServerException;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.rx.RxUtil;
import com.orhanobut.logger.Logger;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HCol on 2016/10/5.
 */

class GameListAtyPresenter extends BasePresenter<GameListAtyView>
    implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String NICE_AND_FRIENDLY = ",请稍后再试 :)";

    @Inject YNetApiService mYNetApiService;
    private Bundle args;

    private int pageNum = 1;
    private int pageSize = 10;

    private Subscription getCategoryGameListSubscription;
    private Subscription searchByGameNameSubscription;

    GameListAtyPresenter(Bundle args) {
        this.args = args;
    }

    @Override protected void onWakeUp() {
        super.onWakeUp();
        renderTitle();
        getDataFromCloud();
    }

    @Override protected void onSleep() {
        super.onSleep();
        doUnsubscribe(getCategoryGameListSubscription);
        doUnsubscribe(searchByGameNameSubscription);
    }

    private void getDataFromCloud() {
        int listRenderType = args.getInt(GameListActivity.TAG_TYPE);
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
        params.put("catid", args.getInt(GameListActivity.CATEGORY_ID_TAG) + "");
        params.put("token", QyClient.curUser.getToken());
        params.put("size", pageSize + "");
        params.put("page", pageNum + "");
        getCategoryGameListSubscription = mYNetApiService.getCategoryGameList(params)
            .compose(RxUtil.networkTransform())
            .subscribe(
                //next
                respData -> {
                    List<RankingGameEntity> data =
                        JSONArray.parseArray(respData.toString(), RankingGameEntity.class);
                    getView().add2List(data);
                },
                // error
                error -> {
                    if (error instanceof ServerException && (((ServerException) error).code
                        == ServerConstans.RESPONSE_DATA_IS_NULL)) {
                        getView().loadCompleted();
                    } else {
                        getView().showEmpty(error.getMessage() + NICE_AND_FRIENDLY);
                    }
                },
                // completed
                () -> {
                    Logger.i(">>> @GameListAtyPresenter#requestCategoryGames completed.");
                });
    }

    private void requestKeywordSearch() {
        searchByGameNameSubscription =
            mYNetApiService.searchByGameName(args.getString(GameListActivity.SEARCH_KEYWORD_TAG))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resp -> {
                    if (resp.getRet() == 0) {
                        List<RankingGameEntity> data =
                            JSONArray.parseArray(resp.getData().toString(),
                                RankingGameEntity.class);
                        getView().add2List(data);
                    } else if (resp.getRet() == ServerConstans.RESPONSE_DATA_IS_NULL) {
                        getView().loadCompleted();
                    } else {
                        getView().showEmpty(resp.getData().toString() + NICE_AND_FRIENDLY);
                    }
                });
    }

    @Override public void onLoadMoreRequested() {
        pageNum++;
        requestCategoryGames();
    }

    @Override public void onRefresh() {
        pageNum = 1;
        getView().clearList();
        getDataFromCloud();
    }
}
