package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.FriendEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class FriendsAdapter extends BaseQuickAdapter<FriendEntity> {
    public FriendsAdapter(int layoutResId, List<FriendEntity> data) {
        super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, FriendEntity friendEntity) {
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_avatar)).setImageUrlAndAuthorInfo(friendEntity.getAvatar(),
            null);

        baseViewHolder.setText(R.id.tv_nickname, friendEntity.getNickname());
    }
}
