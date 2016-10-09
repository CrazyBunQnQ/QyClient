package com.i7676.qyclient.functions.main;

import android.content.Context;
import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.functions.main.activity.ActivityFrPresenter;
import com.i7676.qyclient.functions.main.activity.ActivityFragment;
import com.i7676.qyclient.functions.main.adapters.ActivityFrAdapter;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GameCardAdapter;
import com.i7676.qyclient.functions.main.adapters.GameGridAdapter;
import com.i7676.qyclient.functions.main.adapters.GameHistoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GiftListAdapter;
import com.i7676.qyclient.functions.main.gift.GiftFragment;
import com.i7676.qyclient.functions.main.gift.GiftFtPresenter;
import com.i7676.qyclient.functions.main.home.HomeFrPresenter;
import com.i7676.qyclient.functions.main.home.HomeFragment;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import com.i7676.qyclient.functions.main.profile.ProfileFrPresenter;
import com.i7676.qyclient.functions.main.profile.ProfileFragment;
import dagger.Component;

/**
 * Created by Administrator on 2016/9/19.
 */
@PerActivity @Component(modules = MainAtyModule.class, dependencies = QyClientComponent.class)
public interface MainAtyComponent {

    void inject(MainActivity activity);

    void inject(MainAtyPresenter presenter);

    void inject(HomeFragment fragment);

    void inject(HomeFrPresenter presenter);

    void inject(ActivityFragment fragment);

    void inject(ActivityFrPresenter presenter);


    void inject(GiftFragment fragment);

    void inject(GiftFtPresenter presenter);

    void inject(ProfileFragment fragment);

    void inject(ProfileFrPresenter presenter);

    Context getMainAtyContext();

    MainActivity getMainAtySelf();

    MainAtyNavigator getNavigator();

    CategoryAdapter getCategoryAdapter();

    GameHistoryAdapter getGameHistoryAdapter();

    GameCardAdapter getGameCardAdapter();

    GameGridAdapter getGameGridAdapter();

    ActivityFrAdapter getActivityFrAdapter();


    GiftListAdapter  getGiftListAdapter();
}
