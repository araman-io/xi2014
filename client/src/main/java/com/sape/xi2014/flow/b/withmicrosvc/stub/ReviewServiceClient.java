package com.sape.xi2014.flow.b.withmicrosvc.stub;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.sape.xi2014.entity.Reviews;
import com.sun.scenario.effect.impl.prism.PrDrawable;

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

  public String getProductImage(String productId) throws Exception {

    String response =
        Request.Get("http://localhost:4568/listing/images?productId=".concat(productId)).execute().returnContent()
            .asString();

    Gson json = new Gson();

    EtsyImage productImage = json.fromJson(response, EtsyImage.class);

    for (ImgResult r : productImage.getResults()) {
      return r.getUrl_fullxfull();
    }

    return "";

  }
}
