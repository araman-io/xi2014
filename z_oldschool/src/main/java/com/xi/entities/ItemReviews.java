
package com.xi.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"count",
"results"
})
public class ItemReviews {

@Override
	public String toString() {
		return "ItemReviews [count=" + count + ", results=" + results
				+ ", additionalProperties=" + additionalProperties + "]";
	}

@JsonProperty("count")
private Integer count;
@JsonProperty("results")
private List<ReviewAttributes> results = new ArrayList<ReviewAttributes>();
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("count")
public Integer getCount() {
return count;
}

@JsonProperty("count")
public void setCount(Integer count) {
this.count = count;
}

public ItemReviews withCount(Integer count) {
this.count = count;
return this;
}

@JsonProperty("results")
public List<ReviewAttributes> getResults() {
return results;
}

@JsonProperty("results")
public void setResults(List<ReviewAttributes> results) {
this.results = results;
}

public ItemReviews withResults(List<ReviewAttributes> results) {
this.results = results;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public ItemReviews withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}