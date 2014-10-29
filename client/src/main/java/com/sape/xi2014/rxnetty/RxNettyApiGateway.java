package com.sape.xi2014.rxnetty;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rx.Observable;

import com.sape.xi2014.entity.ClientResponse;
import com.sape.xi2014.entity.Reviews;
import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.search.entity.SearchProtos.Item;
import com.sape.xi2014.service.ServiceMediator;

public class RxNettyApiGateway implements ServiceMediator {

  RxNettySearchCommand searchCommand = new RxNettySearchCommand();
  RxNettyReviewCommand reviewCommand = new RxNettyReviewCommand();
  RxNettyImageCommand imageCommand = new RxNettyImageCommand();

  @Override
  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception {

    List<Tile> tiles = searchCommand.getSearchResults(searchTerm).flatMap(searchResponse -> {

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

        return Observable.from(allTiles);
      }).toList().toBlocking().single();

    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setTiles(new Tiles(tiles));

    return clientResponse;
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
