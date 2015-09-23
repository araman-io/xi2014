package com.sape.xi2014.listing.domain;

import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class EtsyResponseBuilder {
	
	public static EtsyReview generateEtsyReview(String sellerid, int numOfReviews) {
		PodamFactory factory = new PodamFactoryImpl(); 
		EtsyReview review = new EtsyReview();
		List<Result> results = new ArrayList<Result>();
		for(int i = 0; i < numOfReviews; i ++ ) {
			results.add(factory.manufacturePojo(Result.class));
		}
		review.setResults(results);
		review.setCount(results.size());
		return review;
	}
	
	public static EtsyImage generateEtsyImage(String productId) {
		PodamFactory factory = new PodamFactoryImpl(); 
		EtsyImage etsyImage = new EtsyImage();
		List<ImgResult> results = new ArrayList<ImgResult>();
		ImgResult imgResult = factory.manufacturePojo(ImgResult.class);
		imgResult.setUrl_fullxfull("http://dummyimageservice.com/" + productId + ".jpg");
		results.add(imgResult);
		etsyImage.setResults(results);
		return etsyImage;
	}
	
	public static void  main(String[] args) {
		System.out.println(generateEtsyReview("111", 4));
	}

}
