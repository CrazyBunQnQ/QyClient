package com.i7676.qyclient.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/8/30.
 *
 * CAN NOT USE RECYCLER VIEW TOUCH EVENT!!!
 * CAN NOT USE RECYCLER VIEW TOUCH EVENT!!!
 * CAN NOT USE RECYCLER VIEW TOUCH EVENT!!!
 */
public class NonScrollableRecyclerView extends RecyclerView {
    public NonScrollableRecyclerView(Context context) {
        super(context);
    }

    public NonScrollableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }
}
