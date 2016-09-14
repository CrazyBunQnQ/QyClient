package com.i7676.qyclient.function.main.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class GameEntity extends CommonBaseEntity {
    private int gameId;
    private String name;
    private String type;
    private int payType;
    private int screen;
    private int played;
    private String url;
    private String runtimeUrl;
    private String payCallBackUrl;
    private String icon;
    private List<String> gamePictures;
    private String shortDesc;
    private String desc;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRuntimeUrl() {
        return runtimeUrl;
    }

    public void setRuntimeUrl(String runtimeUrl) {
        this.runtimeUrl = runtimeUrl;
    }

    public String getPayCallBackUrl() {
        return payCallBackUrl;
    }

    public void setPayCallBackUrl(String payCallBackUrl) {
        this.payCallBackUrl = payCallBackUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getGamePictures() {
        return gamePictures;
    }

    public void setGamePictures(List<String> gamePictures) {
        this.gamePictures = gamePictures;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
