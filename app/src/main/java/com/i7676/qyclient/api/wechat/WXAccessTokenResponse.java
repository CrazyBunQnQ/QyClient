package com.i7676.qyclient.api.wechat;

/**
 * Created by Administrator on 2016/9/23.
 */

public class WXAccessTokenResponse extends WXBaseEntity {
    public String accessToken;
    public long expiresIn;
    public String openid;
    public String refreshToken;
    public String scope;
}
