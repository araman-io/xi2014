package com.sape.xi2014.demo;

import org.junit.Test;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.c.frpwithmicrosvc.ReactiveServiceMediator;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.ResilientReactiveAsyncServiceMediator;

public class RunAsyncServiceMediatorWithCircuitBreaker {

  String SEARCH_TERM = "fountain pen";

  @Test
  public void runReactiveServiceMediatorWithOutCircuitBreaker() throws Exception {
    ClientResponse searchResults = new ReactiveServiceMediator().getAggregatedResponse(SEARCH_TERM);
    System.out.println(new Gson().toJson(searchResults));
  }
  
  @Test
  public void runAsyncServiceMediatorWithCircuitBreaker() throws Exception {
    ClientResponse searchResults = new ResilientReactiveAsyncServiceMediator().getAggregatedResponse(SEARCH_TERM);
    System.out.println(new Gson().toJson(searchResults));
  }

  
}
