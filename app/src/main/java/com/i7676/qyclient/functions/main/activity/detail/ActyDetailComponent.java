package com.i7676.qyclient.functions.main.activity.detail;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.functions.main.activity.detail.childFragment.RankingFragment.RanckPresenter;
import com.i7676.qyclient.functions.main.activity.detail.childFragment.detaialfragment.ActivityDetailPresenter;

import dagger.Component;

@PerActivity
@Component(dependencies = QyClientComponent.class)

public interface ActyDetailComponent   {

    void inject(ActyDetailPresenter presenter);

    void inject(RanckPresenter presenter);//注入排行榜的碎片

    void inject(ActivityDetailPresenter presenter); // 注入详情的碎片
}