package com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub;

import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.sape.xi2014.flow.b.withmicrosvc.stub.EtsyImage;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ImgResult;

/**
 * @author mvalli
 *
 */
public class ResilentProductImageClient extends HystrixCommand<String> {

	String productId;

	/**
	 * @param sellerId
	 */
	public ResilentProductImageClient(String productId) {
		//super(HystrixCommandGroupKey.Factory.asKey("ProductImageServiceGroup"));
		super( Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductImageServiceGroup")).andCommandKey(
                 HystrixCommandKey.Factory.asKey("ProductImageServiceCommand")).andCommandPropertiesDefaults(
                         HystrixCommandProperties
                         .Setter()
                         .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
                         .withRequestCacheEnabled(false)
                         .withMetricsHealthSnapshotIntervalInMilliseconds(
                                 Integer.valueOf("10000"))
                         .withCircuitBreakerRequestVolumeThreshold(
                                 Integer.valueOf("50"))
                         .withCircuitBreakerSleepWindowInMilliseconds(
                                 Integer.valueOf("100000"))
                         .withExecutionIsolationThreadTimeoutInMilliseconds(
                                 Integer.valueOf("3500"))
                         // If defined, then CircuitBreakerRequestVolumeThreshold takes precedence over error %
                         /*
                          * .withCircuitBreakerErrorThresholdPercentage( Integer.valueOf(getFromConfigRegistry(groupKey,
                          * commandKey, "circuitBreakerErrorThresholdPercentage", "5")))
                          */
                         .withCircuitBreakerForceOpen(
                                 Boolean.getBoolean("false"))
                         .withExecutionIsolationSemaphoreMaxConcurrentRequests(
                                 Integer.valueOf("100"))
                         .withFallbackIsolationSemaphoreMaxConcurrentRequests(
                                 Integer.valueOf("50"))

                 ));

		this.productId = productId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 * 
	 * Implement the getProductImage() functionality here
	 */
	@Override
	protected String run() throws Exception {
		String response = Request.Get("http://localhost:4568/listing/images?productId=".concat(productId)).execute()
				.returnContent().asString();

		Gson json = new Gson();

		EtsyImage productImage = json.fromJson(response, EtsyImage.class);

		for (ImgResult r : productImage.getResults()) {
			return r.getUrl_fullxfull();
		}

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.HystrixCommand#getFallback()
	 * 
	 * Currently returns a empty String for a image URL. Additionally prints a
	 * call out statement to the system stream.
	 * 
	 * To test fallback, change the product image URL to a non-existent URL
	 * such as http://nonexistenthost:4568/listing/images
	 */
	@Override
	protected String getFallback() {
		System.out.println("Couldn't retrieve response from product image service. Executing fallback");
		return "";
	}

}
