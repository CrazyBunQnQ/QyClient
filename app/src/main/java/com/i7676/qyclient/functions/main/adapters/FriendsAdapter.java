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

    public static final int SEARCH_RESULT = 1;
    public static final int NORMAL = 2;

    private int dataType = NORMAL;

    public FriendsAdapter(int layoutResId, List<FriendEntity> data) {
        super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, FriendEntity friendEntity) {
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.img_avatar)).setImageUrlAndAuthorInfo(friendEntity.getAvatar(),
            null);

        baseViewHolder.setText(R.id.tv_nickname, friendEntity.getNickname());

        if (dataType == SEARCH_RESULT) {
            if (isAllow2MakeFriends(friendEntity.getState())) {
                baseViewHolder.setText(R.id.btn_sendMsg, "加好友");
            }
        } else {
            if (isAllow2SendMsg(friendEntity.getState())) {
                baseViewHolder.setText(R.id.btn_sendMsg, "发消息");
            } else {
                baseViewHolder.getConvertView().findViewById(R.id.btn_sendMsg).setEnabled(false);
            }
        }
    }

    private boolean isAllow2SendMsg(int state) {
        return state != FriendEntity.StateConstants.BANNED;
    }

    private boolean isAllow2MakeFriends(int state) {
        return state == FriendEntity.StateConstants.NOT_APPLIED
            || state == FriendEntity.StateConstants.IGNORED;
    }

    public void setMyNewData(int dataType, List<FriendEntity> friendEntities) {
        this.dataType = dataType;
        this.setNewData(friendEntities);
    }
}
