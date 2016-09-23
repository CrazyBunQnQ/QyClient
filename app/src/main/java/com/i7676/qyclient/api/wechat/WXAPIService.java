package com.i7676.qyclient.api.wechat;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/23.
 */

public interface WXAPIService {
    @GET("/sns/oauth2/access_token?grant_type=authorization_code") void getAccessToken(
        @Query("appid") String appId, @Query("secret") String secret, @Query("code") String code,
        Callback<WXAccessTokenResponse> callback);

    @GET("/sns/userinfo") void getUserInfo(@Query("access_token") String accessToken,
        @Query("openid") String openId, Callback<WXUserInfoResponse> callback);
}
