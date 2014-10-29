package com.sape.xi2014.rxnetty;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpMethod;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import com.google.gson.Gson;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.flow.b.withmicrosvc.stub.EtsyReview;
import com.sape.xi2014.flow.b.withmicrosvc.stub.Result;

public class RxNettyReviewCommand {

  public Observable<Reviews> getSellerReviews(String sellerId) {

    Observable<Reviews> response = null;

    response =
        RxNetty
            .createHttpClient("localhost", 4568, PipelineConfigurators.<String, ByteBuf>httpClientConfigurator())
            .submit(HttpClientRequest.create(HttpMethod.GET, "/listing/reviews/seller?sellerId=".concat(sellerId)))
            .flatMap(httpClientResponse -> {


              // take the httpclient response and convert it to a Review object
                return httpClientResponse.getContent().flatMap(
                    reviewsAsByteBuf -> {

                      Gson json = new Gson();
                      List<String> messages = new ArrayList<String>();

                      EtsyReview sellerReview =
                          json.fromJson(reviewsAsByteBuf.toString(Charset.defaultCharset()), EtsyReview.class);
                      for (Result r : sellerReview.getResults()) {
                        messages.add(r.getMessage());
                      }

                      Reviews reviews = new Reviews();
                      reviews.setCount(sellerReview.getCount());
                      reviews.setMessage(messages);

                      return Observable.just(reviews);
                    });

              });

    return response;

  }


}
