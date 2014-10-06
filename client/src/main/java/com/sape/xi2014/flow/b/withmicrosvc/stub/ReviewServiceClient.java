package com.sape.xi2014.flow.b.withmicrosvc.stub;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.sape.xi2014.entity.Reviews;

public class ReviewServiceClient {

  public Reviews getSellerReviews(String sellerId) throws Exception {

    String response =
        Request.Get("http://localhost:4568/reviews/seller?sellerId=".concat(sellerId)).execute().returnContent()
            .asString();

    Gson json = new Gson();
    
    EtsyReview sellerReview = json.fromJson(response, EtsyReview.class);
    
    List<String> messages = new ArrayList<String>();
    for (Result r : sellerReview.getResults()) {
      messages.add(r.getMessage());
    }
    
    Reviews reviews = new Reviews();
    reviews.setCount(sellerReview.getCount());
    reviews.setMessage(messages);
    
    return reviews;

  }
}
