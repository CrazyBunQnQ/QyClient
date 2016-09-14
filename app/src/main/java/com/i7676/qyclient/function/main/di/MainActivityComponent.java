package com.i7676.qyclient.function.main.di;

import com.i7676.qyclient.QyClientComponent;
import dagger.Component;

/**
 * Created by Administrator on 2016/9/14.
 */

@Component(modules = MainActivityModule.class, dependencies = QyClientComponent.class)
public interface MainActivityComponent {
}
