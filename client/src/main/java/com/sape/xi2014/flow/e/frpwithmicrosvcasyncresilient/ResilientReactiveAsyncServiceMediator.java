package com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub.ResilentProductImageServiceClient;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub.ResilentReviewServiceClient;
import com.sape.xi2014.service.ServiceMediator;

public class ResilientReactiveAsyncServiceMediator implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {

    final long startTime = System.currentTimeMillis();
    ClientResponse response = new ClientResponse();

    Observable<Tile> searchTile =
        searchServiceClient.getSearchResults(searchTerm).doOnSubscribe(() -> logTime("Search started ", startTime))
            .doOnCompleted(() -> logTime("Search completed ", startTime));

    Observable<Tile> mergedTile =
        searchTile.flatMap(t -> {

          Observable<Reviews> reviews =
              new ResilentReviewServiceClient(t.getSellerId()).toObservable().subscribeOn(Schedulers.newThread())
                  .doOnCompleted(() -> logTime("getSellerReview [" + t.getProductId() + "] completed", startTime));;

          Observable<String> imageUrl =
              new ResilentProductImageServiceClient(t.getProductId()).toObservable()
                  .subscribeOn(Schedulers.newThread())
                  .doOnCompleted(() -> logTime("getProductImage [" + t.getProductId() + "] completed", startTime));;

          return Observable.zip(reviews, imageUrl, (r, u) -> {
            return new Tile(t, r, u);
          }).doOnCompleted(() -> logTime("zip [" + t.getProductId() + "] completed", startTime));
        });

    List<Tile> allTiles =
        mergedTile.toList().doOnCompleted(() -> logTime("All Tiles completed", startTime)).toBlocking().single();

    response.setTiles(new Tiles(allTiles));

    return response;

  }

  private void logTime(String message, long startTime) {
    System.out.println(message + " => " + (System.currentTimeMillis() - startTime) + "ms");
  }


}
