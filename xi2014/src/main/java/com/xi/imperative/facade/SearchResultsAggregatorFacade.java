package com.xi.facade;

import org.springframework.web.client.RestTemplate;

import com.xi.entities.ItemAttributes;
import com.xi.entities.ItemReviews;
import com.xi.entities.SearchResults;

public class SearchResultsAggregatorFacade {
	
	/*@Autowired
	RestTemplate restTemplate;*/
	
	String apiKey = "3iv3rdx5szjeq3m1vp6idwm9"; //Externalize it

	//TODO
	/*
	 * Wrap the item attributes and the item reviews calls with a reactive Hystrix Api call
	 */
public SearchResults aggregateSearchResults(String searchTerm){
	SearchResults etsySearchResults = new SearchResults();
	ItemAttributes itemAttributes = retrieveItemAttributes(searchTerm);
	etsySearchResults.withItemAttributes(itemAttributes);
	ItemReviews itemReviews = retrieveItemReviews(searchTerm);
	etsySearchResults.withItemReviews(itemReviews);
	return etsySearchResults;
}

private ItemAttributes retrieveItemAttributes(String searchTerm) {
	String URL = "https://openapi.etsy.com/v2/listings/active?api_key=".concat(apiKey).concat("&keywords=").concat(searchTerm);
	RestTemplate restTemplate = new RestTemplate();
	ItemAttributes itemAttributes = restTemplate.getForObject(URL, ItemAttributes.class);
	return itemAttributes;
}

private ItemReviews retrieveItemReviews(String searchTerm) {
	String URL = "https://openapi.etsy.com/v2//users/7413776/feedback/as-seller?api_key=".concat(apiKey).concat("&keywords=").concat(searchTerm);
	RestTemplate restTemplate = new RestTemplate();
	ItemReviews itemReviews = restTemplate.getForObject(URL, ItemReviews.class);
	return itemReviews;
}

}
