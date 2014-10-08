package com.sape.xi2014.reviews;

import static spark.Spark.get;
import static spark.SparkBase.setPort;

/**
 * App for SearchService
 */
public class ListingApp {

	public static void main(String[] args) {

		String mode = System.getProperty("mode") != null ? System.getProperty("mode") : "esty";
		System.out.println("Mode " + mode);
		
		setPort(4568);

		get("/hello-reviews", (req, res) -> "hello world from the review service");

		get("/listing/reviews/seller", (request, response) -> {

			Object returnValue = null;
			String sellerId = null;

			try {
				sellerId = request.queryParams("sellerId");
				returnValue = ListingService.INSTANCE.getSellerReviews(sellerId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("encountered an exception while trying to fetch reviews for seller ", e);
			}

			return returnValue;
		});

		get("/listing/images", (request, response) -> {
			Object returnValue = null;
			String productId = null;
			try {
				productId = request.queryParams("productId");
				returnValue = ListingService.INSTANCE.getProductImages(productId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("encountered an exception while trying to fetch reviews for seller ", e);
			}

			return returnValue;
		});

	}

}
