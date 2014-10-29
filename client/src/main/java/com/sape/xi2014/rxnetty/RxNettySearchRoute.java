package com.sape.xi2014.rxnetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import com.google.gson.Gson;
import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.search.entity.SearchProtos.Item;

public class RxNettySearchRoute {

  RxNettySearchCommand searchCommand = new RxNettySearchCommand();
  RxNettyReviewCommand reviewCommand = new RxNettyReviewCommand();
  RxNettyImageCommand imageCommand = new RxNettyImageCommand();

  public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {

    String searchTerm = request.getQueryParameters().get("searchTerm").get(0);

    Observable<Void> mandatoryReturn = searchCommand.getSearchResults(searchTerm).flatMap(searchResponse -> {

      List<Tile> allTiles = new ArrayList<Tile>();


      // iterate over all items to get the reviews and images
        for (Item item : searchResponse.getItemList()) {
          Observable<Reviews> reviews = reviewCommand.getSellerReviews(item.getUserId());
          Observable<String> image = imageCommand.getProductImage(item.getId());


          // compose the tile
          Tile t = this.getTileFromItem(item);

          reviews.subscribe(r -> {
            t.setReviews(r);
          });

          image.subscribe(img -> {
            t.setImageUrl(img);
          });

          allTiles.add(t);
        }

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setTiles(new Tiles(allTiles));

        return Observable.just(clientResponse);
      })
      .flatMap(data -> {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(data);
        System.out.println(jsonResponse);
        response.writeString(jsonResponse);
        return response.close(true);
      });
    
    return mandatoryReturn;

  }

  protected Tile getTileFromItem(Item i) {
    Tile t = null;

    t = new Tile();
    t.setProductId(i.getId());
    t.setSellerId(i.getUserId());
    t.setDescription(i.getDescription());
    t.setState(i.getState());
    t.setUrl(i.getUrl());

    return t;
  }

}
