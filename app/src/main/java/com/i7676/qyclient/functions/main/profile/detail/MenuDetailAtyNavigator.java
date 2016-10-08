package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/10/8.
 */

class MenuDetailAtyNavigator {

    private MenuDetailActivity mAty;

    MenuDetailAtyNavigator(Context context) {
        this.mAty = (MenuDetailActivity) context;
    }

    private void transform(BaseFragment fragment) {
        mAty.getSupportFragmentManager().beginTransaction()
            // 添加Fragment
            .replace(mAty.getContainerResId(), fragment, fragment.getClass().getSimpleName())
            // 提交
            .commit();
    }
}
