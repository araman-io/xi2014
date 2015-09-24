package com.sape.xi2014.listing.domain;

import java.util.List;

public class EtsyReview {
  
  Integer count;
  
  List<Result> results;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<Result> getResults() {
    return results;
  }

  public void setResults(List<Result> results) {
    this.results = results;
  }

}
