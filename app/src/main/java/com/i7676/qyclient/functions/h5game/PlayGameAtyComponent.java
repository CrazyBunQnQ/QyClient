package com.i7676.qyclient.functions.h5game;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import dagger.Component;

/**
 * Created by Administrator on 2016/10/11.
 */
@PerActivity @Component(dependencies = QyClientComponent.class)
public interface PlayGameAtyComponent {

    void inject(PlayGameActivity activity);
}
