package com.sape.xi2014.rxnetty;

import org.junit.Test;

import com.sape.xi2014.entity.ClientResponse;

public class MyRxNettyTests {
  
    RxNettySearchCommand searchCommand = new RxNettySearchCommand();
    RxNettyReviewCommand reviewCommand = new RxNettyReviewCommand();

    @Test
    public void shouldReturnSearchResults() {
      searchCommand.getSearchResults("shoes").toBlocking().forEach(System.out::println);
    }
    
    @Test
    public void shouldReturnSellerReviews() {
      reviewCommand.getSellerReviews("8467327").toBlocking().forEach(System.out::println);
    }
    
    @Test
    public void testClientResponse() throws Exception {
      ClientResponse aggregatedResponse = new RxNettyApiGateway().getAggregatedResponse("shoes");
      
      System.out.println(aggregatedResponse.getTiles().getTiles());
    }
    
}
