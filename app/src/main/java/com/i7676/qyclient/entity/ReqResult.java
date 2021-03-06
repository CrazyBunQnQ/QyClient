package com.i7676.qyclient.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/20.
 */

public class ReqResult<T> implements Serializable {
    private T data;
    private int ret;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
