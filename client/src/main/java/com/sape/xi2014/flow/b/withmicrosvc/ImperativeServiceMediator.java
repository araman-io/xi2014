package com.sape.xi2014.flow.b.withmicrosvc;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ListingServiceClient;
import com.sape.xi2014.flow.b.withmicrosvc.stub.SearchServiceClient;
import com.sape.xi2014.service.ServiceMediator;

public class ImperativeServiceMediator implements ServiceMediator {

  SearchServiceClient searchClient = new SearchServiceClient();
  ListingServiceClient listingClient = new ListingServiceClient();

  @Override
  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {

    ClientResponse clientResponse = new ClientResponse();

    Tiles searchResults = searchClient.getSearchResults(searchTerm);
    for (Tile t : searchResults.getTiles()) {
      Reviews sellerReviews = listingClient.getSellerReviews(t.getSellerId());
      t.setReviews(sellerReviews);
      
      String imageUrl = listingClient.getProductImage(t.getProductId());
      t.setImageUrl(imageUrl);
    }
    
    clientResponse.setTiles(searchResults);

    return clientResponse;
  }

  public static void main(String[] args) throws Exception {
    ClientResponse aggregatedResponse = new ImperativeServiceMediator().getAggregatedResponse("shoes");
    Gson j = new Gson();
    System.out.println(j.toJson(aggregatedResponse.getTiles().getTiles()));
  }

}
