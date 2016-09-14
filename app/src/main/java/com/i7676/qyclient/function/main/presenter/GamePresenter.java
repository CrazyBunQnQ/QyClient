package com.i7676.qyclient.function.main.presenter;

import android.content.Context;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.i7676.qyclient.function.main.entity.GameEntity;
import com.i7676.qyclient.function.main.view.GameView;
import com.i7676.qyclient.net.EgretApiService;
import com.i7676.qyclient.util.AppUtil;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HCol on 2016/9/13.
 */
public class GamePresenter extends BasePresenter<GameView> {

  @Inject EgretApiService mEgretApiService;

  private Context mContext;

  public GamePresenter(Context mContext) {
    this.mContext = mContext;
  }

  @Override protected void onWakeUp() {
    super.onWakeUp();
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

  @Override protected void onDestroy() {
    super.onDestroy();
    mContext = null;
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
