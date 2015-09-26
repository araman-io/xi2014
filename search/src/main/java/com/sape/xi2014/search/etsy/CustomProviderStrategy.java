package com.sape.xi2014.search.etsy;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.AttributeMetadata;

public class CustomProviderStrategy extends AbstractRandomDataProviderStrategy {

    @Override
    public String getStringValue(AttributeMetadata attributeMetadata) {

            if (Result.class.isAssignableFrom(attributeMetadata.getPojoClass())) {

                    if ("url".equals(attributeMetadata.getAttributeName())) {
                            return "http://abc.org/";
                    } 
            }
            return super.getStringValue(attributeMetadata);
    }
}
