package com.xi.entities;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "itemattributes", "itemreviews" })
public class SearchResults {

	@JsonProperty("itemattributes")
	private ItemAttributes itemAttributes;

	@JsonProperty("itemreviews")
	private ItemReviews itemReviews;

	@JsonProperty("itemattributes")
	public ItemAttributes getItemAttributes() {
		return itemAttributes;
	}

	@JsonProperty("itemattributes")
	public void setItemAttributes(ItemAttributes itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

	@JsonProperty("itemreviews")
	public ItemReviews getItemReviews() {
		return itemReviews;
	}

	@JsonProperty("itemreviews")
	public void setItemReviews(ItemReviews itemReviews) {
		this.itemReviews = itemReviews;
	}

	public SearchResults withItemAttributes(ItemAttributes itemAttributes) {
		this.itemAttributes = itemAttributes;
		return this;
	}

	public SearchResults withItemReviews(ItemReviews itemReviews) {
		this.itemReviews = itemReviews;
		return this;
	}

	@Override
	public String toString() {
		return "SearchResults [itemAttributes=" + itemAttributes
				+ ", itemReviews=" + itemReviews + "]";
	}

}
