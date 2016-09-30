package com.i7676.qyclient.functions.main.home;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.EgretApiService;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.BannerEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.entity.HomeFrEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.MainAtyView;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.i7676.qyclient.rx.ServerCallbackHandler;
import com.i7676.qyclient.util.ColorConstants;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.orhanobut.logger.Logger;
import com.recker.flybanner.FlyBanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/19.
 */
public class HomeFrPresenter extends BasePresenter<HomeFrView>
    implements ObservableScrollView.OnScrollChangedListener {

    @Inject EgretApiService mEgretApiService;
    @Inject YNetApiService mYNetApiService;

    private Subscription topBannerSubscription;
    private Subscription RCMDBannerSubscription;
    private Subscription gCardsSubscription;
    private Subscription userPlayedHistory;
    private Subscription userPlayedHistorySubscription;
    private Subscription indexInfoSubscription;

    private static List<String> DEFAULT_BANNER_IMAGE = new ArrayList<String>() {
        {
            add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
            add("http://h5.7676.com/uploadfile/2016/0829/20160829062640102.jpg");
        }
    };

    private ArrayList<BannerEntity> topBanners = new ArrayList<>();
    private ArrayList<BannerEntity> RCMDBanners = new ArrayList<>();

    @Override protected void onWakeUp() {
        super.onWakeUp();
        toolbarSetup();
        //initTopBannerData();
        //initCategory();
        //initFstGCards();
        //initGameGrid();
        //initUserPlayedHistory();
        renderData2View();
    }

    @Override protected void onSleep() {
        super.onSleep();
        //doUnsubscribe(topBannerSubscription);
        //doUnsubscribe(RCMDBannerSubscription);
        //doUnsubscribe(gCardsSubscription);
        //doUnsubscribe(userPlayedHistory);
        doUnsubscribe(indexInfoSubscription);
    }

    void renderData2View() {
        Map<String, String> params = new HashMap<>();
        if (QyClient.curUser != null) {
            params.put("token", QyClient.curUser.getToken());
        }

        // 清空banner holder
        topBanners.clear();

        indexInfoSubscription = mYNetApiService.getIndexInfo(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(reqResult -> {
                if (reqResult.getRet() == 0) {
                    return reqResult.getData();
                } else {
                    throw new NullPointerException("Server error: [" + reqResult.getRet() + "]");
                }
            })
            .subscribe(new DefaultSubscriber<HomeFrEntity>() {
                @Override public void onStart() {
                    super.onStart();
                    Logger.i(">>> 首页信息获取成功,正在解析...");
                    getView().showDialog2User("加载中...");
                }

                @Override public void onNext(HomeFrEntity homeFrEntity) {
                    super.onNext(homeFrEntity);
                    // banner
                    topBanners.addAll(homeFrEntity.getBanner());
                    ArrayList<String> images = new ArrayList<>();
                    for (BannerEntity entity : topBanners) {
                        images.add(entity.getImageURL());
                    }
                    getView().setupTopBanner(images);
                    // played history
                    getView().setupUserPlayedHistory(homeFrEntity.getHistory());
                    // category
                    getView().setupCategory(homeFrEntity.getCategory());
                    // newest
                    getView().renderGameRanking(ShowGameFragment.SHOW_CATEGORY_NEWEST,
                        homeFrEntity.getNewgame());
                    // hottest
                    getView().renderGameRanking(ShowGameFragment.SHOW_CATEGORY_HOTTEST,
                        homeFrEntity.getHotgame());
                }

                @Override public void onError(Throwable e) {
                    super.onError(e);
                    Logger.i(">>> 首页信息获取成功,正在解析错误: " + e.getMessage());
                    //getView().closeDialog();
                    getView().serverFuckedUp();
                }

                @Override public void onCompleted() {
                    super.onCompleted();
                    Logger.i(">>> 首页信息获取成功,正在解析完成.");
                    getView().closeDialog();
                }
            });
    }

    void initUserPlayedHistory() {
        if (QyClient.curUser == null) return;

        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("size", "10");
        params.put("token", QyClient.curUser.getToken());

        userPlayedHistory = mYNetApiService.getUserPlayedGames(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(reqResult -> {
                if (reqResult.getRet() == 0) {
                    return reqResult.getData();
                } else {
                    throw new NullPointerException();
                }
            })
            .subscribe(getView()::setupUserPlayedHistory,
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    getView().closeDialog();
                },
                // complement
                () -> {
                    Logger.i(">>> onComplement: getGameList");
                    getView().closeDialog();
                });
    }

    private void doUnsubscribe(Subscription subscription) {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void toolbarSetup() {
        getView().showActionBar();
        getView().setActionBarTitle("主页");
        getView().setActionBarBackground(ColorConstants.TRANSPARENT);
        getView().setBottomBarIndex(MainAtyView.TAB_INDEX_HOME);
        getView().showOptionsMenu();
    }

    private void initTopBannerData() {
        // DialogStarted
        //getView().showDialog2User("加载数据中...");

        topBanners.clear();
        topBannerSubscription = mYNetApiService.getBanner()
            // 网络请求线程
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            // 请求错误处理
            .flatMap(new ServerCallbackHandler<>())
            // 数据分发
            .flatMap(this::collectBannerImgURLs).subscribe(
                // next
                getView()::setupTopBanner,
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    getView().closeDialog();
                },
                // complement
                () -> Logger.i(">>> onComplement"));
    }

    private void initRCMDBannerData() {
        RCMDBanners.clear();
        RCMDBanners.addAll(topBanners);
        RCMDBannerSubscription =
            Observable.just(RCMDBanners).observeOn(AndroidSchedulers.mainThread())
                // 数据分发
                .flatMap(bannerEntities -> {
                    ArrayList<String> images = new ArrayList<>();
                    for (BannerEntity entity : bannerEntities) {
                        images.add(entity.getImageURL());
                    }
                    return Observable.just(images);
                }).subscribe(
                // next
                getView()::setupRCMDBanner,
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    getView().closeDialog();
                },
                // complement
                () -> Logger.i(">>> onComplement"));
    }

    private Observable<List<String>> collectBannerImgURLs(List<BannerEntity> bannerEntities) {

        if (bannerEntities == null) return Observable.just(DEFAULT_BANNER_IMAGE);

        topBanners.addAll(bannerEntities);
        //initRCMDBannerData();
        ArrayList<String> images = new ArrayList<>();
        for (BannerEntity entity : topBanners) {
            images.add(entity.getImageURL());
        }
        return Observable.just(images);
    }

    //private void initCategory() {
    //    getView().setupCategory(MainAtyPresenter.CATEGORIES);
    //}

    private void initGameGrid() {
        gCardsSubscription = mEgretApiService.getGameList("20814")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(this::map2GameEntity)
            .flatMap(Observable::from)
            .subscribe(
                // next
                getView()::setupGameGrid,
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    getView().closeDialog();
                },
                // complement
                () -> {
                    Logger.i(">>> onComplement");
                    getView().closeDialog();
                });
    }

    private void initFstGCards() {
        gCardsSubscription = mEgretApiService.getGameList("20814")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(this::map2GameEntity)
            .flatMap(Observable::from)
            .take(20)
            .toList()
            .map(this::collectFstGCards)
            .take(3)
            .subscribe(
                // next
                getView()::setupFstGCards,
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    getView().closeDialog();
                },
                // complement
                () -> Logger.i(">>> onComplement"));
    }

    private List<GameEntity> map2GameEntity(Object obj) {
        JSONObject response = JSONObject.parseObject(obj.toString());
        if (response != null && "0".equals(String.valueOf(response.get("code")))) {
            List<GameEntity> tempList =
                JSONArray.parseArray(response.getString("game_list"), GameEntity.class);
            Collections.reverse(tempList);
            return tempList;
        } else {
            return null;
        }
    }

    private List<GameCardEntity> collectSndGCards(List<GameEntity> entities) {
        List<GameCardEntity> cardEntities = new ArrayList<>();
        cardEntities.add(new GameCardEntity("补充上线", entities));
        return cardEntities;
    }

    private List<GameCardEntity> collectFstGCards(List<GameEntity> entities) {
        String cardsTitles[] = { "最新上线", "热门小游戏", "热门网络游戏" };
        List<GameCardEntity> fstCardEntities = new ArrayList<>();
        for (String cardTitle : cardsTitles) {
            List<GameEntity> entityList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                entityList.add(entities.remove(i));
            }
            fstCardEntities.add(new GameCardEntity(cardTitle, entityList));
        }
        // 1秒之后再分发其他的卡片
        Observable.just(this.collectSndGCards(entities))
            .observeOn(AndroidSchedulers.mainThread())
            .delaySubscription(1000, TimeUnit.MILLISECONDS)
            .subscribe(
                // next
                getView()::setupSndGCards,
                // error
                throwable -> {
                    Logger.e(">>> onError:" + throwable.getMessage());
                    getView().closeDialog();
                },
                // complement
                () -> Logger.i(">>> onComplement"));
        return fstCardEntities;
    }

    // ****************************************************************************   Toolbar 渐变处理

    /**
     * 四舍五入运算
     *
     * @param tempColor 被整除的数
     * @param mod 模
     */
    private int calcColorOffset(int tempColor, int mod) {
        // 进位标识
        int carryFlag = 0;
        // 余数
        int x = tempColor % mod;
        // 余数占模的百分比
        float modPercent = x / mod;
        // 5入
        if (modPercent > 0.5f) {
            ++carryFlag;
        }
        return ((tempColor / mod) + carryFlag) * mod;
    }

    @Override public void onScrollChanged(int l, int r, int oldl, int oldr) {
        Logger.i(">>> {l:" + l + ",r:" + r + ",oldl:" + oldl + ",oldr:" + oldr + "}");
        if (getView() != null) {
            boolean scrollDown = r - oldr > 0;
            if (r <= 0) {
                getView().setActionBarBackground(ColorConstants.TRANSPARENT);
            } else if (r > 0 && r < 360) {
                float percent = r / 360f;
                int tempColor = Float.valueOf(ColorConstants.TOTAL_OFFSET * percent).intValue();
                //Log.d(TAG, "percent:" + percent + "%, tempColor:" + tempColor);
                // 下滑
                if (scrollDown) {
                    getView().setActionBarBackground(
                        ColorConstants.TRANSPARENT + calcColorOffset(tempColor,
                            ColorConstants.OFFSET));
                }
                // 上拉
                else {
                    getView().setActionBarBackground(
                        ColorConstants.TRANSPARENT - calcColorOffset(tempColor,
                            ColorConstants.OFFSET));
                }
            } else if (r >= 360) {
                getView().setActionBarBackground(ColorConstants.PRIMARY_COLOR);
            }
        }
    }

    // 轮播图片监听器
    private final FlyBanner.OnItemClickListener topBannerListener = position -> {
        if (topBanners.isEmpty()) {
            Logger.w(">>> Click position is [" + position + "] and topBanners is empty!");
            return;
        }
        BannerEntity target = topBanners.get(position);
        Logger.i(">>> topBannerInfo: " + target.getDes());

        if (QyClient.curUser == null) {
            getView().toast2User("请先登录");
        } else {
            if (target.getType() == HomeFrView.BANNER_TYPE_H5) {
                getView().go2PlayH5Game(target.getRemark());
            }
        }
    };

    private final FlyBanner.OnItemClickListener RCMDBannerListener = position -> {
        if (RCMDBanners.isEmpty()) {
            Logger.w(">>> Click position is [" + position + "] and RCMDBannerInfo is empty!");
            return;
        }
        Logger.i(">>> RCMDBannerInfo: " + RCMDBanners.get(position).getDes());
    };

    FlyBanner.OnItemClickListener getTopBannerListener() {
        return topBannerListener;
    }

    FlyBanner.OnItemClickListener getRCMDBannerListener() {
        return RCMDBannerListener;
    }
}
