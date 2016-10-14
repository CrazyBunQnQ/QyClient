package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/14.
 *
 * id	动态ID	int
 * uid	登录用户ID	int
 * title	标题	string
 * content	内容	string
 * creat_dt	发表时间	int
 * img	图片	json
 * comnum	评论数	int
 * likenum	点赞数	int
 * nickname	昵称	string
 * userid	动态发表人ID	int
 * phpssouid	动态发表人phpssouid	int
 * username	动态发表人昵称	string
 * avatar	动态发表人头像	string
 * islike	登录用户是否点赞该动态 1 是；0 否	int
 * img_prefix	图片地址前缀	string
 */

public class HiCardEntity {
    private int id;
    private int uid;
    private String title;
    private String content;
    private String creat_dt;
    private String img;
    private int comnum;
    private int likenum;
    private String nickname;
    private int userid;
    private int phpssouid;
    private String username;
    private String avatar;
    private int islike;
    private String img_prefix;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreat_dt() {
        return creat_dt;
    }

    public void setCreat_dt(String creat_dt) {
        this.creat_dt = creat_dt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getComnum() {
        return comnum;
    }

    public void setComnum(int comnum) {
        this.comnum = comnum;
    }

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIslike() {
        return islike;
    }

    public void setIslike(int islike) {
        this.islike = islike;
    }

    public String getImg_prefix() {
        return img_prefix;
    }

    public void setImg_prefix(String img_prefix) {
        this.img_prefix = img_prefix;
    }
}
