package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/8.
 */

public class GiftEntity{


    /*
     * bid : 52
     * bname : 仙路传奇国庆大礼包
     * introduce : 礼包内容 挂机加速卡x5挂机经验卡x5国庆大礼包x5藏宝图x5,声望令x2
     使用说明 游戏中点击“设置->兑换激活码”
     * gid : 285
     * add_time : 1475916480
     * icon : http://h5.7676.com/statics/images/gift_icon/52.png
     * status : 0
     * remain : 100
     * consume : 0
     */

    private int bid;
    private String bname;
    private String introduce;
    private int gid;
    private int add_time;
    private String icon;
    private int status;
    private String remain;
    private String consume;

    public GiftEntity() {
    }

    public GiftEntity(int bid, String bname, String introduce, int gid, int add_time, String icon, int status, String remain, String consume) {
        this.bid = bid;
        this.bname = bname;
        this.introduce = introduce;
        this.gid = gid;
        this.add_time = add_time;
        this.icon = icon;
        this.status = status;
        this.remain = remain;
        this.consume = consume;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }
}
