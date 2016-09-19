package com.i7676.qyclient;

import android.app.Application;
import com.i7676.qyclient.net.EgretApiService;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by HCol on 2016/9/13.
 */
@Singleton @Component(modules = QyClientModule.class) public interface QyClientComponent {

  Application getApplication();

  EgretApiService getEgretApiService();
}
