package com.i7676.qyclient.functions.login.rof;

import android.support.annotation.NonNull;
import android.view.View;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/9/23.
 *
 * 登录-快速注册
 */
public class QuickRegFragment extends BaseFragment<QuickRegFrPresenter, QuickRegFrView>
    implements QuickRegFrView {
    @Override protected void initRootViews(View rootView) {

    }

    @NonNull @Override public QuickRegFrPresenter providePresenter() {
        return new QuickRegFrPresenter();
    }
}
