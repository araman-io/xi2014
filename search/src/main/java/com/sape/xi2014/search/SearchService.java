package com.sape.xi2014.search;

import org.apache.http.client.fluent.Request;

import com.sape.xi2014.search.entity.SearchProtos;
import com.sape.xi2014.search.entity.SearchProtos.Item;
import com.sape.xi2014.search.entity.SearchProtos.SearchRequest;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponseOrBuilder;

public class SearchService {

  public static SearchService INSTANCE = new SearchService();
  
  private static final String API_KEY = "3iv3rdx5szjeq3m1vp6idwm9";

  public SearchResponse getSearchResults(SearchRequest searchRequest) {
    
    String serviceResponse = null;
    SearchResponse searchResponse = null;

    try {
      serviceResponse = Request.Get("https://openapi.etsy.com/v2/listings/active?api_key="
                .concat(API_KEY).concat("&keywords=")
                .concat(searchRequest.getSearchTerm()))
                .execute().returnContent().asString();
      
      searchResponse = SearchProtos.SearchResponse.newBuilder().addItem(Item.newBuilder().setListingId(serviceResponse).build()).build();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return searchResponse;

  }

}
