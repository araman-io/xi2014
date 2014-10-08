package com.sape.xi2014.flow.c.frpwithmicrosvc.stub;

import rx.Observable;

import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ReviewServiceClient;

public class ObservableReviewsServiceClient {

  ReviewServiceClient basicReviewsClient = new ReviewServiceClient();

  public Observable<Reviews> getSellerReviews(String sellerId) {

    Observable<Reviews> r = Observable.create(subscriber -> {
      try {
        Reviews sellerReviews = basicReviewsClient.getSellerReviews(sellerId);
        subscriber.onNext(sellerReviews);
      } catch (Exception e) {
        e.printStackTrace();
      }
      subscriber.onCompleted();
    });

    return r;
  }

  public Observable<String> getProductImage(String productId) {
    Observable<String> productImageUrl = Observable.create(subscriber -> {
      String url = null;
      try {
        url = basicReviewsClient.getProductImage(productId);
        subscriber.onNext(url);
      } catch (Exception e) {
        e.printStackTrace();
      }
      subscriber.onCompleted();
    });
    return productImageUrl;
  }

}
