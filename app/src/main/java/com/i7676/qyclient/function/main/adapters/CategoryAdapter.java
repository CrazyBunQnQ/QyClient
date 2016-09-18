package com.i7676.qyclient.function.main.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i7676.qyclient.R;
import com.i7676.qyclient.function.main.entity.CategoryEntity;
import com.i7676.qyclient.widgets.AutoLoadImageView;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<CategoryEntity> {

    public CategoryAdapter(int layoutResId, List<CategoryEntity> data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, CategoryEntity categoryEntity) {
      ((AutoLoadImageView) baseViewHolder.getConvertView()
          .findViewById(R.id.category_img)).setImageUrlAndAuthorInfo(categoryEntity.getImageURL(),
          null);
      baseViewHolder.setText(R.id.category_text, categoryEntity.getCategoryText());
    }
  }