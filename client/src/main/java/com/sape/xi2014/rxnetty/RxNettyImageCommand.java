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
import com.sape.xi2014.flow.b.withmicrosvc.stub.EtsyImage;

public class RxNettyImageCommand {

  public Observable<String> getProductImage(String productId) {

    Observable<String> response = null;

    response =
        RxNetty
            .createHttpClient("localhost", 4568, PipelineConfigurators.<String, ByteBuf>httpClientConfigurator())
            .submit(HttpClientRequest.create(HttpMethod.GET, "/listing/images?productId=".concat(productId)))
            .flatMap(httpClientResponse -> {
              return httpClientResponse.getContent();
            })
            .flatMap(imageAsByteBuf -> {
              // take the httpclient response and convert it to a Review object
                Gson json = new Gson();
                List<String> messages = new ArrayList<String>();

                EtsyImage productImage =
                    json.fromJson(imageAsByteBuf.toString(Charset.defaultCharset()), EtsyImage.class);

                String url = productImage.getResults().get(0).getUrl_fullxfull();

                return Observable.just(url);
              });

    return response;

  }


}
