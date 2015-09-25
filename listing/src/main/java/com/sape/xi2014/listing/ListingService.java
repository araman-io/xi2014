package com.sape.xi2014.listing;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.sape.xi2014.listing.domain.EtsyResponseBuilder;

public class ListingService {

	public static ListingService INSTANCE = new ListingService();

	private static final String API_KEY = "3iv3rdx5szjeq3m1vp6idwm9";

	/**
	 * 
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public String getSellerReviews(String sellerId) throws Exception {
		String etsyResponse = null;
		// Call to Etsy API to fetch the seller review using sellerId
		Gson json = new Gson();
		int numOfReview = (int) (Math.random() * 10);
		etsyResponse = json.toJson(EtsyResponseBuilder.generateEtsyReview(sellerId, numOfReview));
		injectdelay();
		return etsyResponse;
	}

	private void injectdelay() {
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * 
	 * @param productID
	 * @return
	 * @throws Exception
	 */
	public String getProductImages(String productID) throws Exception {
		String etsyResponse = null;
		Gson json = new Gson();
		etsyResponse = json.toJson(EtsyResponseBuilder.generateEtsyImage(productID));
		injectdelay();
		return etsyResponse;
	}

}
