package com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.childfragment.pastonlistfragment.OnListPresenter;
import com.i7676.qyclient.functions.main.activity.pastactivity.Pastdetail.childfragment.pastrankFragment.PastRankPresenter;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/17.
 */

@PerActivity
@Component(dependencies = QyClientComponent.class)
public interface PastDetailComponent {

  void inject (PastDetailPresenter presenter);

  void inject(PastRankPresenter presenter);

  void inject(OnListPresenter presenter);



}
