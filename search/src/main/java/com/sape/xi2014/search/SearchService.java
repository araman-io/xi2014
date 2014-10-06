package com.sape.xi2014.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.sape.xi2014.search.entity.SearchProtos.Item;
import com.sape.xi2014.search.entity.SearchProtos.SearchRequest;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;
import com.sape.xi2014.search.etsy.EtsySearchResponse;
import com.sape.xi2014.search.etsy.Result;

public class SearchService {

  public static SearchService INSTANCE = new SearchService();

  private static final String API_KEY = "3iv3rdx5szjeq3m1vp6idwm9";

  public SearchResponse getSearchResults(SearchRequest searchRequest) throws Exception {

    String etsyResponse = null;
    SearchResponse searchResponse = null;

    etsyResponse =
        Request
            .Get(
                "https://openapi.etsy.com/v2/listings/active?api_key=".concat(API_KEY).concat("&keywords=")
                    .concat(searchRequest.getSearchTerm())).execute().returnContent().asString();

    searchResponse = parseEtsyResponse(etsyResponse);

    // searchResponse
    return searchResponse;

  }

  protected SearchResponse parseEtsyResponse(String searchResponse) {
    Gson gson = new Gson();
    EtsySearchResponse response = gson.fromJson(searchResponse, EtsySearchResponse.class);

    List<Item> itemList = new ArrayList<Item>();

    for (Result result : response.getResults()) {
      Item i =
          Item.newBuilder().setId(result.getListing_id()).setState(result.getState()).setDescription(result.getTitle())
              .setUserId(result.getUser_id()).setUrl(result.getUrl()).build();

      itemList.add(i);
    }

    return SearchResponse.newBuilder().setCount(response.getCount()).addAllItem(itemList).build();
  }

}
