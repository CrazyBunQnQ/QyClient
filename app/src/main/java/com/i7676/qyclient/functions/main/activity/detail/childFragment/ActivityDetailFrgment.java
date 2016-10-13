package com.i7676.qyclient.functions.main.activity.detail.childFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.ActivitiesEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */

public class ActivityDetailFrgment   extends Fragment{
    private Button butgame;
    private TextView tvContent;
    private ActivitiesEntity activitiesEntity;
    private List<ActivitiesEntity>list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activites_detail_, null);


        butgame = (Button) view.findViewById(R.id.but_startgame);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        //  tvContent.setText();
      tvContent.setText(activitiesEntity.getDescription());

        return  view;
    };



}
