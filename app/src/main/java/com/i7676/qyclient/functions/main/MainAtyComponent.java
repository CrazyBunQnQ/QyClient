package com.i7676.qyclient.functions.main;

import android.content.Context;
import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PreActivity;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GameCardAdapter;
import com.i7676.qyclient.functions.main.home.HomeFrPresenter;
import com.i7676.qyclient.functions.main.home.HomeFragment;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import dagger.Component;

/**
 * Created by Administrator on 2016/9/19.
 */
@PreActivity @Component(modules = MainAtyModule.class, dependencies = QyClientComponent.class)
public interface MainAtyComponent {

  void inject(MainActivity activity);

  void inject(MainAtyPresenter presenter);

  void inject(HomeFragment fragment);

  void inject(HomeFrPresenter presenter);

  Context getMainAtyContext();

  MainActivity getMainAtySelf();

  MainAtyNavigator getNavigator();

  CategoryAdapter getCategoryAdapter();

  GameCardAdapter getGameCardAdapter();
}
