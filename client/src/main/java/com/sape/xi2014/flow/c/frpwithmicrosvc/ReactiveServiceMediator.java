package com.sape.xi2014.flow.c.frpwithmicrosvc;

import java.util.List;

import rx.Observable;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableReviewsServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sape.xi2014.service.ServiceMediator;

public class ReactiveServiceMediator implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableReviewsServiceClient reviewsServiceClient = new ObservableReviewsServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
    Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);
    List<Tile> allTiles = null;
    ClientResponse response = new ClientResponse();

    Observable<Tile> mergedTile = searchTile.flatMap(t -> {
      Observable<Reviews> reviews = reviewsServiceClient.getSellerReviews(t.getSellerId());
      Observable<String> imageUrl = reviewsServiceClient.getProductImage(t.getProductId());

      return Observable.zip(reviews, imageUrl, (r, u) -> {
        return new Tile(t, r, u);
      });
    });

    allTiles = mergedTile.toList().toBlocking().single();

    Tiles tiles = new Tiles();
    tiles.setTiles(allTiles);
    response.setTiles(tiles);

    return response;

  }

  public static void main(String[] args) throws Exception {
    ClientResponse searchResults = new ReactiveServiceMediator().getAggregatedResponse("shoes");
    System.out.println(new Gson().toJson(searchResults));
  }

}
