package com.i7676.qyclient.functions.login.sign.entity;

public class SignWayEntity {
    private int resId;
    private String text;

    public SignWayEntity(int resId, String text) {
        this.resId = resId;
        this.text = text;
    }

    public int getResId() {
        return resId;
    }

    public String getText() {
        return text;
    }
}