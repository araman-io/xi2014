package com.sape.xi2014.flow.c.frpwithmicrosvc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rx.Observable;

import com.google.gson.Gson;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.stub.SearchServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableReviewsServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;
import com.sun.org.apache.bcel.internal.generic.RETURN;


public class ReactiveServiceMediatorTest {

  ObservableSearchServiceClient searchClient = new ObservableSearchServiceClient();
  ObservableReviewsServiceClient reviewClient = new ObservableReviewsServiceClient();
  SearchServiceClient basicSearchServiceClient = new SearchServiceClient();

  String SEARCH_TERM = "shoes";

  public void testSearchResultsAsTiles() throws Exception {
    Observable<Tiles> tiles = null;

    // tiles = searchClient.getSearchResults(SEARCH_TERM);

    tiles.flatMap(ts -> {
      Observable<Reviews> r = null;
      for (Tile t : ts.getTiles()) {
        r = reviewClient.getSellerReviews(t.getSellerId());
      }
      return r;
    }).subscribe(rew -> {
      System.out.println(rew.getMessage().size());
    });

  }

  @Test
  public void testSearchResultsAsListOfTilesFromBasicSearchClient() throws Exception {
    List<Tile> tiles = basicSearchServiceClient.getSearchResults(SEARCH_TERM).getTiles();

    Observable.from(tiles).take(3).flatMap(ts -> {
      Observable<Reviews> review = null;
      review = reviewClient.getSellerReviews(ts.getSellerId());
      return review;
    }).subscribe(r -> {
      System.out.println(r.getCount() + "<>" + r.getMessage().size());
    });

  }

  @Test
  public void testSearchResultsAsTileFromObservableSearchClient() throws Exception {
    Observable<Tile> tile = searchClient.getSearchResults(SEARCH_TERM);

    tile.flatMap(t -> {
      Observable<Reviews> review = null;
      review = reviewClient.getSellerReviews(t.getSellerId());
      return review;
    }).subscribe(r -> {
      System.out.println(r.getCount() + "<>" + r.getMessage().size());
    });

  }

  @Test
  public void testReviewPopulationForTileFromObservableSearchClient() throws Exception {
    Observable<Tile> searchTile = searchClient.getSearchResults(SEARCH_TERM);
    Gson gson = new Gson();

    searchTile.flatMap(t -> {
      Observable<Tile> tileWithReview = reviewClient.getSellerReviews(t.getSellerId()).map(r -> {
        t.setReviews(r);
        return t;
      });
      return tileWithReview;
    }).subscribe(o -> {
      System.out.println(gson.toJson(o));
    });
  }


  @Test
  public void shouldReturnListOfTileFromObservableSearchClient() throws Exception {
    Observable<Tile> searchTile = searchClient.getSearchResults(SEARCH_TERM);
    Gson gson = new Gson();
    List<Tile> allTiles = new ArrayList<Tile>();

    searchTile.flatMap(t -> {
      Observable<Tile> tileWithReview = reviewClient.getSellerReviews(t.getSellerId()).map(r -> {
        t.setReviews(r);
        return t;
      });
      return tileWithReview;
    }).subscribe(e -> {
      allTiles.add(e);
    });

    System.out.println(allTiles.size());
    System.out.println(gson.toJson(allTiles));

  }

  @Test
  public void alternateImplementationToShouldReturnListOfTileFromObservableSearchClient() throws Exception {
    Observable<Tile> searchTile = searchClient.getSearchResults(SEARCH_TERM);
    Gson gson = new Gson();
    List<Tile> allTiles = new ArrayList<Tile>();

    searchTile.flatMap(t -> {
      Observable<Reviews> reviews = reviewClient.getSellerReviews(t.getSellerId());
      Observable<String> imageUrl = reviewClient.getProductImage(t.getProductId());

      return Observable.merge(reviews, imageUrl).flatMap(o -> {
        if (o instanceof String) {
          t.setImageUrl((String) o);
        } else {
          t.setReviews((Reviews) o);
        }
        
        return Observable.just(t);
      });
      
    }).subscribe(e -> {
      allTiles.add((Tile) e);
    });

    System.out.println(allTiles.size());
    System.out.println(gson.toJson(allTiles));

  }
  
}
