package com.sape.xi2014.flow.d.frpwithmicrosvcasync;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableReviewsServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sape.xi2014.service.ServiceMediator;


public class ReactiveAsyncServiceMediatorStrategy implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableReviewsServiceClient reviewsServiceClient = new ObservableReviewsServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
    final long startTime = System.currentTimeMillis();

    Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);
    ClientResponse response = new ClientResponse();

    Observable<Tile> mergedTile =
        searchTile.flatMap(t -> {
          Observable<Reviews> reviews =
              reviewsServiceClient.getSellerReviews(t.getSellerId()).subscribeOn(Schedulers.io());
          Observable<String> imageUrl =
              reviewsServiceClient.getProductImage(t.getProductId()).subscribeOn(Schedulers.io());

          return Observable.zip(reviews, imageUrl, (r, u) -> {
            return new Tile(t, r, u);
          });
        });

    List<Tile> single =
        mergedTile.doOnCompleted(() -> System.out.println("All Tiles Completed " + startTime)).toList().toBlocking()
            .single();

    response.setTiles(new Tiles(single));

    return response;

  }

  public static void main(String[] args) throws Exception {
    ClientResponse searchResults = new ReactiveAsyncServiceMediatorStrategy().getAggregatedResponse("shoes");
    System.out.println(new Gson().toJson(searchResults));
  }

}
