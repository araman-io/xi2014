package com.sape.xi2014.service;

import com.sape.xi2014.entity.ClientResponse;

public interface ServiceMediator {
  
  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception;

}
