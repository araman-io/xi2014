package com.sape.xi2014.rxnetty;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;


public class RxNettyApiServer {

  static RxNettySearchRoute SEARCH_ROUTE = new RxNettySearchRoute();

  public static void main(String[] args) {
    
    System.out.println("Server => Starting at http://localhost:8080/");
    System.out.println("   Sample URLs: ");
    System.out.println("      - /search");

    RxNetty.createHttpServer(8080, (request, response) -> {
      System.out.println("Server => Request: " + request.getPath());
      return Observable.defer(() -> {
        return handleRoutes(request, response);
      });
    }, PipelineConfigurators.<ByteBuf, ByteBuf>httpServerConfigurator()).startAndWait();

  }

  private static Observable<Void> handleRoutes(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
    // TODO Auto-generated method stub
    // introduce this check when we have interesting things to ==>
    // if(request.getUri().equals("/search")) {
    return SEARCH_ROUTE.handle(request, response);
  }

}
