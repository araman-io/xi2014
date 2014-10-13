package com.sape.xi2014.flow.b.withmicrosvc.stub;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.fluent.Request;

import com.sape.xi2014.entity.Tile;
import com.sape.xi2014.entity.Tiles;
import com.sape.xi2014.search.entity.SearchProtos.Item;
import com.sape.xi2014.search.entity.SearchProtos.SearchResponse;

public class SearchServiceClient {

  public Tiles getSearchResults(String searchTerm) throws Exception {


    long time = System.currentTimeMillis();
    InputStream asStream =
        Request
            .Get("http://localhost:4567/search/bykeyword?searchTerm=".concat(URLEncoder.encode(searchTerm, "UTF-8")))
            .execute().returnContent().asStream();
    SearchResponse parsedResponse = SearchResponse.parseFrom(asStream);

    List<Tile> tiles = new ArrayList<Tile>();

    for (Item i : parsedResponse.getItemList()) {
      Tile t = new Tile();
      t.setProductId(i.getId());
      t.setSellerId(i.getUserId());
      t.setDescription(i.getDescription());
      t.setState(i.getState());
      t.setUrl(i.getUrl());

      tiles.add(t);
    }

    Tiles tileContainer = new Tiles();
    tileContainer.setCount(parsedResponse.getCount());
    tileContainer.setTiles(tiles);

    return tileContainer;
  }

}
