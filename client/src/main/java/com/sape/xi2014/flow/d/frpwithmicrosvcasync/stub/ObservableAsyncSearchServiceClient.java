package com.sape.xi2014.flow.d.frpwithmicrosvcasync.stub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.fluent.Async;

import rx.Observable;

import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.stub.SearchServiceClient;

public class ObservableAsyncSearchServiceClient {

  ExecutorService threadpool = Executors.newFixedThreadPool(2);
  Async async = Async.newInstance().use(threadpool);
  SearchServiceClient searchServiceClient = new SearchServiceClient();

  public Observable<Tile> getSearchResults(String searchTerm) throws Exception {

    // Process the queue
    Observable<Tile> tile = Observable.create(subscriber -> {
      Tiles searchResults = null;
      try {
        searchResults = searchServiceClient.getSearchResults(searchTerm);
      } catch (Exception e) {
        e.printStackTrace();
      }

      for (Tile t : searchResults.getTiles()) {
        subscriber.onNext(t);
      }

    });

    return tile;
  }

}
