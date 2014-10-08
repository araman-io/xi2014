package com.sape.xi2014.flow.c.frpwithmicrosvc.stub;

import java.util.List;

import rx.Observable;

import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.stub.SearchServiceClient;

public class ObservableSearchServiceClient {

  public Observable<Tile> getSearchResults(String searchTerm) throws Exception {

    SearchServiceClient basicSearchClient = new SearchServiceClient();

    Observable<Tile> tiles = Observable.create(subscriber -> {
      Tiles searchResults = null;
      try {
        searchResults = basicSearchClient.getSearchResults(searchTerm);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      for (Tile t : searchResults.getTiles()) {
        subscriber.onNext(t);
      }
     
      subscriber.onCompleted();

    });

    return tiles;
  }

  public Observable<List<Tile>> getListOfTiles(String searchTerm) throws Exception {
    SearchServiceClient basicSearchClient = new SearchServiceClient();

    Observable<List<Tile>> listOfTiles = Observable.create(subscriber -> {
      Tiles searchResults = null;
      try {
        searchResults = basicSearchClient.getSearchResults(searchTerm);
      } catch (Exception e) {
        e.printStackTrace();
      }
      subscriber.onNext(searchResults.getTiles());
    });

    return listOfTiles;
  }

}
