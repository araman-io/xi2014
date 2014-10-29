package com.sape.xi2014.rxnetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.http.HttpMethod;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import rx.Observable;

import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;

public class RxNettySearchCommand {

  public Observable<SearchResponse> getSearchResults(String searchTerm) {

    Observable<SearchResponse> r = null;

    r =
        RxNetty.createHttpClient("localhost", 4567, PipelineConfigurators.<String, ByteBuf>httpClientConfigurator())
            .submit(HttpClientRequest.create(HttpMethod.GET, "/search/bykeyword?searchTerm=" + searchTerm))
            .flatMap(httpClientResponse -> {

              Observable<SearchResponse> searchResult = null;

              // convert the HttpClientResponse to a SearchResult
                searchResult = httpClientResponse.getContent().flatMap(byteResponse -> {
                  ByteBufInputStream responseInputStream = null;
                  SearchResponse parsedResult = null;

                  responseInputStream = new ByteBufInputStream(byteResponse);
                  try {
                    parsedResult = SearchResponse.parseFrom(responseInputStream);
                  } catch (Exception e) {
                    // TODO:: better error handling. Can I use onError() ??
                    e.printStackTrace();
                  }

                  return Observable.just(parsedResult);
                });

                return searchResult;
              });

    return r;

  }

}