package com.i7676.qyclient.functions.main.home.detail;

import android.support.annotation.NonNull;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseActivity;

/**
 * Created by Administrator on 2016/9/27.
 */
@Layout(R.layout.activity_game_detail) public class GameDetailActivity
    extends BaseActivity<GameDetailAtyPresenter, GameDetailAtyView> {

    @Override public void initViews() {

    }

    @NonNull @Override public GameDetailAtyPresenter providePresenter() {
        return new GameDetailAtyPresenter();
    }
}
