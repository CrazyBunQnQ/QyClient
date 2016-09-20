package com.i7676.qyclient.entity;

import java.util.List;

public class GameCardEntity {
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