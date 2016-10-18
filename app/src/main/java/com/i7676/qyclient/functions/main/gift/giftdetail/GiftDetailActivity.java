package com.i7676.qyclient.functions.main.gift.giftdetail;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.Test;
import com.i7676.qyclient.functions.BaseActivity;
import com.i7676.qyclient.functions.main.adapters.GiftpagerAdapter;
import com.i7676.qyclient.functions.main.gift.giftdetail.childfragment.GiftDetailFragment;
import com.i7676.qyclient.functions.main.gift.giftdetail.childfragment.GiftOtherFragment;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import com.i7676.qyclient.widgets.MyViewPager;
import com.i7676.qyclient.widgets.RoundedRectProgressBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */

@Layout(R.layout.activity_gift_detail)
public class GiftDetailActivity  extends BaseActivity<GiftDetailPresenter,GiftDetailView> implements  GiftDetailView{

private GiftDetailComponent mGiftdetialcompoent;


    private MyViewPager myViewPager;
    private TabLayout mTabLayout;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    private TextView  tv_gamename, tv_name, tv_reedemcode;
    private RoundedRectProgressBarView mProgress;
    private AutoLoadImageView gift_icon;

    @Override
    public void initViews() {
        //进行注入
        setUpInject();
        //礼包详情
         tv_name = (TextView) findViewById(R.id.tvdetail_gift_name); // 礼包名称
        tv_gamename= (TextView) findViewById(R.id.tv_gift_gamename);  // 游戏名称
        tv_reedemcode = (TextView) findViewById(R.id.tv_RedeemCode); //兑换码
        mProgress = (RoundedRectProgressBarView) findViewById(R.id.probar); // 进度条
        gift_icon= (AutoLoadImageView) findViewById(R.id.gift_detail_img); // icon



        // 礼包内的两个碎片
        mTitle.add("礼包详情");
        mTitle.add("相关礼包");
        mFragment.add(new GiftDetailFragment());
        mFragment.add(new GiftOtherFragment());
        myViewPager= (MyViewPager) findViewById(R.id.Gift_viewpager);
        myViewPager.setAdapter(new GiftpagerAdapter(getSupportFragmentManager(),mTitle,mFragment)) ;
        mTabLayout= (TabLayout) findViewById(R.id.Gift_tab);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        mTabLayout.addTab(mTabLayout.newTab().setText("礼包详情"));
        mTabLayout.addTab(mTabLayout.newTab().setText("相关礼包" ));
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
        mGiftdetialcompoent= DaggerGiftDetailComponent.builder()
                .qyClientComponent(((QyClient) getApplication()).getClientComponent())
                .build();
        mGiftdetialcompoent.inject(getPresenter());

    }
//    private void setupInject() {
//        atyComponent = DaggerGameListAtyComponent.builder()
//                .qyClientComponent(((QyClient) getApplication()).getClientComponent())
//                .build();
//
//        atyComponent.inject(getPresenter());
//    }

    @NonNull
    @Override
    public GiftDetailPresenter providePresenter() {
        return new GiftDetailPresenter(getIntent());
    }



    @Override
    public void addGiftitem(Test mTest) {
       tv_name.setText(mTest.getDetail().getCatdir());
        tv_gamename.setText(mTest.getDetail().getCatname());
       // tv_reedemcode.setText(mTest.getDetail().g);  兑换码
        mProgress.setProgress(mTest.getDetail().getRemain());
        gift_icon.setImageUrlAndAuthorInfo(mTest.getDetail().getIcon(),null);


    }
}
