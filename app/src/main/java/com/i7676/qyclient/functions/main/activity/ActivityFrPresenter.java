package com.i7676.qyclient.functions.main.activity;

import com.i7676.qyclient.constants.ColorConstants;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.MainAtyView;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ActivityFrPresenter extends BasePresenter<ActivityFrView> {

  @Inject MainActivity mainActivity;

  @Override protected void onWakeUp() {
    super.onWakeUp();
    toolbarSetup();
  }

  private void toolbarSetup() {
    mainActivity.getPresenter().getView().setTitle("活动");
    mainActivity.getPresenter().getView().setToolbarBkg(ColorConstants.PRIMARY_COLOR);
    mainActivity.getPresenter().getView().setBottomBarSelectedIndex(MainAtyView.TAB_INDEX_ACTIVITY);

  }
}
