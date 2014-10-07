package com.sape.xi2014.flow.d.frpwithmicrosvcasync;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.ServiceMediator;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableReviewsServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;

public class ReactiveAsyncServiceMediator implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableReviewsServiceClient reviewsServiceClient = new ObservableReviewsServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
    Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);
    List<Tile> allTiles = new ArrayList<Tile>();
    ClientResponse response = new ClientResponse();

    searchTile.parallel(oTile -> {
      return oTile.flatMap(t -> {
        Observable<Reviews> reviews = reviewsServiceClient.getSellerReviews(t.getSellerId());
        Observable<String> imageUrl = reviewsServiceClient.getProductImage(t.getProductId());

        return Observable.zip(reviews, imageUrl, (r, u) -> {
          t.setReviews(r);
          t.setImageUrl(u);

          return t;
        });

      });
    }).subscribe(e -> {
      allTiles.add((Tile) e);
    });

    Tiles tiles = new Tiles();
    tiles.setTiles(allTiles);
    response.setTiles(tiles);

    return response;

  }

  public static void main(String[] args) throws Exception {
    ClientResponse searchResults = new ReactiveAsyncServiceMediator().getAggregatedResponse("shoes");
    Thread.sleep(60000);
    System.out.println(new Gson().toJson(searchResults));
  }

}
