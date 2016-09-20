package com.i7676.qyclient.functions.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.entity.GameCardEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GameCardAdapter;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;
import com.i7676.qyclient.widgets.ObservableScrollView;
import com.recker.flybanner.FlyBanner;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
@Layout(R.layout.fragment_home) public class HomeFragment
    extends BaseFragment<HomeFrPresenter, HomeFrView> implements HomeFrView {

  public static HomeFragment create(@Nullable Bundle args) {
    final HomeFragment fragment = new HomeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  // views
  private ObservableScrollView rootScroll;
  private FlyBanner topBanner;
  private FlyBanner RCMDBanner;
  private NonScrollableRecyclerView categoryRecyclerView;
  private NonScrollableRecyclerView fstGCardsRecyclerView;
  private NonScrollableRecyclerView sndGCardsRecyclerView;

  @Inject CategoryAdapter categoryAdapter;
  @Inject GameCardAdapter fstGCardAdapter;
  @Inject GameCardAdapter sndGCardAdapter;

  @Override protected void initRootViews(View rootView) {
    initInject();

    rootScroll = (ObservableScrollView) rootView.findViewById(R.id.rootScroll);
    rootScroll.setmScrollChangedListener(getPresenter());

    topBanner = (FlyBanner) rootView.findViewById(R.id.topBanner);
    RCMDBanner = (FlyBanner) rootView.findViewById(R.id.rcmdBanner);

    topBanner.setOnItemClickListener(getPresenter().getTopBannerListener());
    RCMDBanner.setOnItemClickListener(getPresenter().getRCMDBannerListener());

    categoryRecyclerView =
        (NonScrollableRecyclerView) rootView.findViewById(R.id.categoryRecyclerView);
    fstGCardsRecyclerView =
        (NonScrollableRecyclerView) rootView.findViewById(R.id.fstGCardsRecyclerView);
    sndGCardsRecyclerView =
        (NonScrollableRecyclerView) rootView.findViewById(R.id.sndGCardsRecyclerView);

    categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
    categoryRecyclerView.setAdapter(categoryAdapter);

    fstGCardsRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    fstGCardsRecyclerView.setAdapter(fstGCardAdapter);

    sndGCardsRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    sndGCardsRecyclerView.setAdapter(sndGCardAdapter);
  }

  @Override public void setupTopBanner(List<String> bannerImgURLs) {
    topBanner.setImagesUrl(bannerImgURLs);
  }

  @Override public void setupRCMDBanner(List<String> bannerImgURLs) {
    if (bannerImgURLs.isEmpty()) {
      RCMDBanner.setVisibility(View.GONE);
    } else {
      RCMDBanner.setImagesUrl(bannerImgURLs);
    }
  }

  @Override public void setupFstGCards(List<GameCardEntity> fstGCards) {
    fstGCardAdapter.setNewData(fstGCards);
  }

  @Override public void setupSndGCards(List<GameCardEntity> sndGCards) {
    sndGCardAdapter.setNewData(sndGCards);
  }

  @Override public void setupCategory(List<CategoryEntity> categoryEntities) {
    categoryAdapter.setNewData(categoryEntities);
  }

  @NonNull @Override public HomeFrPresenter providePresenter() {
    return new HomeFrPresenter();
  }

  private void initInject() {
    ((MainActivity) getActivity()).getAtyComponent().inject(this);
    ((MainActivity) getActivity()).getAtyComponent().inject(getPresenter());
  }
}
