package com.i7676.qyclient.functions.main.activity;

import com.i7676.qyclient.util.ColorConstants;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.MainAtyView;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ActivityFrPresenter extends BasePresenter<ActivityFrView> {

  @Inject MainActivity mainActivity;

  @Override protected void onWakeUp() {
    super.onWakeUp();
    toolbarSetup();

    initActivityFrData();
  }

  private void initActivityFrData() {
    Observable.from(new String[] { "1", "2", "3" })
        .observeOn(AndroidSchedulers.mainThread())
        .toList()
        .subscribe(getView()::setupActivityData);
  }

  private void toolbarSetup() {
    mainActivity.getPresenter().getView().setTitle("活动");
    mainActivity.getPresenter().getView().setToolbarBkg(ColorConstants.PRIMARY_COLOR);
    mainActivity.getPresenter().getView().setBottomBarSelectedIndex(MainAtyView.TAB_INDEX_ACTIVITY);
    mainActivity.getPresenter().getView().hideOptionsMenu();
  }
}
