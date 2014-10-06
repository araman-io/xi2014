package com.sape.xi2014.flow.b.withmicrosvc;

import com.sape.xi2014.entity.ClientReponse;

public interface ServiceMediator {
  
  public ClientReponse getAggregatedResponse(String searchTerm) throws Exception;

}
