package com.sape.xi2014.flow.c.frpwithmicrosvc;

import java.util.List;

import rx.Observable;

import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableListingServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sape.xi2014.service.ServiceMediator;

public class ReactiveServiceMediator implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableListingServiceClient listingServiceClient = new ObservableListingServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {

    final long startTime = System.currentTimeMillis();

    Observable<Tile> searchTile =
        searchServiceClient.getSearchResults(searchTerm).doOnSubscribe(() -> logTime("Search started ", startTime))
            .doOnCompleted(() -> logTime("Search completed ", startTime));

    List<Tile> allTiles = null;
    ClientResponse response = new ClientResponse();

    Observable<Tile> mergedTile =
        searchTile.flatMap(t -> {
          Observable<Reviews> reviews =
              listingServiceClient.getSellerReviews(t.getSellerId()).doOnCompleted(
                  () -> logTime("\ngetSellerReview [" + t.getProductId() + "] completed", startTime));

          Observable<String> imageUrl =
              listingServiceClient.getProductImage(t.getProductId()).doOnCompleted(
                  () -> logTime("getProductImage [" + t.getProductId() + "] completed", startTime));

          return Observable.zip(reviews, imageUrl, (r, u) -> {
            return new Tile(t, r, u);
          });
        });


    allTiles =
        mergedTile.toList().doOnCompleted(() -> logTime("All Tiles completed", startTime)).toBlocking().single();

    Tiles tiles = new Tiles();
    tiles.setTiles(allTiles);
    response.setTiles(tiles);

    return response;

  }

  private void logTime(String message, long startTime) {
    System.out.println(message + " => " + (System.currentTimeMillis() - startTime) + "ms");
  }


}
