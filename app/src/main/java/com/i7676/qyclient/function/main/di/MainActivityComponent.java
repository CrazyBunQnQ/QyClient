package com.i7676.qyclient.function.main.di;

import android.content.Context;
import android.widget.PopupWindow;
import com.i7676.qyclient.di.QyClientComponent;
import com.i7676.qyclient.annotations.PreActivity;
import com.i7676.qyclient.function.main.MainActivity;
import com.i7676.qyclient.function.main.adapters.CategoryAdapter;
import com.i7676.qyclient.function.main.adapters.GameCardAdapter;
import com.i7676.qyclient.function.main.fragments.GameFragment;
import com.i7676.qyclient.function.main.presenter.MainActivityPresenter;
import dagger.Component;

/**
 * Created by Administrator on 2016/9/14.
 */

@PreActivity @Component(modules = MainActivityModule.class, dependencies = QyClientComponent.class)
public interface MainActivityComponent {

  void inject(MainActivity activity);

  void inject(MainActivityPresenter presenter);

  void inject(GameFragment fragment);

  MainActivity getMainActivity();

  Context getContext();

  CategoryAdapter getCategoryAdapter();

  GameCardAdapter getGameCardAdapter();

  PopupWindow getPopupWindow();
}
