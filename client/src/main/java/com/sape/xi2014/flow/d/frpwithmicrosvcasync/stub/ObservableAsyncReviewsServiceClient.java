package com.sape.xi2014.flow.d.frpwithmicrosvcasync.stub;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import rx.Observable;

import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ReviewServiceClient;


public class ObservableAsyncReviewsServiceClient {

  ExecutorService executorService = Executors.newFixedThreadPool(5);
  ReviewServiceClient basicReviewsClient = new ReviewServiceClient();

  public Observable<Reviews> getSellerReviews(String sellerId) {

    Observable<Reviews> observableReviews = Observable.create(subscriber -> {
      Future<Reviews> reviewFuture = executorService.submit(new Callable<Reviews>() {
        @Override
        public Reviews call() throws Exception {
          Reviews sellerReviews = basicReviewsClient.getSellerReviews(sellerId);
          return sellerReviews;
        }
      });
      
      try {
        subscriber.onNext(reviewFuture.get());
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    
    return observableReviews;
  }

  public Observable<String> getProductImage(String productId) {
    
    Observable<String> observableUrl = Observable.create(subscriber -> {
      Future<String> urlFuture = executorService.submit(new Callable<String>() {
        @Override
        public String call() throws Exception {
          String url = basicReviewsClient.getProductImage(productId);
          return url;
        }
      });
      
      try {
        subscriber.onNext(urlFuture.get());
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    });
    
    return observableUrl;
  }

}
