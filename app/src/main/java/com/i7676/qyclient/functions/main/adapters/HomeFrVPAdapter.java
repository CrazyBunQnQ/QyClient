package com.i7676.qyclient.functions.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.i7676.qyclient.functions.main.home.ShowGameFragment;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/28.
 *
 * 主页 ViewPager 的 Adapter
 */
public class HomeFrVPAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ShowGameFragment> fragments;

    public HomeFrVPAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    @Override public Fragment getItem(int position) {
        //Bundle args = new Bundle();
        //switch (position) {
        //    case 0:
        //        args.putInt(ShowGameFragment.SHOW_CATEGORY_TYPE,
        //            ShowGameFragment.SHOW_CATEGORY_HOTTEST);
        //        break;
        //    case 1:
        //        args.putInt(ShowGameFragment.SHOW_CATEGORY_TYPE,
        //            ShowGameFragment.SHOW_CATEGORY_NEWEST);
        //        break;
        //}
        //return ShowGameFragment.create(args);
        return fragments.get(position);
    }

    public void setNewData(ArrayList<ShowGameFragment> fragments) {
        this.fragments.clear();
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }

    public void addFr(ShowGameFragment fragment) {
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override public int getCount() {
        return fragments.size();
    }
}
