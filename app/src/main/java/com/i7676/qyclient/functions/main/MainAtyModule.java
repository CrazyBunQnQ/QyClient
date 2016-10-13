package com.i7676.qyclient.functions.main;

import android.content.Context;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.functions.main.adapters.ActivityFrAdapter;
import com.i7676.qyclient.functions.main.adapters.CategoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GameCardAdapter;
import com.i7676.qyclient.functions.main.adapters.GameGridAdapter;
import com.i7676.qyclient.functions.main.adapters.GameHistoryAdapter;
import com.i7676.qyclient.functions.main.adapters.GiftListAdapter;
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

    @PerActivity @Provides Context providedMainAtyContext() {
        return mainAtyContext;
    }

    @PerActivity @Provides MainActivity providedMainAtySelf() {
        return (MainActivity) mainAtyContext;
    }

    @PerActivity @Provides MainAtyNavigator providedMainAtyNavigator(MainActivity mainActivity) {
        return MainAtyNavigator.create(mainActivity);
    }

    @Provides ActivityFrAdapter providedActivityFrAdapter() {
        return new ActivityFrAdapter(R.layout.item_activities_list, new ArrayList<>());
    }

    @Provides CategoryAdapter providedCategoryAdapter() {
        return new CategoryAdapter(R.layout.item_game_category, new ArrayList<>());
    }

    @Provides @PerActivity GameHistoryAdapter providedGameHistoryAdapter() {
        return new GameHistoryAdapter(R.layout.item_game_history, new ArrayList<>());
    }

    @Provides GameCardAdapter providedGameCardAdapter() {
        return new GameCardAdapter(R.layout.item_game_card, new ArrayList<>());
    }

    @Provides GameGridAdapter providedGameGridAdapter() {
        return new GameGridAdapter(R.layout.item_game_list_grid, new ArrayList<>());
    }

    @Provides GiftListAdapter providetGiftListAdapter() {
        return new GiftListAdapter(R.layout.item_gifts_list, new ArrayList<>());
    }
}
