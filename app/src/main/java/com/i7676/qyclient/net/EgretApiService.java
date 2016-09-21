package com.i7676.qyclient.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author heqi
 * @version 1.0.0
 * @description
 * @create 2016/9/13
 * @modify 2016/9/13 by HCol
 */
public interface EgretApiService {

    String BASE_URL = "http://api.open.egret.com/";

    @GET("Channel.gameList") Observable<Object> getGameList(@Query("app_id") String appId);
}
