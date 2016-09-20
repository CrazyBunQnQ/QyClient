package com.i7676.qyclient.net;

import com.i7676.qyclient.entity.BannerEntity;
import com.i7676.qyclient.entity.ReqResult;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/14.
 */
public interface YNetApiService {
  String BASE_URL = "http://h5.7676.com/";

  // 首页-轮播图
  String BANNER_IMG = "mapiindex.php?m=index&c=indexapi&a=getbanner";

  @GET(BANNER_IMG) Observable<ReqResult<List<BannerEntity>>> getBanner();
}
