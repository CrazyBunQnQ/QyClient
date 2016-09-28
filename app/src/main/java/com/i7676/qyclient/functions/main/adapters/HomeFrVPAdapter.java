package com.i7676.qyclient.functions.main.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.i7676.qyclient.functions.main.home.ShowGameFragment;

/**
 * Created by Administrator on 2016/9/28.
 *
 * 主页 ViewPager 的 Adapter
 */
public class HomeFrVPAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    public HomeFrVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                args.putInt(ShowGameFragment.SHOW_CATEGORY_TYPE,
                    ShowGameFragment.SHOW_CATEGORY_HOTTEST);
                break;
            case 1:
                args.putInt(ShowGameFragment.SHOW_CATEGORY_TYPE,
                    ShowGameFragment.SHOW_CATEGORY_NEWEST);
                break;
        }
        return ShowGameFragment.create(args);
    }

    @Override public int getCount() {
        return COUNT;
    }
}
