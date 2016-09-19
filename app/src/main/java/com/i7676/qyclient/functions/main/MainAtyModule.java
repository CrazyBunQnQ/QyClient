package com.i7676.qyclient.functions.main;

import android.content.Context;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.PreActivity;
import com.i7676.qyclient.functions.main.adapters.ActivityFrAdapter;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GameCardAdapter;
import com.i7676.qyclient.functions.main.navigation.MainAtyNavigator;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/19.
 */
@Module public class MainAtyModule {

  private Context mainAtyContext;

  public MainAtyModule(Context mainAtyContext) {
    this.mainAtyContext = mainAtyContext;
  }

  @PreActivity @Provides Context providedMainAtyContext() {
    return mainAtyContext;
  }

  @PreActivity @Provides MainActivity providedMainAtySelf() {
    return (MainActivity) mainAtyContext;
  }

  @PreActivity @Provides MainAtyNavigator providedMainAtyNavigator(MainActivity mainActivity) {
    return MainAtyNavigator.create(mainActivity);
  }

  @Provides ActivityFrAdapter providedActivityFrAdapter() {
    return new ActivityFrAdapter(R.layout.item_activities_list, new ArrayList<>());
  }

  @Provides CategoryAdapter providedCategoryAdapter() {
    return new CategoryAdapter(R.layout.item_game_category, new ArrayList<>());
  }

  @Provides GameCardAdapter providedGameCardAdapter() {
    return new GameCardAdapter(R.layout.item_game_card, new ArrayList<>());
  }
}
