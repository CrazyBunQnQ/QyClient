package com.i7676.qyclient.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/21.
 */

public class UserEntity implements Serializable {
    private String userid;
    private String groupid;
    private String username;
    private String nickname;
    private String avatar;
    private String token;
    private String rtoken;

    public String getRtoken() {
        return rtoken;
    }

    public void setRtoken(String rtoken) {
        this.rtoken = rtoken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override public String toString() {
        return "{userid: "
            + getUserid()
            + ", groupid: "
            + getGroupid()
            + ", username: "
            + getUsername()
            + ", nickname: "
            + getNickname()
            + ", avatar: "
            + getAvatar()
            + ", token: "
            + getToken()
            + "}\n";
    }
}
