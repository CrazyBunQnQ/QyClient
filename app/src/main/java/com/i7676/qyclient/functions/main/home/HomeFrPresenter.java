package com.i7676.qyclient.functions.main.home;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.i7676.qyclient.constants.ColorConstants;
import com.i7676.qyclient.entity.BannerEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.MainAtyPresenter;
import com.i7676.qyclient.functions.main.MainAtyView;
import com.i7676.qyclient.net.EgretApiService;
import com.i7676.qyclient.net.YNetApiService;
import com.i7676.qyclient.rx.ServerCallbackHandler;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.orhanobut.logger.Logger;
import com.recker.flybanner.FlyBanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/19.
 */
public class HomeFrPresenter extends BasePresenter<HomeFrView>
    implements ObservableScrollView.OnScrollChangedListener {

  @Inject EgretApiService mEgretApiService;
  @Inject YNetApiService mYNetApiService;
  @Inject MainActivity mainActivity;

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

    initTopBannerData();
    initCategory();
    initFstGCards();
  }

  private void toolbarSetup() {
    mainActivity.getPresenter().getView().setTitle("主页");
    mainActivity.getPresenter().getView().setToolbarBkg(ColorConstants.TRANSPARENT);
    mainActivity.getPresenter().getView().setBottomBarSelectedIndex(MainAtyView.TAB_INDEX_HOME);
    mainActivity.getPresenter().getView().showOptionsMenu();
  }

  private void initTopBannerData() {
    topBanners.clear();
    mYNetApiService.getBanner()
        // 网络请求线程
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 请求错误处理
        .flatMap(new ServerCallbackHandler<>())
        // 错误分发
        //.onErrorResumeNext()
        // 数据分发
        .flatMap(this::collectBannerImgURLs).subscribe(getView()::setupTopBanner);
  }

  private void initRCMDBannerData() {
    RCMDBanners.clear();
    RCMDBanners.addAll(topBanners);
    Observable.just(RCMDBanners)
        .doOnError(this::errorHandler)
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(bannerEntities -> {
          ArrayList<String> images = new ArrayList<>();
          for (BannerEntity entity : bannerEntities) {
            images.add(entity.getImageURL());
          }
          return Observable.just(images);
        })
        .subscribe(getView()::setupRCMDBanner);
  }

  private Observable<List<String>> collectBannerImgURLs(List<BannerEntity> bannerEntities) {

    if (bannerEntities == null) return Observable.just(DEFAULT_BANNER_IMAGE);

    topBanners.addAll(bannerEntities);
    initRCMDBannerData();
    ArrayList<String> images = new ArrayList<>();
    for (BannerEntity entity : topBanners) {
      images.add(entity.getImageURL());
    }
    return Observable.just(images);
  }

  private void initCategory() {
    getView().setupCategory(MainAtyPresenter.CATEGORIES);
  }

  private void initFstGCards() {
    mEgretApiService.getGameList("20814")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError(this::errorHandler)
        .map(this::map2GameEntity)
        .flatMap(Observable::from)
        .take(20)
        .toList()
        .map(this::collectFstGCards)
        .take(3)
        .subscribe(getView()::setupFstGCards);
  }

  private void errorHandler(Throwable throwable) {
    Logger.w(">>> throwable: " + throwable.getMessage());
  }

  private List<GameEntity> map2GameEntity(Object obj) {
    JSONObject response = JSONObject.parseObject(obj.toString());
    if (response != null && "0".equals(String.valueOf(response.get("code")))) {
      return JSONArray.parseArray(response.getString("game_list"), GameEntity.class);
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
        .subscribe(getView()::setupSndGCards);
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
    if (mainActivity.getPresenter().getView() != null) {
      boolean scrollDown = r - oldr > 0;
      if (r <= 0) {
        mainActivity.getPresenter().getView().setToolbarBkg(ColorConstants.TRANSPARENT);
      } else if (r > 0 && r < 360) {
        float percent = r / 360f;
        int tempColor = Float.valueOf(ColorConstants.TOTAL_OFFSET * percent).intValue();
        //Log.d(TAG, "percent:" + percent + "%, tempColor:" + tempColor);
        // 下滑
        if (scrollDown) {
          mainActivity.getPresenter()
              .getView()
              .setToolbarBkg(
                  ColorConstants.TRANSPARENT + calcColorOffset(tempColor, ColorConstants.OFFSET));
        }
        // 上拉
        else {
          mainActivity.getPresenter()
              .getView()
              .setToolbarBkg(
                  ColorConstants.TRANSPARENT - calcColorOffset(tempColor, ColorConstants.OFFSET));
        }
      } else if (r >= 360) {
        mainActivity.getPresenter().getView().setToolbarBkg(ColorConstants.PRIMARY_COLOR);
      }
    }
  }

  // 轮播图片监听器
  private final FlyBanner.OnItemClickListener topBannerListener = position -> {
    if (topBanners.isEmpty()) {
      Logger.w(">>> Click position is [" + position + "] and topBanners is empty!");
      return;
    }
    Logger.i(">>> topBannerInfo: " + topBanners.get(position).getDes());
  };

  private final FlyBanner.OnItemClickListener RCMDBannerListener = position -> {
    if (RCMDBanners.isEmpty()) {
      Logger.w(">>> Click position is [" + position + "] and RCMDBannerInfo is empty!");
      return;
    }
    Logger.i(">>> RCMDBannerInfo: " + RCMDBanners.get(position).getDes());
  };

  public FlyBanner.OnItemClickListener getTopBannerListener() {
    return topBannerListener;
  }

  public FlyBanner.OnItemClickListener getRCMDBannerListener() {
    return RCMDBannerListener;
  }
}
