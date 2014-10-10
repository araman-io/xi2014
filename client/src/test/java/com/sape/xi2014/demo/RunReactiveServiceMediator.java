package com.sape.xi2014.demo;

import org.junit.Test;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.c.frpwithmicrosvc.ReactiveServiceMediator;


public class RunReactiveServiceMediator {

  String SEARCH_TERM = "fountain%20pen";

  @Test
  public void runReactiveServiceMediator() throws Exception {
    ClientResponse searchResults = new ReactiveServiceMediator().getAggregatedResponse(SEARCH_TERM);
    System.out.println(new Gson().toJson(searchResults));
  }
}
