package com.i7676.qyclient.functions.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.entity.CategoryEntity;
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
            .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(categoryEntity.getImageURL(),
            null);
        baseViewHolder.setText(R.id.category_text, categoryEntity.getCategoryText());
    }
}
