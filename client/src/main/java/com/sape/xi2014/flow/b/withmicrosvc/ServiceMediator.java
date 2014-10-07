package com.sape.xi2014.flow.b.withmicrosvc;

import com.sape.xi2014.entity.ClientResponse;

public interface ServiceMediator {
  
  public ClientResponse getAggregatedResponse(String searchTerm) throws Exception;

}
