package com.sape.xi2014.flow.c.frpwithmicrosvc;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.ServiceMediator;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ResilentProductImageClient;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ResilentReviewServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableReviewsServiceClient;
import com.sape.xi2014.flow.c.frpwithmicrosvc.stub.ObservableSearchServiceClient;

public class ResilentReactiveServiceMediator implements ServiceMediator {

  ObservableSearchServiceClient searchServiceClient = new ObservableSearchServiceClient();
  ObservableReviewsServiceClient reviewsServiceClient = new ObservableReviewsServiceClient();

  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {
	    Observable<Tile> searchTile = searchServiceClient.getSearchResults(searchTerm);

	    List<Tile> allTiles = new ArrayList<Tile>();
	    ClientResponse response = new ClientResponse();

	    searchTile.flatMap(t -> {
	      /* Invoke the Hystrix wrapped review service client directly */
	      Observable<Reviews> reviews = new ResilentReviewServiceClient(t.getSellerId()).observe();
	      reviews.subscribe((rev) -> {
	          System.out.println("onNext: " + rev);
	      }, (exception) -> {
	          exception.printStackTrace();
	      });

	      /* Invoke the Hystrix wrapped product image service client */
	      Observable<String> imageUrl = new ResilentProductImageClient(t.getProductId()).observe();
	      imageUrl.subscribe((imgURL) -> {
	          System.out.println("onNext: " + imgURL);
	      }, (exception) -> {
	          exception.printStackTrace();
	      });
	      
	      /* Business as usual. Merge the above two calls */
	      return Observable.merge(reviews, imageUrl).flatMap(o -> {
	        if (o instanceof String) {
	          t.setImageUrl((String) o);
	          System.out.println("URL in mediator - "+(String) o);
	        } else {
	          t.setReviews((Reviews) o);
	          System.out.println("Reviews in mediator - "+(Reviews) o);
	        }

	        return Observable.just(t);
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
	  ClientResponse searchResults = new ResilentReactiveServiceMediator().getAggregatedResponse("shoes");
      System.out.println(new Gson().toJson(searchResults));
  }

}

