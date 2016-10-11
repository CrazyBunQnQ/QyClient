package com.i7676.qyclient.functions.h5game.pay;

import com.i7676.qyclient.functions.BaseView;
import com.switfpass.pay.bean.RequestMsg;

/**
 * Created by Administrator on 2016/10/10.
 */

public interface PaymentCheckAtyView extends BaseView {

    void go2Payment(RequestMsg msg);

    void loadProgressDialog(String msg);

    void payResults(String msg);
}
