package com.sape.xi2014;

import static spark.Spark.get;
import static spark.SparkBase.setPort;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.flow.b.withmicrosvc.ImperativeServiceMediator;
import com.sape.xi2014.flow.c.frpwithmicrosvc.ReactiveServiceMediator;
import com.sape.xi2014.flow.d.frpwithmicrosvcasync.ReactiveAsyncServiceMediatorStrategy;
import com.sape.xi2014.service.ServiceMediator;


/**
 * The Client App
 */
public class App {
  
  static Map<String, Object> valueMap = new HashMap<String, Object>();
  static String DEFAULT_SEARCH_TERM = "bag";

  public static void main(String[] args) {
   
    setPort(4569);

    get("/hello-client", (req, res) -> "hello world from the web client");

    get("/search", (request, response) -> {
      
      response.header("Access-Control-Allow-Origin", "*");
      
      String flow = request.queryParams("flow") == null ? "B" : request.queryParams("flow");

      Object returnValue = null;
      String searchTerm = null;
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

      return returnValue;
    });
  }


  public static ServiceMediator getServiceMediator(String flow) {

    switch (flow) {
      case "B":
        return new ImperativeServiceMediator();
      case "C":
        return new ReactiveServiceMediator();
      case "D":
        return new ReactiveAsyncServiceMediatorStrategy();
      default:
        return new ReactiveServiceMediator();
    }
  }
}
