package com.i7676.qyclient.functions.main.home.list;

import com.i7676.qyclient.entity.GameEntity;
import com.i7676.qyclient.functions.BaseView;
import java.util.List;

/**
 * Created by HCol on 2016/10/5.
 */

public interface GameListAtyView extends BaseView {

    void add2List(List<GameEntity> gameEntities);

    void clearList();

    void setTitleText(String titleText);

    void showDialog(String msg);

    void closeDialog();
}
