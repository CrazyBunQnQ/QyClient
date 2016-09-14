package com.i7676.qyclient.function.main.view;

import com.i7676.qyclient.function.main.entity.GameEntity;

import java.util.List;

/**
 * Created by HCol on 2016/9/13.
 */
public interface GameView extends BaseView {
    void renderGameCards(List<GameEntity> gameEntities);
}
