package com.i7676.qyclient.functions.login.sign.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.functions.login.sign.entity.SignWayEntity;
import java.util.List;

public class SignWayAdapter extends BaseQuickAdapter<SignWayEntity> {

    public interface FkItemClickListener {
        void onItemClick(int position, SignWayEntity signWayEntity);
    }

    private FkItemClickListener fkItemClickListener;

    public void setFkItemClickListener(FkItemClickListener fkItemClickListener) {
        this.fkItemClickListener = fkItemClickListener;
    }

    public SignWayAdapter(int layoutResId, List<SignWayEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, SignWayEntity signWayEntity) {
        baseViewHolder.setImageResource(R.id.img_sign_logo, signWayEntity.getIconResId())
            .setText(R.id.tv_sin_text, signWayEntity.getText());
        baseViewHolder.convertView.setOnClickListener(v -> {
            if (fkItemClickListener != null) {
                fkItemClickListener.onItemClick(baseViewHolder.getLayoutPosition(), signWayEntity);
            }
        });
    }
}