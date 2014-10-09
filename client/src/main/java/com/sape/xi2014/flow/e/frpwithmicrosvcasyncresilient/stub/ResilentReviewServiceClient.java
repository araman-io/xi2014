package com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ListingServiceClient;

/**
 * @author mvalli
 *
 */
public class ResilentReviewServiceClient extends HystrixCommand<Reviews> {
  
  private static Logger logger = LoggerFactory.getLogger(ResilentReviewServiceClient.class);
  
  String sellerId = null;
  ListingServiceClient listingServiceClient = new ListingServiceClient();

  /**
   * @param sellerId
   */
  public ResilentReviewServiceClient(String sellerId) {

    super(Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductServiceGroup"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("ProductReviewServiceCommand"))
        .andCommandPropertiesDefaults(
            HystrixCommandProperties.Setter().withExecutionIsolationStrategy(ExecutionIsolationStrategy.THREAD)
                .withRequestCacheEnabled(false)
                .withMetricsHealthSnapshotIntervalInMilliseconds(Integer.valueOf("10000"))
                .withCircuitBreakerRequestVolumeThreshold(Integer.valueOf("50"))
                .withCircuitBreakerSleepWindowInMilliseconds(Integer.valueOf("100000"))
                .withExecutionIsolationThreadTimeoutInMilliseconds(Integer.valueOf("3000"))
                .withCircuitBreakerForceOpen(Boolean.getBoolean("false"))
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(Integer.valueOf("100"))
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(Integer.valueOf("50"))
        ));

    this.sellerId = sellerId;

  }

  @Override
  protected Reviews run() throws Exception {
    Reviews sellerReviews = listingServiceClient.getSellerReviews(this.sellerId);
    return sellerReviews;
  }

  @Override
  protected Reviews getFallback() {
    logger.error("Couldn't retrieve response from review service. Executing fallback");
    Reviews reviews = new Reviews();
    return reviews;
  }
}
