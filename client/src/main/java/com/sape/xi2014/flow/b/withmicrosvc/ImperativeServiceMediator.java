package com.sape.xi2014.flow.b.withmicrosvc;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientReponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ReviewServiceClient;
import com.sape.xi2014.flow.b.withmicrosvc.stub.SearchServiceClient;

public class ImperativeServiceMediator implements ServiceMediator {

  SearchServiceClient searchClient = new SearchServiceClient();
  ReviewServiceClient reviewClient = new ReviewServiceClient();

  @Override
  public ClientReponse getAggregatedResponse(String searchTerm) throws Exception {

    ClientReponse clientResponse = new ClientReponse();

    Tiles searchResults = searchClient.getSearchResults(searchTerm);
    for (Tile t : searchResults.getTiles()) {
      Reviews sellerReviews = reviewClient.getSellerReviews(t.getSellerId());
      t.setReviews(sellerReviews);
    }

    clientResponse.setTiles(searchResults);

    return clientResponse;
  }

  public static void main(String[] args) throws Exception {
    ClientReponse aggregatedResponse = new ImperativeServiceMediator().getAggregatedResponse("shoes");

    Gson j = new Gson();
    System.out.println(j.toJson(aggregatedResponse));
  }

}
