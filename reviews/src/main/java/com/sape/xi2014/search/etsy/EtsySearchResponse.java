package com.sape.xi2014.search.etsy;

import java.util.List;

public class EtsySearchResponse {
  Integer count;
  List<Result> results;

  public List<Result> getResults() {
    return results;
  }

  public void setResults(List<Result> results) {
    this.results = results;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

}
