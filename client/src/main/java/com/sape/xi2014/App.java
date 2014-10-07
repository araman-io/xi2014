package com.sape.xi2014;

import static spark.Spark.get;
import static spark.SparkBase.setPort;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.b.withmicrosvc.ImperativeServiceMediator;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	setPort(4569);

		get("/hello-client", (req, res) -> "hello world from the web client");

		get("imperative/search", (request, response) -> {

			Object returnValue = null;
			String searchTerm = null;
			ImperativeServiceMediator serviceAgg = new ImperativeServiceMediator();
			ClientResponse aggregatedResponse = null;
			try {
				searchTerm = request.queryParams("searchTerm");
				aggregatedResponse = serviceAgg.getAggregatedResponse(searchTerm);
				Gson j = new Gson();
				returnValue = j.toJson(aggregatedResponse.getTiles().getTiles());
				
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("encountered an exception while trying to fetch reviews for seller ", e);
			}

			return returnValue;
		});
    }
}
