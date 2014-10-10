package com.sape.xi2014.demo;

import org.junit.Test;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.d.frpwithmicrosvcasync.ReactiveAsyncServiceMediator;

public class RunAsyncServiceMediator {
  
  @Test
  public void runAsyncMediator() throws Exception {
    ClientResponse searchResults = new ReactiveAsyncServiceMediator().getAggregatedResponse("shoes");
    System.out.println(new Gson().toJson(searchResults));
  }

}
