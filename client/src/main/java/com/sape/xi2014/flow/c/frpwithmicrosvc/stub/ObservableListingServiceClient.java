package com.sape.xi2014.flow.c.frpwithmicrosvc.stub;

import rx.Observable;

import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ListingServiceClient;

public class ObservableListingServiceClient {

  ListingServiceClient basicListingClient = new ListingServiceClient();

  public Observable<Reviews> getSellerReviews(String sellerId) {

    Observable<Reviews> r = Observable.create(subscriber -> {
      try {
        Reviews sellerReviews = basicListingClient.getSellerReviews(sellerId);
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
        url = basicListingClient.getProductImage(productId);
        subscriber.onNext(url);
      } catch (Exception e) {
        e.printStackTrace();
      }
      subscriber.onCompleted();
    });
    return productImageUrl;
  }

}
