package com.i7676.qyclient.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ObservableScrollView extends ScrollView {

  public interface OnScrollChangedListener {
    void onScrollChanged(int l, int t, int oldl, int oldt);
  }

  private OnScrollChangedListener mScrollChangedListener;

  public void setmScrollChangedListener(OnScrollChangedListener mScrollChangedListener) {
    this.mScrollChangedListener = mScrollChangedListener;
  }

  public ObservableScrollView(Context context) {
    super(context);
  }

  public ObservableScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    super.onScrollChanged(l, t, oldl, oldt);
    if (mScrollChangedListener != null) {
      mScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
    }
  }
}
