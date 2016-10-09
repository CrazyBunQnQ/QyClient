package com.i7676.qyclient.functions.main.activity.detail;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseActivity;
import com.i7676.qyclient.functions.main.activity.detail.childFragment.ActivityDetailFrgment;
import com.i7676.qyclient.functions.main.activity.detail.childFragment.RankingListFrgment;
import com.i7676.qyclient.functions.main.adapters.ActivitiespagerAdapter;
import com.i7676.qyclient.widgets.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@Layout(R.layout.activity_activites_detail)
public class ActyDetaiActivity extends BaseActivity<ActyDetailPresenter,ActyDetailView>implements  ActyDetailView {

    private MyViewPager myViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    private ActyDetailComponent mActyDetailcomponent;


    @Override
    public void initViews() {

        setUpInject();
        mTitle.add("活动详情");
        mTitle.add("本期活动排行榜");
        mFragment.add(new ActivityDetailFrgment());
        mFragment.add(new RankingListFrgment());
        myViewPager= (MyViewPager) findViewById(R.id.activites_viewpager);
        myViewPager.setAdapter(new ActivitiespagerAdapter(getSupportFragmentManager(),mTitle,mFragment)) ;

        mTabLayout= (TabLayout) findViewById(R.id.activities_tab);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        mTabLayout.addTab(mTabLayout.newTab().setText("活动详情"));
        mTabLayout.addTab(mTabLayout.newTab().setText("本期活动排行榜" ));
        mTabLayout.setTabTextColors(Color.BLACK,Color.parseColor("#FF6F00"));
        mTabLayout.setupWithViewPager(myViewPager);//

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setUpInject() {
        mActyDetailcomponent= DaggerActyDetailComponent.builder().qyClientComponent(((QyClient)getApplication()).getClientComponent()).build();
        mActyDetailcomponent.inject(getPresenter());


    }

    @NonNull
    @Override
    public ActyDetailPresenter providePresenter() {
        return new ActyDetailPresenter();
    }
}
