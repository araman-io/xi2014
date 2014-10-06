package com.sape.xi2014.search;

import static spark.Spark.get;

import com.sape.xi2014.search.entity.SearchProtos;
import com.sape.xi2014.search.entity.SearchProtos.SearchRequest;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;

/**
 * App for SearchService
 */
public class App {

  public static void main(String[] args) {
    get("/hello-search", (req, res) -> "hello world from the search service");
    
    get("/search/bykeyword", (request, response) -> { 
      SearchRequest searchRequest = SearchProtos.SearchRequest.newBuilder().setSearchTerm(request.queryParams("searchTerm")).build();
      response.type("application/x-protobuf");
      SearchResponse searchResults = SearchService.INSTANCE.getSearchResults(searchRequest);
      
      System.out.println(searchResults);
      
      return searchResults;
    });
  }
  
}
