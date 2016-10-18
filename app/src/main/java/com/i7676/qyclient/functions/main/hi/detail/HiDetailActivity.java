package com.i7676.qyclient.functions.main.hi.detail;

import android.support.annotation.NonNull;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.OneHasToolbarActivity;

/**
 * Created by Administrator on 2016/10/18.
 */
@Layout(R.layout.activity_toolbar) public class HiDetailActivity
    extends OneHasToolbarActivity<HiDetailAtyPresetner, HiDetailAtyView> {

    @Override public void initViews() {
        super.initViews();
    }

    @NonNull @Override public HiDetailAtyPresetner providePresenter() {
        return new HiDetailAtyPresetner();
    }
}
