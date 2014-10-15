package com.sape.xi2014.demo;

import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;

public class RunMicroServiceTest {
	@Test
	public void runSearchServiceTest() throws Exception {
		InputStream asStream = Request
				.Get("http://localhost:4567/search/bykeyword?searchTerm=".concat(URLEncoder.encode("shoes", "UTF-8")))
				.execute().returnContent().asStream();
		System.out.println(SearchResponse.parseFrom(asStream));
	}
	
	@Test
	public void runListingImageServiceTest() throws Exception {
		String jsonResponse = Request
				.Get("http://localhost:4568/listing/images?productId=62558662").execute().returnContent().asString();
		System.out.println(jsonResponse);
	}
	
	@Test
	public void runListingReviewServiceTest() throws Exception {
		String jsonResponse = Request
				.Get("http://localhost:4568/listing/reviews/seller?sellerId=37803428").execute().returnContent().asString();
		System.out.println(jsonResponse);
	}
}
