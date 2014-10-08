package com.sape.xi2014.flow.c.frpwithmicrosvc.stub;

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
        for (Tile t : searchResults.getTiles()) {
          subscriber.onNext(t);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      subscriber.onCompleted();

    });

    return tiles;
  }

}
