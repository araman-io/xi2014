package com.sape.xi2014.search.etsy;

import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class EtsySearchResponseBuilder {
	public static EtsySearchResponse generateSearchResult() {
		EtsySearchResponse etysSearchResponse = new EtsySearchResponse();
		int count = (int) (Math.random() * 100);
		List<Result> results = new ArrayList<Result>();
		CustomProviderStrategy providerStrategy = new CustomProviderStrategy();
		PodamFactory factory = new PodamFactoryImpl(providerStrategy);
		for (int i = 0; i < count; i++) {
			Result result =factory.manufacturePojo(Result.class);
			results.add(result);
		}
		etysSearchResponse.setCount(results.size());
		etysSearchResponse.setResults(results);
		return etysSearchResponse;
	}
	
	public static void main(String[] args) {
		System.out.println(generateSearchResult());
	}

	
}

