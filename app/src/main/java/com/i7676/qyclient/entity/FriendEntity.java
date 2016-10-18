package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/17.
 *
 * id	关系唯一标示	int
 * uid	登录人ID	int
 * fuid	好友ID	int
 * notename		string
 * datetime	添加好友时间	int
 * state	好友状态-1 未申请；0，申请，1被忽略，2 好友，3 黑名单，4删除	int
 * username	好友名称	string
 * nickname	好友昵称	string
 * userid	好友ID	int
 * phpssouid	好友SSOID	int
 * avatar	头像	string
 */

public class FriendEntity {

    public static class StateConstants {
        public static final int NOT_APPLIED = -1;
        public static final int APPLIED = 0;
        public static final int IGNORED = 1;
        public static final int FRIENDS_NOW = 2;
        public static final int BANNED = 3;
        public static final int DELETED = 4;
    }

    /**
     * 用于搜索结果
     */
    //public static class SearchResultEntity {
    //    /**
    //     * userid: "127",
    //     * phpssouid: "164",
    //     * username: "wx_IngAP7GeTqla",
    //     * nickname: "猴赛雷",
    //     * avatar: "http://h5.7676.com/phpsso_server/uploadfile/avatar/1/1/164/90x90.jpg
    //     */
    //    private int userid;
    //    private int phpssouid;
    //    private String username;
    //    private String nickname;
    //    private String avatar;
    //}

    private int id;
    private int uid;
    private int fuid;
    private String notename;
    private long datetime;
    private int state;
    private String username;
    private String nickname;
    private int userid;
    private int phpssouid;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFuid() {
        return fuid;
    }

    public void setFuid(int fuid) {
        this.fuid = fuid;
    }

    public String getNotename() {
        return notename;
    }

    public void setNotename(String notename) {
        this.notename = notename;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPhpssouid() {
        return phpssouid;
    }

    public void setPhpssouid(int phpssouid) {
        this.phpssouid = phpssouid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
