package com.i7676.qyclient.functions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.i7676.qyclient.util.AnnotationUtil;
import net.grandcentrix.thirtyinch.TiFragment;

/**
 * Created by Administrator on 2016/9/19.
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView>
    extends TiFragment<P, V> implements BaseView {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInject();
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View rootView =
            inflater.inflate(AnnotationUtil.getLayoutResId(getClass()), container, false);
        initRootViews(rootView);
        return rootView;
    }

    @Override public void initViews() {
        // empty
    }

    protected abstract void initRootViews(View rootView);

    protected void setupInject() {
        // empty is better for everyone
    }
}
