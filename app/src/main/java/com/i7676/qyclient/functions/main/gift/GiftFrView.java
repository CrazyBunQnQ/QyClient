package com.i7676.qyclient.functions.main.gift;

import com.i7676.qyclient.entity.GiftEntity;
import com.i7676.qyclient.functions.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */

public interface GiftFrView extends BaseView{


    void addList(List<GiftEntity> giftEntities);

   void  clearList();

    void showDialog(String msg);

    void closeDialog();

    void loadCompleted();
    void setbutton();


}
