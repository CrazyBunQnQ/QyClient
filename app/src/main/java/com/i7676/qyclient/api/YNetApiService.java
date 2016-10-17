package com.i7676.qyclient.api;

import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.entity.BannerEntity;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.HomeFrEntity;
import com.i7676.qyclient.entity.ProfileEntity;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.Test;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.entity.WftUnifiedResponseEntity;

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
    // 第三方登录请求-微信
    String WX_SIGN_IN = "mapiindex.php?m=members&c=loginapi&a=thirdLogin&type=wx";
    // 忘记密码
    String FORGET_PASSWORD = "mapiindex.php?m=members&c=loginapi&a=forget";
    // 分类列表游戏数据
    String CATEGORY_GAME_LIST = "mapiindex.php?m=index&c=indexapi&a=getGame";
    // 游戏搜索接口
    String GAME_SEARCH = "mapiindex.php?m=index&c=indexapi&a=searchGame";
    // 修改密码
    String MODIFY_PASSWORD = "mapiindex.php?m=members&c=info&a=modifyPass";
    // 修改昵称
    String MODIFY_NICKNAME = "mapiindex.php?m=members&c=info&a=modifyNick";
    // 手机绑定
    String TEL_BIND = "mapiindex.php?m=members&c=info&a=bindMobile";
    // 手机绑定状态检测
    String TEL_BIND_STATUS = "mapiindex.php?m=members&c=info&a=index";
    // 威富通统一预下单请求
    String WFT_PAYMENT = "mapiindex.php?m=pay&c=index&a=getTransNo";
    // 威富通支付结果请求
    String WFT_PAY_CALLBACK = "http://h5.7676.com/index.php?m=index&c=PayH5&a=paystatus";
    // 支付宝Only
    String ZFB_ONLY = "mapiindex.php?m=pay&c=index&a=getTransNo";

    // &transno=ss
    //@GET(WFT_PAY_CALLBACK) Observable<ReqResult<String>> wftPayCallback(
    //    @Query("transno") String transno);

    //正在进行的活动
    String ACTIVITY_CURRENT="mapiindex.php?m=activities&c=index&a=getNowAct";
    //

    // 活动历史接口
    String Activity_PAST="mapiindex.php?m=activities&c=index&a=getHisAct";

    // 活动详情接口
    String ACTIVITY_DETAIL="http://h5.7676.com/mapiindex.php?m=activities&c=index&a=getActDet";

    //活动详情排行版
    String ACTIVITY_RANKING="mapiindex.php?m=activities&c=index&a=getActRank";

    //&token
    @GET(TEL_BIND_STATUS) Observable<ReqResult<ProfileEntity>> getProfileInfo(
        @Query("token") String token);

    //礼包接口
    String GIFT_Home="mapiindex.php?m=gift&c=index&a=index";

    //礼包领取接口
    String GIFT_GET="mapiindex.php?m=gift&c=index&a=recGift";

    // 礼包详情接口
    String GIFT_DETAIL="mapiindex.php?m=gift&c=index&a=getDetail";

    @GET(ZFB_ONLY) Observable<ReqResult<String>> getZFBOnly(@QueryMap Map<String, String> params);

    @POST(WFT_PAYMENT) @FormUrlEncoded
    Observable<ReqResult<WftUnifiedResponseEntity>> postWFTUnified(
        @FieldMap Map<String, String> params);

    //&mobile=xxxxx&token=oo&code=23414
    @POST(TEL_BIND) @FormUrlEncoded Observable<ReqResult<String>> telBind(
        @FieldMap Map<String, String> params);

    //&oldpass=ss&newpass=ddd&token
    @POST(MODIFY_PASSWORD) @FormUrlEncoded Observable<ReqResult<String>> modifyPassword(
        @FieldMap Map<String, String> params);

    //&nickname=aa&token
    @POST(MODIFY_NICKNAME) @FormUrlEncoded Observable<ReqResult<String>> modifyNickname(
        @Field("token") String token, @Field("nickname") String nickname);

    //&name=00
    @GET(GAME_SEARCH) Observable<ReqResult<Object>> searchByGameName(
        @Query("name") String gameName);

    //&catid=11&page=1&size=10
    @GET(CATEGORY_GAME_LIST) Observable<ReqResult<Object>> getCategoryGameList(
        @QueryMap Map<String, String> params);

    // &mobile=189xxxxxx&password=aaaaa&code=23141
    @POST(FORGET_PASSWORD) @FormUrlEncoded Observable<ReqResult<Object>> forgetPassword(
        @FieldMap Map<String, String> params);

    //&nickname=张三&openid=xxxxxxxxx&iconurl=http://aaaaa"
    @FormUrlEncoded @POST(WX_SIGN_IN) Observable<ReqResult<UserEntity>> wxSignIn(
        @Field("openid") String openid, @Field("nickname") String nickname,
        @Field("iconurl") String iconurl);

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

    @GET(CAPTCHA) Observable<ReqResult<String>> getCaptcha(@Query("mobile") String mobile,
        @Query("type") String type);
    //@GET(CAPTCHA) Observable<ReqResult<String>> getCaptcha(@Query("mobile") String mobile);

    //正在进行 的活动
    @GET(ACTIVITY_CURRENT)Observable<ReqResult<List<Object>>> getCurrentAcitivyList();
    //活动历史接口

    @GET(Activity_PAST)Observable<ReqResult<List<Object>>>getActivityList();
    // 活动 详情接口
    @GET(ACTIVITY_DETAIL)Observable<ReqResult<ActivitiesEntity>>getActyivityDetail(
            @QueryMap Map<String, String> params);

    // 活动排行榜的接口
    @GET(ACTIVITY_RANKING)Observable<ReqResult<List<Object>>>getActyivityRanking(
            @QueryMap Map<String, String> params);


    //礼包首页接口
    @GET(GIFT_Home)Observable<ReqResult<List<Object>>> getGiftList(
    @QueryMap Map<String, String> params);
    //礼包领取接口
    //h5.7676.com/mapiindex.php?m=gift&c=index&a=recGift&bid=50&token
   @GET(GIFT_GET)Observable<ReqResult<String>>  receiveGift(
           @QueryMap Map<String,String> params);
    //礼包详情界面
    @GET(GIFT_DETAIL)Observable<ReqResult<Test>> getGiftDetail(
            @QueryMap Map<String,String> params);



}
