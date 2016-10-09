package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/8.
 */

public class ProfileMenuEntity {
    private int id;
    private int icon;
    private String name;
    private String desc;
    private String desc1;
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
