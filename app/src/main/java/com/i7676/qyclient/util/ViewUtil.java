package com.i7676.qyclient.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.i7676.qyclient.R;
import com.i7676.qyclient.widgets.NonScrollableRecyclerView;

/**
 * Created by HCol on 2016/9/18.
 */
public class ViewUtil {

  public static PopupWindow createPopWindow(Context ctx, BaseQuickAdapter adapter) {
    // init content view
    final NonScrollableRecyclerView categoryList = new NonScrollableRecyclerView(ctx);
    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
    categoryList.setLayoutParams(lp);
    categoryList.setBackgroundColor(Color.WHITE);
    categoryList.setLayoutManager(new GridLayoutManager(ctx, 2));
    categoryList.setHasFixedSize(true);
    categoryList.setAdapter(adapter);

    // get window height
    Display display = ((AppCompatActivity) ctx).getWindowManager().getDefaultDisplay();
    DisplayMetrics dm = new DisplayMetrics();
    display.getMetrics(dm);
    int height = dm.heightPixels;
    int width = dm.widthPixels;

    // custom window display
    final PopupWindow popupWindow = new PopupWindow(categoryList);
    popupWindow.setWidth(width);
    popupWindow.setHeight(height);
    popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
    popupWindow.setBackgroundDrawable(ctx.getResources().getDrawable(R.drawable.pub_popwindow));
    return popupWindow;
  }
}
