package com.i7676.qyclient.functions.main.gift.giftdetail;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/10/9.
 */

@PerActivity @Component(dependencies = QyClientComponent.class)
public interface GiftDetailComponent {

    void inject(GiftDetailPresenter presenter);

}
