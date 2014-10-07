package com.sape.xi2014.search;

import static spark.Spark.*;

import javax.servlet.ServletOutputStream;

import com.sape.xi2014.search.entity.SearchProtos;
import com.sape.xi2014.search.entity.SearchProtos.SearchRequest;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;

/**
 * App for SearchService
 */
public class SearchApp {

  public static void main(String[] args) {
	  
	String mode = System.getProperty("mode") != null ? System.getProperty("mode") : "esty";
	System.out.println("Mode " + mode);  
    
    get("/hello-search", (req, res) -> "hello world from the search service");

    get("/search/bykeyword",
        (request, response) -> {

          Object returnValue = null;
          String outputAs = request.queryParams("as") == null ? "protobuf" : request.queryParams("as");

          try {
            // build a search request
            SearchRequest searchRequest =
                SearchProtos.SearchRequest.newBuilder().setSearchTerm(request.queryParams("searchTerm")).build();

            SearchResponse searchResults = SearchService.INSTANCE.getSearchResults(searchRequest);

            if (outputAs.equals("protobuf")) {
              // set the response type
              response.type("application/x-protobuf");
              ServletOutputStream outputStream = response.raw().getOutputStream();
              searchResults.writeTo(outputStream);
              outputStream.close();
            } else {
              returnValue = searchResults;
            }
          } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("encountered an exception while trying to serve searchrequest", e);
          }

          return returnValue;
        });

  }

}
