package com.i7676.qyclient.function.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i7676.qyclient.util.AnnotationUtil;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class ToolbarInteractorFragment extends Fragment {
    protected ToolbarAgent mToolbarAgent;

    public void registerToolbarAgent(ToolbarAgent mToolbarAgent) {
        this.mToolbarAgent = mToolbarAgent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(AnnotationUtil.getLayoutResId(getClass()), container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initHostToolbar();
    }

    protected abstract void initView(View view);

    protected void initHostToolbar() {
        if (mToolbarAgent != null) {
            mToolbarAgent.setBgColor(0xFFFF6F00);
            mToolbarAgent.opMenuVisibility(true);
        }
    }
}
