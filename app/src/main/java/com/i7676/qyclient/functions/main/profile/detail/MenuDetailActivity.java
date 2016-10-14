package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.OneHasToolbarActivity;

/**
 * Created by Administrator on 2016/10/8.
 */

@Layout(R.layout.activity_toolbar) public class MenuDetailActivity
    extends OneHasToolbarActivity<MenuDetailAtyPresenter, MenuDetailAtyView>
    implements MenuDetailAtyView {

    public static final String SHOW_TAG = "SHOW_TAG";

    public static Intent buildIntent(Context from, Bundle args) {
        final Intent mIntent = new Intent(from, MenuDetailActivity.class);
        mIntent.putExtras(args);
        return mIntent;
    }

    private MenuDetailComponent atyComponent;

    public MenuDetailComponent getAtyComponent() {
        return atyComponent;
    }

    @Override public void initViews() {
        super.initViews();
        setupInject();
    }

    @Override public int getFrPlaceHolder() {
        return getContainerResId();
    }

    private void setupInject() {
        atyComponent = DaggerMenuDetailComponent.builder()
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();
    }

    @NonNull @Override public MenuDetailAtyPresenter providePresenter() {
        return new MenuDetailAtyPresenter(getIntent().getExtras(),
            new MenuDetailAtyNavigator(this));
    }

    @Override public void msg2User(String msg) {
        showToast2User(msg, Toast.LENGTH_SHORT);
    }
}
