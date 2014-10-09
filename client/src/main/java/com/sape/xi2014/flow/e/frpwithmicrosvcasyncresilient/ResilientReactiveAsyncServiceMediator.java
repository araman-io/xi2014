package com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.google.gson.Gson;
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

    ClientResponse response = new ClientResponse();
    Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);

    Observable<Tile> mergedTile =
        searchTile.flatMap(t -> {

          Observable<Reviews> reviews =
              new ResilentReviewServiceClient(t.getSellerId()).toObservable().subscribeOn(Schedulers.newThread());

          Observable<String> imageUrl =
              new ResilentProductImageServiceClient(t.getProductId()).toObservable()
                  .subscribeOn(Schedulers.newThread());

          return Observable.zip(reviews, imageUrl, (r, u) -> {
            return new Tile(t, r, u);
          });
        });

    List<Tile> single = mergedTile.toList().toBlocking().single();

    response.setTiles(new Tiles(single));

    return response;

  }

  public static void main(String[] args) throws Exception {
    ClientResponse searchResults = new ResilientReactiveAsyncServiceMediator().getAggregatedResponse("shoes");
    System.out.println(new Gson().toJson(searchResults));
  }

}
