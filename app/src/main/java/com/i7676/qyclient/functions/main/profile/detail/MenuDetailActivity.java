package com.i7676.qyclient.functions.main.profile.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.OneHasToolbarActivity;
import com.i7676.qyclient.functions.main.profile.ProfileConstants;

/**
 * Created by Administrator on 2016/10/8.
 */

@Layout(R.layout.activity_toolbar) public class MenuDetailActivity
    extends OneHasToolbarActivity<MenuDetailAtyPresenter, MenuDetailAtyView>
    implements MenuDetailAtyView {

    public static final String SHOW_TAG = "SHOW_TAG";
    private int menuId;

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

    public void renderToolbarOptionMenus(int menuId) {
        this.menuId = menuId;
        invalidateOptionsMenu();
    }

    /**
     * 创建 menu
     */
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 当 menu 被选中
     */
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * 每次 invalidateOptionsMenu 就会调用这个方法
     */
    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        switch (menuId) {
            case ProfileConstants.MENU_FRIENDS:
                menu.findItem(R.id.commentNotify).setVisible(true);
                break;
            default:
                menu.findItem(R.id.commentNotify).setVisible(false);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
