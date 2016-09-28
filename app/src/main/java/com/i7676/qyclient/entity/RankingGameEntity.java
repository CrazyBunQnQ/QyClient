package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/9/28.
 */

public class RankingGameEntity {

    /**
     * gameId : 20001
     * catname : 梦幻家园
     * gameSize : 3w
     * href : http://h5.7676.com/index.php?m=game&c=game_manage&a=clienttogame&gname=layabox&gameId=20001
     * gameIco : http://h5.7676.com/uploadfile/2016/0913/20160913104351787.jpg
     * thumb : http://h5.7676.com/uploadfile/2016/0913/20160913104412303.jpg
     * isopencharge : 1
     * iswebgame : 1
     */

    private int gameId;
    private String catname;
    private String gameSize;
    private String href;
    private String gameIco;
    private String thumb;
    private int isopencharge;
    private int iswebgame;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getGameSize() {
        return gameSize;
    }

    public void setGameSize(String gameSize) {
        this.gameSize = gameSize;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getGameIco() {
        return gameIco;
    }

    public void setGameIco(String gameIco) {
        this.gameIco = gameIco;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getIsopencharge() {
        return isopencharge;
    }

    public void setIsopencharge(int isopencharge) {
        this.isopencharge = isopencharge;
    }

    public int getIswebgame() {
        return iswebgame;
    }

    public void setIswebgame(int iswebgame) {
        this.iswebgame = iswebgame;
    }
}
