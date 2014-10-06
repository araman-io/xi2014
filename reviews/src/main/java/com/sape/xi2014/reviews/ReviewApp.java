package com.sape.xi2014.reviews;

import static spark.Spark.get;
import static spark.SparkBase.setPort;

/**
 * App for SearchService
 */
public class ReviewApp {

  public static void main(String[] args) {
    
    setPort(4568);

    get("/hello-reviews", (req, res) -> "hello world from the review service");

    get("/reviews/seller", (request, response) -> {

      Object returnValue = null;
      String sellerId = null;

      try {
        sellerId = request.queryParams("sellerId");
        returnValue = ReviewService.INSTANCE.getSellerReviews(sellerId);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("encountered an exception while trying to fetch reviews for seller ", e);
      }

      return returnValue;
    });

  }

}
