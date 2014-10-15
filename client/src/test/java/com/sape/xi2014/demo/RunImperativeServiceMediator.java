package com.sape.xi2014.demo;

import org.junit.Test;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.b.withmicrosvc.ImperativeServiceMediator;

public class RunImperativeServiceMediator {
	@Test
	public void runImperativeServiceMediator() throws Exception {
		long t = System.currentTimeMillis();
		ClientResponse searchResults = new ImperativeServiceMediator().getAggregatedResponse("shoes");
		System.out.println("Time Taken [" + (System.currentTimeMillis() - t) + "] ms");
	    System.out.println(new Gson().toJson(searchResults));
	}
}
