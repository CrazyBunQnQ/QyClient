package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RankingActyEnty {


    /**
     * userid : 118
     * strval : 64
     * sortid : 1
     * name : 1133
     * avatar : http://h5.7676.com/phpsso_server/uploadfile/avatar/1/1/153/90x90.jpg
     */

    private int userid;
    private String strval;
    private String sortid;
    private String name;
    private String avatar;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getStrval() {
        return strval;
    }

    public void setStrval(String strval) {
        this.strval = strval;
    }

    public String getSortid() {
        return sortid;
    }

    public void setSortid(String sortid) {
        this.sortid = sortid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    @Override
    public String toString() {
        return "RankingActyEnty{" +
                "userid=" + userid +
                ", strval='" + strval + '\'' +
                ", sortid=" + sortid +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
