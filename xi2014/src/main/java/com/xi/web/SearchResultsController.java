package com.xi.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xi.entities.SearchResults;
import com.xi.facade.SearchResultsAggregatorFacade;

@RestController
public class SearchResultsController {

	/*@Autowired
	SearchResultsAggregatorFacade searchResultsAggregatorFacade;*/

	@RequestMapping("search")
    public void performSearch(@RequestParam(value = "searchTerm", required = true) String searchTerm) {
		SearchResultsAggregatorFacade searchResultsAggregatorFacade = new SearchResultsAggregatorFacade();
		SearchResults etsySearchResults = searchResultsAggregatorFacade.aggregateSearchResults(searchTerm);
		System.out.println("itemAttributes--"+etsySearchResults.toString());
    }
}