package com.i7676.qyclient.functions.main.home.list;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import dagger.Component;

/**
 * Created by HCol on 2016/10/5.
 */

@PerActivity @Component(dependencies = QyClientComponent.class)
public interface GameListAtyComponent {
    void inject(GameListAtyPresenter presenter);
}
