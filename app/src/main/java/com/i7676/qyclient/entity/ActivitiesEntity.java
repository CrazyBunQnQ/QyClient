package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ActivitiesEntity {


    /**
     * id : 25
     * title : 六角碎片积分送好礼
     * catid : 32
     * description :
     * thumb : http://h5.7676.com/uploadfile/2016/0818/20160818033004517.jpg
     * gameSize : 5749
     * href : http://h5.7676.com/index.php?m=game&c=game_manage&a=clienttogame&gname=ljsp
     * gameid : 31
     * starttime : 2016-08-30
     * endtime : 2016-08-31
     */

    private int id;
    private String title;
    private String catid;
    private String description;
    private String thumb;
    private String gameSize;
    private String href;
    private String gameid;
    private String starttime;
    private String endtime;

    public ActivitiesEntity() {
        super();
    }

    public ActivitiesEntity(int
                                    id, String title, String catid, String gameSize, String starttime, String endtime, String gameid, String href, String thumb, String description) {
        super();
        this.id = id;
        this.title = title;
        this.catid = catid;
        this.gameSize = gameSize;
        this.starttime = starttime;
        this.endtime = endtime;
        this.gameid = gameid;
        this.href = href;
        this.thumb = thumb;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
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

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
