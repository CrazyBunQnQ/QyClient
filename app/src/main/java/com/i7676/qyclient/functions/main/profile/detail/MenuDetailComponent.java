package com.i7676.qyclient.functions.main.profile.detail;

import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PerActivity;
import com.i7676.qyclient.functions.main.profile.detail.account.AccountFraPresenter;
import com.i7676.qyclient.functions.main.profile.detail.telbind.TelBindFraPresenter;
import dagger.Component;

/**
 * Created by Administrator on 2016/10/9.
 */
@PerActivity @Component(dependencies = QyClientComponent.class)
public interface MenuDetailComponent {
    void inject(TelBindFraPresenter presenter);

    void inject(AccountFraPresenter presenter);
}
