package com.i7676.qyclient.functions.main.activity.detail.childFragment.detaialfragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.i7676.qyclient.R;
import com.i7676.qyclient.annotations.Layout;
import com.i7676.qyclient.entity.ActivitiesEntity;
import com.i7676.qyclient.functions.BaseFragment;
import com.i7676.qyclient.functions.main.activity.detail.ActyDetaiActivity;
import com.i7676.qyclient.functions.main.activity.detail.ActyDetailComponent;

/**
 * Created by Administrator on 2016/10/9.
 */
@Layout(R.layout.fragment_activites_detail_)
public class ActivityDetailFrgment   extends BaseFragment<ActivityDetailPresenter,ActivityDetailView> implements  ActivityDetailView{
    private Button butgame;
    private TextView tvContent;
    private ActivitiesEntity activitiesEntity;
    private ActyDetailComponent mActyDetailcomponent;




//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_activites_detail_, null);


//        butgame = (Button) view.findViewById(R.id.but_startgame);
//        tvContent = (TextView) view.findViewById(R.id.tv_content);
//     tvContent.setText(activitiesEntity.getDescription());

      //  return  view;
   // }

    @Override
    protected void initRootViews(View rootView) {
        butgame = (Button) rootView.findViewById(R.id.but_startgame);
        tvContent = (TextView) rootView.findViewById(R.id.tv_content);

    }


    @Override
    protected void setupInject() {
        ((ActyDetaiActivity)getActivity()).getAtyComponent().inject(getPresenter());


    }



    @Override
    public void getTextdetail(String description) {
        tvContent.setText(description);


    }

    @Override
    public void goActivity(String  href) {



    }


    @NonNull
    @Override
    public ActivityDetailPresenter providePresenter() {
        return new ActivityDetailPresenter();
    }
}
