package com.i7676.qyclient.function.main.di;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.PopupWindow;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.PreActivity;
import com.i7676.qyclient.function.main.MainActivity;
import com.i7676.qyclient.function.main.adapters.CategoryAdapter;
import com.i7676.qyclient.function.main.adapters.GameCardAdapter;
import com.i7676.qyclient.util.ViewUtil;
import com.roughike.bottombar.BottomBar;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/14.
 */

@PreActivity @Module public class MainActivityModule {

  private Context atyContext;

  public MainActivityModule(Context atyContext) {
    this.atyContext = atyContext;
  }

  @Provides @PreActivity MainActivity providedMainActivity() {
    return (MainActivity) atyContext;
  }

  @Provides @PreActivity Context providedContext() {
    return atyContext;
  }

  @Provides @PreActivity Toolbar providedToolbar() {
    return ((MainActivity) atyContext).getmToolbar();
  }

  @Provides @PreActivity BottomBar providedBottomBar() {
    return ((MainActivity) atyContext).getmBottomBar();
  }

  @Provides @PreActivity CategoryAdapter providedCategoryAdapter() {
    return new CategoryAdapter(R.layout.item_game_category, new ArrayList<>());
  }

  @Provides @PreActivity PopupWindow providedPopupWindow(CategoryAdapter adapter) {
    return ViewUtil.createPopWindow(atyContext, adapter);
  }

  @Provides @PreActivity GameCardAdapter providedCardAdapter() {
    return new GameCardAdapter(R.layout.item_game_card, new ArrayList<>());
  }
}
