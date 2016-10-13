package com.i7676.qyclient.functions.main.activity.detail;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = QyClientComponent.class)

public interface ActyDetailComponent   {

    void inject(ActyDetailPresenter presenter);
}