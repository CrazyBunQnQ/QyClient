package com.i7676.qyclient.function.main.entity;

import java.util.List;

/**
 * Created by HCol on 2016/9/18.
 */
public class GameCardEntity extends CommonBaseEntity {
  private String type;
  private List<GameEntity> gameEntities;

  public GameCardEntity(String type, List<GameEntity> gameEntities) {
    this.type = type;
    this.gameEntities = gameEntities;
  }

  public List<GameEntity> getGameEntities() {
    return gameEntities;
  }

  public String getType() {
    return type;
  }
}
