package com.i7676.qyclient.function.presenter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.function.view.GameView;
import com.i7676.qyclient.net.EgretApiService;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HCol on 2016/9/13.
 */
public class GamePresenter extends BasePresenter<GameView> {

    @Inject
    EgretApiService mEgretApiService;

    @Override
    protected void onWakeUp() {
        super.onWakeUp();
        mEgretApiService.getGameList("20814")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(this::map2GameEntity)
                .subscribe(getView()::renderGameCards);
    }

    private List<GameEntity> map2GameEntity(Object obj) {
        JSONObject response = JSONObject.parseObject(obj.toString());
        if (response != null && "0".equals(String.valueOf(response.get("code")))) {
            return JSONArray.parseArray(response.getString("game_list"), GameEntity.class);
        } else {
            return null;
        }
    }
}
