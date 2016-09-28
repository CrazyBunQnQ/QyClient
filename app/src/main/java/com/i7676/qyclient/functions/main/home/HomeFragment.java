package com.i7676.qyclient.functions.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.entity.RankingGameEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GameHistoryAdapter;
import com.i7676.qyclient.functions.main.adapters.HomeFrVPAdapter;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.i7676.qyclient.widgets.NonScrollableViewPager;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.recker.flybanner.FlyBanner;
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
    private NonScrollableRecyclerView gameHistory;
    private NonScrollableViewPager viewPager;
    private NavigationTabStrip navigationTabStrip;

    @Inject CategoryAdapter categoryAdapter;
    @Inject GameHistoryAdapter gameHistoryAdapter;
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

        gameHistory = (NonScrollableRecyclerView) rootView.findViewById(R.id.rv_game_history);
        gameHistory.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        gameHistory.setAdapter(gameHistoryAdapter);

        navigationTabStrip = (NavigationTabStrip) rootView.findViewById(R.id.nts);
        viewPager = (NonScrollableViewPager) rootView.findViewById(R.id.vp_home);
        viewPager.setAdapter(new HomeFrVPAdapter(getChildFragmentManager()));
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
        categoryAdapter.setNewData(categoryEntities);
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
            gameHistoryAdapter.addData(gameEntities);
        } else {
            gameHistory.setVisibility(View.GONE);
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
}
