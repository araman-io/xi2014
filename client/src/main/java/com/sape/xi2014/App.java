package com.sape.xi2014;

import static spark.Spark.get;
import static spark.SparkBase.setPort;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.b.withmicrosvc.ImperativeServiceMediator;
import com.sape.xi2014.flow.b.withmicrosvc.ServiceMediator;
import com.sape.xi2014.flow.c.frpwithmicrosvc.ReactiveServiceMediator;


/**
 * Hello world!
 *
 */
public class App 
{
	static Map<String,Object> valueMap = new HashMap<String,Object>();
    public static void main( String[] args )
    {
    	setPort(4569);

		get("/hello-client", (req, res) -> "hello world from the web client");

		get("/search", (request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			String flow = request.queryParams("flow") == null ? "A" : request.queryParams("flow");
			
			Object returnValue = null;
			String searchTerm = null;
			ServiceMediator serviceAgg = getServiceMediator(flow);
			ClientResponse aggregatedResponse = null;
			try {
				searchTerm = request.queryParams("searchTerm");
				if(null==searchTerm || searchTerm.length()==0) searchTerm="bag";
				if(valueMap.containsKey(searchTerm)) return valueMap.get(searchTerm);
				aggregatedResponse = serviceAgg.getAggregatedResponse(searchTerm);
				Gson j = new Gson();
				returnValue = j.toJson(aggregatedResponse.getTiles().getTiles());
				valueMap.put(searchTerm,returnValue);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("encountered an exception while trying to fetch reviews for seller ", e);
			}

			System.out.println(returnValue);
			return returnValue;
		});
    }
    
    public static ServiceMediator getServiceMediator(String flow) {
    	System.out.println("Flow " + flow);
    	switch(flow) {
    	case "A" : return new ImperativeServiceMediator();
    	case "B" :
    	default:
    		return new ReactiveServiceMediator();
    	}
    }
}
