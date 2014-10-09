package com.sape.xi2014.flow.e.frpwithmicrosvcasyncresilient.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.sape.xi2014.flow.b.withmicrosvc.stub.ListingServiceClient;

/**
 * @author mvalli
 */
public class ResilentProductImageServiceClient extends HystrixCommand<String> {

  private static Logger logger = LoggerFactory.getLogger(ResilentProductImageServiceClient.class);

  String productId;
  ListingServiceClient listingServiceClient = new ListingServiceClient();

  /**
   * @param sellerId
   */
  public ResilentProductImageServiceClient(String productId) {
    super(Setter
        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductImageServiceGroup"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("ProductImageServiceCommand"))
        .andCommandPropertiesDefaults(
            HystrixCommandProperties.Setter().withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
                .withRequestCacheEnabled(false)
                .withMetricsHealthSnapshotIntervalInMilliseconds(Integer.valueOf("10000"))
                .withCircuitBreakerRequestVolumeThreshold(Integer.valueOf("50"))
                .withCircuitBreakerSleepWindowInMilliseconds(Integer.valueOf("100000"))
                .withExecutionIsolationThreadTimeoutInMilliseconds(Integer.valueOf("3500"))
                .withCircuitBreakerForceOpen(Boolean.getBoolean("false"))
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(Integer.valueOf("100"))
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(Integer.valueOf("50")))
        .andThreadPoolPropertiesDefaults(
            HystrixThreadPoolProperties.Setter().withCoreSize(20).withMaxQueueSize(30).withCoreSize(30)
                .withQueueSizeRejectionThreshold(20)));

    this.productId = productId;
  }

  @Override
  protected String run() throws Exception {
    String imageUrl = null;
    imageUrl = listingServiceClient.getProductImage(this.productId);
    return imageUrl;
  }

  @Override
  protected String getFallback() {
    logger.error("Couldn't retrieve response from product image service. Executing fallback");
    return "";
  }

}
