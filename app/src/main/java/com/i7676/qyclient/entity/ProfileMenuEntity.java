package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/8.
 */

public class ProfileMenuEntity {
    // 按钮编号
    private int id;
    // 按钮图标
    private int icon;
    // 按钮名字
    private String name;
    // 描述一，在图片下面的那段描述
    private String desc;
    // 描述二，在按钮名字下面的那段描述,描述一和描述二在适配器中的显示方案是互斥的
    private String desc1;
    // 是否可用，不可用的话会显示“未开放”角标
    private boolean available;

    public ProfileMenuEntity() {
    }

    public ProfileMenuEntity(int id, int icon, String name, String desc, String desc1,
        boolean available) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.desc = desc;
        this.desc1 = desc1;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
