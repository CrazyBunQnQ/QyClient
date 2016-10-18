package com.i7676.qyclient.functions.main.hi.detail.edit;

import android.support.annotation.NonNull;
import android.view.View;
import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.functions.BaseFragment;

/**
 * Created by Administrator on 2016/10/18.
 *
 * 发布新的动态
 */
@Layout(R.layout.fragment_hi_mk_new_card) public class MakeANewCardFragment
    extends BaseFragment<MakeANewCardPresenter, MakeANewCardFrgView>
    implements MakeANewCardFrgView {

    @Override protected void initRootViews(View rootView) {
        
    }

    @NonNull @Override public MakeANewCardPresenter providePresenter() {
        return new MakeANewCardPresenter();
    }
}
