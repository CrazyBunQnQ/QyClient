package com.i7676.qyclient.functions.main.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.h5game.PlayGameActivity;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.GameHistoryAdapter;
import com.i7676.qyclient.functions.main.adapters.HomeFrVPAdapter;
import com.i7676.qyclient.widgets.NonScrollableViewPager;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.recker.flybanner.FlyBanner;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
@Layout(R.layout.fragment_home_grid) public class HomeFragment
    extends BaseFragment<HomeFrPresenter, HomeFrView> implements HomeFrView {

    public static HomeFragment create(@Nullable Bundle args) {
        final HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // views
    private ObservableScrollView rootScroll;
    private FlyBanner topBanner;
    //private FlyBanner RCMDBanner;
    //private NonScrollableRecyclerView categoryRecyclerView;
    //private NonScrollableRecyclerView fstGCardsRecyclerView;
    //private NonScrollableRecyclerView sndGCardsRecyclerView;
    private RecyclerView gameHistory;
    private View gameHistoryHead;
    private NonScrollableViewPager viewPager;
    private NavigationTabStrip navigationTabStrip;

    @Inject GameHistoryAdapter gameHistoryAdapter;
    private HomeFrVPAdapter homeFrVPAdapter;
    //@Inject GameCardAdapter fstGCardAdapter;
    //@Inject GameCardAdapter sndGCardAdapter;
    //@Inject GameGridAdapter gameGridAdapter;

    @Override protected void initRootViews(View rootView) {
        rootScroll = (ObservableScrollView) rootView.findViewById(R.id.rootScroll);
        rootScroll.setmScrollChangedListener(getPresenter());

        topBanner = (FlyBanner) rootView.findViewById(R.id.topBanner);
        //RCMDBanner = (FlyBanner) rootView.findViewById(R.id.rcmdBanner);

        topBanner.setOnItemClickListener(getPresenter().getTopBannerListener());
        //RCMDBanner.setOnItemClickListener(getPresenter().getRCMDBannerListener());

        //categoryRecyclerView =
        //    (NonScrollableRecyclerView) rootView.findViewById(categoryRecyclerView);
        //fstGCardsRecyclerView =
        //    (NonScrollableRecyclerView) rootView.findViewById(R.id.fstGCardsRecyclerView);
        //sndGCardsRecyclerView =
        //    (NonScrollableRecyclerView) rootView.findViewById(sndGCardsRecyclerView);
        //categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //categoryRecyclerView.setAdapter(categoryAdapter);

        //fstGCardsRecyclerView.setLayoutManager(
        //    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //fstGCardsRecyclerView.setAdapter(fstGCardsRecyclerView);
        //sndGCardsRecyclerView.setLayoutManager(
        //    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //sndGCardsRecyclerView.setAdapter(sndGCardAdapter);

        gameHistory = (RecyclerView) rootView.findViewById(R.id.rv_game_history);
        gameHistoryHead = rootView.findViewById(R.id.view_game_history_head);
        gameHistory.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        gameHistory.setAdapter(gameHistoryAdapter);

        navigationTabStrip = (NavigationTabStrip) rootView.findViewById(R.id.nts);
        viewPager = (NonScrollableViewPager) rootView.findViewById(R.id.vp_home);
        homeFrVPAdapter = new HomeFrVPAdapter(getChildFragmentManager());
        viewPager.setAdapter(homeFrVPAdapter);
        viewPager.setOffscreenPageLimit(2);

        navigationTabStrip.setTitles("热力推荐", "最新上线");
        navigationTabStrip.setViewPager(viewPager);
    }

    @Override public void setupTopBanner(List<String> bannerImgURLs) {
        topBanner.setImagesUrl(bannerImgURLs);
    }

    @Override public void setupRCMDBanner(List<String> bannerImgURLs) {
        //if (bannerImgURLs != null && !bannerImgURLs.isEmpty()) {
        //    RCMDBanner.setVisibility(View.VISIBLE);
        //    RCMDBanner.setImagesUrl(bannerImgURLs);
        //}
    }

    @Override public void setupFstGCards(List<GameCardEntity> fstGCards) {
        //fstGCardAdapter.setNewData(fstGCards);
        //this.closeDialog();
    }

    @Override public void setupSndGCards(List<GameCardEntity> sndGCards) {
        //if (sndGCards == null) return;
        //sndGCardAdapter.setNewData(sndGCards);
    }

    @Override public void setupCategory(List<CategoryEntity> categoryEntities) {
        ((MainActivity) getActivity()).getPresenter()
            .getView()
            .setupCategoryPopupWindow(categoryEntities);
    }

    @Override public void setupGameGrid(GameEntity gameEntity) {
        //gameGridAdapter.setNewData(gameEntities);
        //gameGridAdapter.add(0, gameEntity);
    }

    @NonNull @Override public HomeFrPresenter providePresenter() {
        return new HomeFrPresenter();
    }

    @Override protected void setupInject() {
        ((MainActivity) getActivity()).getAtyComponent().inject(this);
        ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
    }

    @Override public void setActionBarTitle(String titleText) {
        ((MainActivity) getActivity()).getPresenter().getView().setTitle(titleText);
    }

    @Override public void setupUserPlayedHistory(List<RankingGameEntity> gameEntities) {
        if (gameEntities != null && !gameEntities.isEmpty()) {
            gameHistory.setVisibility(View.VISIBLE);
            gameHistoryHead.setVisibility(View.VISIBLE);
            gameHistoryAdapter.setNewData(gameEntities);
        } else {
            gameHistory.setVisibility(View.GONE);
            gameHistoryHead.setVisibility(View.GONE);
        }
    }

    @Override public void showActionBar() {
        ((MainActivity) getActivity()).getPresenter().getView().showActionBar();
    }

    @Override public void setActionBarBackground(int color) {
        ((MainActivity) getActivity()).getPresenter().getView().setToolbarBkg(color);
    }

    @Override public void setBottomBarIndex(int index) {
        ((MainActivity) getActivity()).getPresenter().getView().setBottomBarIndex(index);
    }

    @Override public void showOptionsMenu() {
        ((MainActivity) getActivity()).getPresenter().getView().showOptionsMenu();
    }

    @Override public void showDialog2User(String msg) {
        ((MainActivity) getActivity()).showDialog2User(msg);
    }

    @Override public void closeDialog() {
        ((MainActivity) getActivity()).closeDialog();
    }

    @Override
    public void renderGameRanking(int showCategoryType, ArrayList<RankingGameEntity> data) {
        Bundle args = new Bundle();
        args.putInt(ShowGameFragment.SHOW_CATEGORY_TYPE, showCategoryType);
        args.putParcelableArrayList(ShowGameFragment.SHOW_DATA, data);
        homeFrVPAdapter.addFr(ShowGameFragment.create(args));
    }

    @Override public void serverFuckedUp() {
        new Thread() {
            @Override public void run() {
                super.run();
                int i = 10;
                while (i-- > 0) {
                    Message mMessage = Message.obtain();
                    mMessage.what = SERVER_FUCKED_UP;
                    mMessage.arg1 = i;
                    mUIHandler.sendMessage(mMessage);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override public void toast2User(String msg) {
        ((MainActivity) getActivity()).toast2User(msg, Toast.LENGTH_SHORT);
    }

    @Override public void go2PlayH5Game(String url) {
        Bundle args = new Bundle();
        args.putString(PlayGameActivity.GAME_URL, url + "&token=" + QyClient.curUser.getToken());
        startActivity(PlayGameActivity.buildIntent(getContext(), args));
    }

    private static final int SERVER_FUCKED_UP = 0;

    private Handler mUIHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SERVER_FUCKED_UP:
                    ((MainActivity) getActivity()).showDialog2User(
                        "请检查网络状态,或者服务器已爆炸，APP将在" + msg.arg1 + "秒之后自毁...");
                    break;
            }

            if (msg.arg1 <= 0) {
                System.exit(0);
            }
        }
    };
}
