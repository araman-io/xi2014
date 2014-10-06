package com.sape.xi2014.reviews;

import org.apache.http.client.fluent.Request;

public class ReviewService {

  public static ReviewService INSTANCE = new ReviewService();

  private static final String API_KEY = "3iv3rdx5szjeq3m1vp6idwm9";

  /**
   * 
   * @param sellerId
   * @return
   * @throws Exception
   */
  public String getSellerReviews(String sellerId) throws Exception {
    String etsyResponse = null;
    etsyResponse =
        Request
            .Get(
                "https://openapi.etsy.com/v2/users/".concat(sellerId).concat("/feedback/as-seller?api_key=")
                    .concat(API_KEY)).execute().returnContent().asString();
    return etsyResponse;

  }
  
  /**
   * 
   * @param productID
   * @return
   * @throws Exception
   */
  public String getProductImages(String productID) throws Exception {
	    String etsyResponse = null;
	    etsyResponse =
	        Request
	            .Get(
	                "https://openapi.etsy.com/v2/listings/".concat(productID).concat("/images?&api_key=")
	                    .concat(API_KEY)).execute().returnContent().asString();
	    return etsyResponse;

	  }
  
}
