package com.sape.xi2014.flow.c.frpwithmicrosvc.stub;

import rx.Observable;

import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ReviewServiceClient;

public class ObservableReviewsServiceClient {
  
  public Observable<Reviews> getSellerReviews(String sellerId) {
    
    ReviewServiceClient basicReviewsClient = new ReviewServiceClient();
    
    Observable<Reviews> r = Observable.create(subscriber -> {
      try {
        Reviews sellerReviews = basicReviewsClient.getSellerReviews(sellerId);
        subscriber.onNext(sellerReviews);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    
    return r;
  }

}
