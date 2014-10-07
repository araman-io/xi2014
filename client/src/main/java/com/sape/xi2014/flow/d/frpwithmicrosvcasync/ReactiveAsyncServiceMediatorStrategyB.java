package com.sape.xi2014.flow.d.frpwithmicrosvcasync;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.ServiceMediator;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableReviewsServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;

public class ReactiveAsyncServiceMediatorStrategyB implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableReviewsServiceClient reviewsServiceClient = new ObservableReviewsServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
    Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);
    List<Tile> allTiles = new ArrayList<Tile>();
    ClientResponse response = new ClientResponse();

    Observable o1 = searchTile.parallel(oTile -> {
      return oTile.flatMap(t -> {
        Observable<Reviews> sellerReviews = reviewsServiceClient.getSellerReviews(t.getSellerId());
        return sellerReviews;
      });
    });

    Observable o2 = searchTile.parallel(oTile -> {
      return oTile.flatMap(t -> {
        Observable<String> url = reviewsServiceClient.getProductImage(t.getProductId());
        return url;
      });
    });

    // TODO merge and do something

    Tiles tiles = new Tiles();
    tiles.setTiles(allTiles);
    response.setTiles(tiles);

    return response;

  }

  public static void main(String[] args) throws Exception {
    ClientResponse searchResults = new ReactiveAsyncServiceMediatorStrategyB().getAggregatedResponse("shoes");
    Thread.sleep(60000);
    // System.out.println(new Gson().toJson(searchResults));
  }

}
