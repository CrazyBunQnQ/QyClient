package com.i7676.qyclient.functions.login.sign.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import java.util.List;

public class SignWayAdapter extends BaseQuickAdapter<SignWayEntity> {

    private View.OnClickListener itemClickListener;

    public SignWayAdapter(int layoutResId, List<SignWayEntity> data,
        View.OnClickListener itemClickListener) {
        super(layoutResId, data);
        this.itemClickListener = itemClickListener;
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, SignWayEntity signWayEntity) {
        baseViewHolder.setImageResource(R.id.img_sin_logo, signWayEntity.getResId());
        baseViewHolder.setText(R.id.tv_sin_text, signWayEntity.getText());
        baseViewHolder.getConvertView().setOnClickListener(itemClickListener);
    }
}