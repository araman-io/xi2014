package com.sape.xi2014.client;

import com.sape.xi2014.client.entity.ClientReponse;

public interface ServiceMediator {
  
  public ClientReponse getAggregatedResponse(String searchTerm) throws Exception;

}
