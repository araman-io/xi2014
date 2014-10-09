package com.sape.xi2014;

import static spark.Spark.get;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.b.withmicrosvc.ImperativeServiceMediator;
import com.sape.xi2014.flow.c.frpwithmicrosvc.ReactiveServiceMediator;
import com.sape.xi2014.flow.d.frpwithmicrosvcasync.ReactiveAsyncServiceMediator;
import com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.ResilientReactiveAsyncServiceMediator;
import com.sape.xi2014.service.ServiceMediator;

/**
 * The Client App
 */
public class App {

	static Map<String, Object> valueMap = new HashMap<String, Object>();
	static String DEFAULT_SEARCH_TERM = "bag";

	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("starting the main app");
		setPort(4569);
		staticFileLocation("/webapp");
		get("/hello-client", (req, res) -> "hello world from the web client");

		get("/search", (request, response) -> {

			response.header("Access-Control-Allow-Origin", "*");

			String flow = request.queryParams("flow") == null ? "B" : request.queryParams("flow");

			Object returnValue = null;
			String searchTerm = null;
			long starttime = System.currentTimeMillis();
			ServiceMediator serviceMediator = getServiceMediator(flow);
			ClientResponse aggregatedResponse = null;

			try {
				searchTerm = request.queryParams("searchTerm");
				if (null == searchTerm || searchTerm.length() == 0) {
					searchTerm = DEFAULT_SEARCH_TERM;
				}
				if (valueMap.containsKey(searchTerm)) {
					return valueMap.get(searchTerm);
				}
				aggregatedResponse = serviceMediator.getAggregatedResponse(searchTerm);
				Gson j = new Gson();
				returnValue = j.toJson(aggregatedResponse.getTiles().getTiles());
				valueMap.put(searchTerm, returnValue);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("encountered an exception while trying to fetch reviews for seller ", e);
			}
			logger.info("Time taken searching :" + searchTerm + " [" + (System.currentTimeMillis() - starttime)
					+ "] ms");
			return returnValue;
		});
	}

	public static ServiceMediator getServiceMediator(String flow) {

		switch (flow) {
		case "B":
			logger.info(" Flow B : Imperative Service Mediator");
			return new ImperativeServiceMediator();
		case "C":
			logger.info(" Flow C : Reactive Service Mediator");
			return new ReactiveServiceMediator();
		case "D":
			logger.info(" Flow D : Reactive Async Service Mediator");
			return new ReactiveAsyncServiceMediator();
		case "E":
			logger.info("Flow E: Reactive Async with Hystrix Resilency");
			return new ResilientReactiveAsyncServiceMediator();
		default:
			logger.info(" Flow Default : Reactive Service Mediator");
			return new ReactiveServiceMediator();
		}
	}
}
