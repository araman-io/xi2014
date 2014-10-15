package com.sape.xi2014.demo;

import org.junit.Test;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.d.frpwithmicrosvcasync.ReactiveAsyncServiceMediator;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.ResilientReactiveAsyncServiceMediator;

public class RunAsyncServiceMediatorWithCircuitBreaker {

	String SEARCH_TERM = "fountain pen";

	@Test
	public void runReactiveServiceMediatorWithOutCircuitBreaker() throws Exception {
		long t = System.currentTimeMillis();
		ClientResponse searchResults = new ReactiveAsyncServiceMediator().getAggregatedResponse(SEARCH_TERM);
		System.out.println("Time Taken [" + (System.currentTimeMillis() - t) + "] ms");
		System.out.println(new Gson().toJson(searchResults));
	}

	@Test
	public void runAsyncServiceMediatorWithCircuitBreaker() throws Exception {
		long t = System.currentTimeMillis();
		ClientResponse searchResults = new ResilientReactiveAsyncServiceMediator().getAggregatedResponse(SEARCH_TERM);
		System.out.println("Time Taken [" + (System.currentTimeMillis() - t) + "] ms");
		System.out.println(new Gson().toJson(searchResults));
	}

}
