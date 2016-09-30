package com.i7676.qyclient.api.wechat;

/**
 * Created by Administrator on 2016/9/23.
 */

public class WXAccessTokenResponse extends WXBaseEntity {
    public String access_token;
    public long expires_in;
    public String openid;
    public String refresh__token;
    public String scope;
}
