package com.i7676.qyclient.functions.main.activity.pastactivity;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/8.
 */
@PerActivity
@Component(dependencies = QyClientComponent.class)
public interface PastListAtyComponent {
    void inject(PastListAtyPresenter  presenter);
}

