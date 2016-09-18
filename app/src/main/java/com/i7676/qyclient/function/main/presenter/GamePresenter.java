package com.i7676.qyclient.function.main.presenter;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.i7676.qyclient.annotations.PreFragment;
import com.i7676.qyclient.constants.ColorConstants;
import com.i7676.qyclient.function.main.entity.GameEntity;
import com.i7676.qyclient.function.main.view.GameView;
import com.i7676.qyclient.net.EgretApiService;
import com.i7676.qyclient.util.AppUtil;
import com.roughike.bottombar.BottomBar;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HCol on 2016/9/13.
 */
@PreFragment public class GamePresenter extends BasePresenter<GameView> {

  private EgretApiService mEgretApiService;
  private Context mContext;
  private Toolbar mToolbar;
  private BottomBar mBottomBar;

  public GamePresenter(EgretApiService mEgretApiService, Context mContext, Toolbar mToolbar,
      BottomBar mBottomBar) {
    this.mEgretApiService = mEgretApiService;
    this.mContext = mContext;
    this.mToolbar = mToolbar;
    this.mBottomBar = mBottomBar;
  }

  @Override protected void onWakeUp() {
    super.onWakeUp();
    mToolbar.setTitle("主 页");
    mToolbar.setBackgroundColor(ColorConstants.TRANSPARENT);
    mBottomBar.selectTabAtPosition(0);

    banner();
    cardsEvent();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mContext = null;
  }

  private void banner() {
    Observable.from(new String[] {
        "http://cdn-img.easyicon.net/png/11324/1132448.gif",
        "http://cdn-img.easyicon.net/png/11324/1132448.gif",
        "http://cdn-img.easyicon.net/png/11324/1132448.gif"
    })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .toList()
        .subscribe(getView()::renderBanner);
  }

  private void cardsEvent() {
    if (AppUtil.isThereInternetConnection(mContext)) {
      mEgretApiService.getGameList("20814")
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .unsubscribeOn(Schedulers.io())
          .doOnError(this::fkError)
          .map(this::map2GameEntity)
          .subscribe(getView()::renderGameCards);
    } else {
      getView().renderGameCards(null);
    }
  }

  private List<GameEntity> map2GameEntity(Object obj) {
    JSONObject response = JSONObject.parseObject(obj.toString());
    if (response != null && "0".equals(String.valueOf(response.get("code")))) {
      return JSONArray.parseArray(response.getString("game_list"), GameEntity.class);
    } else {
      return null;
    }
  }

  private void fkError(Throwable throwable) {
    this.map2GameEntity(null);
  }
}
