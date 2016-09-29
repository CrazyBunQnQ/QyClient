package com.i7676.qyclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class HomeFrEntity {
    private List<BannerEntity> banner;
    private List<CategoryEntity> category;
    private ArrayList<RankingGameEntity> newgame;
    private ArrayList<RankingGameEntity> hotgame;
    private List<RankingGameEntity> history;

    public List<RankingGameEntity> getHistory() {
        return history;
    }

    public void setHistory(List<RankingGameEntity> history) {
        this.history = history;
    }

    public List<BannerEntity> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerEntity> banner) {
        this.banner = banner;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public ArrayList<RankingGameEntity> getNewgame() {
        return newgame;
    }

    public void setNewgame(ArrayList<RankingGameEntity> newgame) {
        this.newgame = newgame;
    }

    public ArrayList<RankingGameEntity> getHotgame() {
        return hotgame;
    }

    public void setHotgame(ArrayList<RankingGameEntity> hotgame) {
        this.hotgame = hotgame;
    }
}
