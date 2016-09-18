package com.i7676.qyclient.function.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i7676.qyclient.function.main.presenter.BasePresenter;
import com.i7676.qyclient.function.main.view.BaseView;
import com.i7676.qyclient.util.AnnotationUtil;

import net.grandcentrix.thirtyinch.TiFragment;

/**
 * Created by HCol on 2016/9/13.
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView>
    extends TiFragment<P, V> implements BaseView {

  protected ToolbarAgent mToolbarAgent;

  public void registerToolbarAgent(ToolbarAgent mToolbarAgent) {
    this.mToolbarAgent = mToolbarAgent;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(AnnotationUtil.getLayoutResId(getClass()), container, false);
    initViews(rootView);
    return rootView;
  }

  @Override public void initViews() {
    // empty
  }

  @Override public void onResume() {
    super.onResume();
    initHostToolbar();
  }

  protected void initHostToolbar() {
    if (mToolbarAgent != null) {
      mToolbarAgent.setBgColor(0xFFFF6F00);
      mToolbarAgent.opMenuVisibility(true);
    }
  }
}
