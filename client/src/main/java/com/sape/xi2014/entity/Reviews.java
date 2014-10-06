package com.sape.xi2014.entity;

import java.util.List;

public class Reviews {
  Integer count;
  List<String> message;
  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }
  public List<String> getMessage() {
    return message;
  }
  public void setMessage(List<String> message) {
    this.message = message;
  }
}