package com.sape.xi2014.search;

import java.util.ArrayList;
import java.util.List;

import com.sape.xi2014.search.entity.SearchProtos.Item;
import com.sape.xi2014.search.entity.SearchProtos.SearchRequest;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;
import com.sape.xi2014.search.etsy.EtsySearchResponse;
import com.sape.xi2014.search.etsy.EtsySearchResponseBuilder;
import com.sape.xi2014.search.etsy.Result;

public class SearchService {

	public static SearchService INSTANCE = new SearchService();

	private static final String API_KEY = "3iv3rdx5szjeq3m1vp6idwm9";

	@SuppressWarnings("deprecation")
	public SearchResponse getSearchResults(SearchRequest searchRequest) throws Exception {
		SearchResponse searchResponse = null;
		// mapping the search response json to proto class
		searchResponse = parseEtsyResponse();
		// searchResponse
		injectdelay();
		return searchResponse;

	}
	
	private void injectdelay() {
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * 
	 * @param searchResponse
	 * @return
	 */
	protected SearchResponse parseEtsyResponse() {
		EtsySearchResponse response = EtsySearchResponseBuilder.generateSearchResult();

		List<Item> itemList = new ArrayList<Item>();

		for (Result result : response.getResults()) {
			Item i = Item.newBuilder().setId(result.getListing_id()).setState(result.getState())
					.setDescription(result.getTitle()).setUserId(result.getUser_id()).setUrl(result.getUrl()).build();

			itemList.add(i);
		}
		return SearchResponse.newBuilder().setCount(response.getCount()).addAllItem(itemList).build();
	}

}
