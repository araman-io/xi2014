package com.sape.xi2014.flow.b.withmicrosvc.stub;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sape.xi2014.entity.Reviews;

/**
 * @author mvalli
 *
 */
public class ResilentReviewServiceClient extends HystrixCommand<Reviews> {

	String sellerId;

	/**
	 * @param sellerId
	 */
	public ResilentReviewServiceClient(String sellerId) {
		super(HystrixCommandGroupKey.Factory.asKey("ReviewServiceGroup"));
		// super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReviewServiceGroup")));
		this.sellerId = sellerId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 * 
	 * Implement the getSellerReviews() here
	 */
	@Override
	protected Reviews run() throws Exception {
		String response = Request.Get("http://localhost:4568/reviews/seller?sellerId=".concat(sellerId)).execute()
				.returnContent().asString();

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.HystrixCommand#getFallback()
	 * 
	 * Currently returns a empty review for a listing. Additionally prints a
	 * call out statement to the system stream.
	 * 
	 * To test fallback, change the review service URL to a non-existent URL
	 * such as http://nonexistenthost:4568/reviews/seller
	 */
	@Override
	protected Reviews getFallback() {
		System.out.println("Couldn't retrieve response from review service. Executing fallback");
		Reviews reviews = new Reviews();
		return reviews;
	}
}
