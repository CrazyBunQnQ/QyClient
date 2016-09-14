package com.i7676.qyclient.net;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/14.
 */
public interface YnetApiService {
  String BASE_URL = "";

  @POST Observable<Object> login();
}
