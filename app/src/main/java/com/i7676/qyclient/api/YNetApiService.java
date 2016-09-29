package com.i7676.qyclient.api;

import com.i7676.qyclient.entity.BannerEntity;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.HomeFrEntity;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.UserEntity;
import java.util.List;
import java.util.Map;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/14.
 */
public interface YNetApiService {
    String BASE_URL = "http://h5.7676.com/";

    // 首页-轮播图
    String BANNER_IMG = "mapiindex.php?m=index&c=indexapi&a=getbanner";
    // 登录
    String LOGIN = "mapiindex.php?m=members&c=loginapi&a=login";
    // 注册
    // &username=haha2&password=123456&nickname=ddddd&type=0
    String REGISTER = "mapiindex.php?m=members&c=loginapi&a=register";
    // 手机号拉取验证码
    String CAPTCHA = "mapiindex.php?m=members&c=loginapi&a=sendMsg";
    // 游戏分类列表
    String CATEGORY = "mapiindex.php?m=index&c=indexapi&a=getCategory";
    // 首页排行游戏
    String RANKING_GAMES = "mapiindex.php?m=index&c=indexapi";//&getNewGame&page=1&size=10
    // 用户历史数据  &page=1&size=10&token=d
    String USER_PLAYED_GAMES = "mapiindex.php?m=index&c=indexapi&a=getUserGame";
    // 首页数据集
    String INDEX = "mapiindex.php?m=index&c=indexapi&a=getIndex";

    @GET(INDEX) Observable<ReqResult<HomeFrEntity>> getIndexInfo(
        @QueryMap Map<String, String> params);

    @GET(USER_PLAYED_GAMES) Observable<ReqResult<List<RankingGameEntity>>> getUserPlayedGames(
        @QueryMap Map<String, String> params);

    @GET(RANKING_GAMES) Observable<ReqResult<List<RankingGameEntity>>> getRankingGames(
        @QueryMap Map<String, String> params);

    @GET(BANNER_IMG) Observable<ReqResult<List<BannerEntity>>> getBanner();

    @GET(CATEGORY) Observable<ReqResult<List<CategoryEntity>>> getCategory();

    //username=jiaojie&password=jiaojieadmin
    @FormUrlEncoded @POST(LOGIN) Observable<ReqResult<UserEntity>> login(
        @Field("username") String userName, @Field("password") String password);

    @FormUrlEncoded @POST(REGISTER) Observable<ReqResult<String>> register(
        @FieldMap Map<String, String> params);

    @GET(CAPTCHA) Observable<Void> getCaptcha(@Query("mobile") String mobile);
}
