package com.i7676.qyclient.functions.main.adapters;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.CategoryEntity;
import com.i7676.qyclient.functions.main.MainActivity;
import com.i7676.qyclient.functions.main.home.list.GameListActivity;
import com.i7676.qyclient.widgets.AutoLoadImageView;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class CategoryAdapter extends BaseQuickAdapter<CategoryEntity> {
    public CategoryAdapter(int layoutResId, List<CategoryEntity> data) {
        super(layoutResId, data);
        openLoadAnimation();
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, CategoryEntity categoryEntity) {
        ((AutoLoadImageView) baseViewHolder.getConvertView()
            .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(categoryEntity.getThumb(),
            null);
        baseViewHolder.setText(R.id.category_text, categoryEntity.getCatName());

        baseViewHolder.setOnClickListener(R.id.category_img,
            new YetAnotherClickListener(categoryEntity.getCatId(), categoryEntity.getCatName()));
    }

    private class YetAnotherClickListener implements View.OnClickListener {
        private int categoryId;
        private String categoryText;

        YetAnotherClickListener(int categoryId, String categoryText) {
            this.categoryId = categoryId;
            this.categoryText = categoryText;
        }

        @Override public void onClick(View v) {
            if (QyClient.curUser == null) {
                Toast.makeText(mContext, "请先登录", Toast.LENGTH_LONG).show();
                return;
            }

            final Bundle args = new Bundle();
            args.putString(GameListActivity.TITLE_TEXT_TAG, categoryText);
            args.putInt(GameListActivity.TAG_TYPE, GameListActivity.CATEGORY_TASK);
            args.putInt(GameListActivity.CATEGORY_ID_TAG, categoryId);
            ((MainActivity) mContext).getPresenter().showGameListAty(args);
        }
    }
}
