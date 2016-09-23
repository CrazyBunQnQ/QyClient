package com.i7676.qyclient.functions.login.sign.entity;

public class SignWayEntity {
    private int iconResId;
    private String text;
    private int type;

    public SignWayEntity(int iconResId, String text, int type) {
        this.iconResId = iconResId;
        this.text = text;
        this.type = type;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }
}