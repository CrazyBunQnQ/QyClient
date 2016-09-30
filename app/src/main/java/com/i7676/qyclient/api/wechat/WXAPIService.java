package com.i7676.qyclient.api.wechat;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/23.
 */

public interface WXAPIService {

    String ACCESS_TOKEN_URL =
        "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";

    @GET("/sns/oauth2/access_token?grant_type=authorization_code")
    Observable<WXAccessTokenResponse> getAccessToken(@Query("appid") String appId,
        @Query("secret") String secret, @Query("code") String code);

    @GET("/sns/userinfo") Observable<WXUserInfoResponse> getUserInfo(
        @Query("access_token") String accessToken, @Query("openid") String openId);
}
