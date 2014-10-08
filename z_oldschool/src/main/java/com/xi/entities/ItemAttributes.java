
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
public class ItemAttributes {

@Override
	public String toString() {
		return "ItemAttributes [count=" + count + ", results=" + results
				+ ", additionalProperties=" + additionalProperties + "]";
	}

@JsonProperty("count")
private Integer count;
@JsonProperty("results")
private List<ItemResults> results = new ArrayList<ItemResults>();
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

public ItemAttributes withCount(Integer count) {
this.count = count;
return this;
}

@JsonProperty("results")
public List<ItemResults> getResults() {
return results;
}

@JsonProperty("results")
public void setResults(List<ItemResults> results) {
this.results = results;
}

public ItemAttributes withResults(List<ItemResults> results) {
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

public ItemAttributes withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}