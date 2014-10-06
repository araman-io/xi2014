package com.sape.xi2014.entity;

public class Tile {
  String productId;
  String state;
  String sellerId;
  String description;
  String url;
  String imageUrl;


  Reviews reviews;

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Reviews getReviews() {
    return reviews;
  }

  public void setReviews(Reviews reviews) {
    this.reviews = reviews;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public String toString() {
    return "Tile [productId=" + productId + ", state=" + state + ", sellerId=" + sellerId + ", description="
        + description + ", url=" + url + ", imageUrl=" + imageUrl + ", reviews=" + reviews + "]";
  }


}
