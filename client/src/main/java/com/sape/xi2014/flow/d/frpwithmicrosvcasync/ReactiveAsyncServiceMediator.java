package com.sape.xi2014.flow.d.frpwithmicrosvcasync;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableListingServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sape.xi2014.service.ServiceMediator;


public class ReactiveAsyncServiceMediator implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableListingServiceClient listingServiceClient = new ObservableListingServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
    final long startTime = System.currentTimeMillis();
    ClientResponse response = new ClientResponse();

    Observable<Tile> searchTile =
        searchServiceClient.getSearchResults(searchTerm).doOnSubscribe(() -> logTime("Search started ", startTime))
            .doOnCompleted(() -> logTime("Search completed ", startTime));

    Observable<Tile> mergedTile =
        searchTile.flatMap(t -> {
          Observable<Reviews> reviews =
              listingServiceClient.getSellerReviews(t.getSellerId()).subscribeOn(Schedulers.io())
                  .doOnCompleted(() -> logTime("getSellerReview [" + t.getProductId() + "] completed", startTime));

          Observable<String> imageUrl =
              listingServiceClient.getProductImage(t.getProductId()).subscribeOn(Schedulers.io())
                  .doOnCompleted(() -> logTime("getProductImage [" + t.getProductId() + "] completed", startTime));

          return Observable.zip(reviews, imageUrl, (r, u) -> {
            return new Tile(t, r, u);
          });
        });

    List<Tile> single =
        mergedTile.doOnCompleted(() -> logTime("All Tiles Completed ", startTime)).toList().toBlocking().single();

    response.setTiles(new Tiles(single));

    return response;

  }

  private void logTime(String message, long startTime) {
    System.out.println(message + " => " + (System.currentTimeMillis() - startTime) + "ms");
  }


}
