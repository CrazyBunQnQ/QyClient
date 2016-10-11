package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/11.
 *
 * 威富通统一下单接口返回实体
 *
 * 版本号 version 是 String(8) 版本号，version 默认值是 2.0。
 * 字符集 charset 是 String(8) 可选值 UTF-8 ，默认为 UTF-8。
 * 签名方式 sign_type 是 String(8) 签名类型，取值：MD5 默认：MD5
 * 返回状态码 status 是 String(16) 0 表示成功非 0 表示失败
 * 返回信息 message 否 String(128) 返回信息，如非空，为错误原因签名失败参数格式校验错误
 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 以下字段在 status 为 0 的时候有返回
 * 支持的支付类型 services 是 String 支持的支付类型，多个以“|”连接
 * 授权码 token_id 是 String (32) 支付授权码
 * 签名 sign 是 String(32) MD5 签名结果，详见“第 4 章 MD5 签名规则”
 */
public class WftUnifiedResponseEntity {
    private String version;
    private String charset;
    private String sign_type;
    private int status;
    private String message;

    private String services;
    private String token_id;
    private String sign;
    private String transno;

    public String getTransno() {
        return transno;
    }

    public void setTransno(String transno) {
        this.transno = transno;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
