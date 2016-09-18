package com.i7676.qyclient.function.main.entity;

public class CategoryEntity extends CommonBaseEntity {
  private String imageURL;
  private String categoryText;

  public CategoryEntity(String imageURL, String categoryText) {
    this.imageURL = imageURL;
    this.categoryText = categoryText;
  }

  public String getImageURL() {
    return imageURL;
  }

  public String getCategoryText() {
    return categoryText;
  }
}