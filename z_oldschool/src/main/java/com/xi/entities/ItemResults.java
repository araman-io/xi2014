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
"listing_id",
"state",
"user_id",
"category_id",
"title",
"description",
"creation_tsz",
"ending_tsz",
"original_creation_tsz",
"last_modified_tsz",
"price",
"currency_code",
"quantity",
"tags",
"category_path",
"category_path_ids",
"materials",
"shop_section_id",
"featured_rank",
"state_tsz",
"url",
"views",
"num_favorers",
"shipping_template_id",
"processing_min",
"processing_max",
"who_made",
"is_supply",
"when_made",
"is_private",
"recipient",
"occasion",
"style",
"non_taxable",
"is_customizable",
"is_digital",
"file_data",
"language",
"has_variations",
"used_manufacturer"
})
public class ItemResults {

@Override
	public String toString() {
		return "ItemResults [listingId=" + listingId + ", state=" + state
				+ ", userId=" + userId + ", categoryId=" + categoryId
				+ ", title=" + title + ", description=" + description
				+ ", creationTsz=" + creationTsz + ", endingTsz=" + endingTsz
				+ ", originalCreationTsz=" + originalCreationTsz
				+ ", lastModifiedTsz=" + lastModifiedTsz + ", price=" + price
				+ ", currencyCode=" + currencyCode + ", quantity=" + quantity
				+ ", tags=" + tags + ", categoryPath=" + categoryPath
				+ ", categoryPathIds=" + categoryPathIds + ", materials="
				+ materials + ", shopSectionId=" + shopSectionId
				+ ", featuredRank=" + featuredRank + ", stateTsz=" + stateTsz
				+ ", url=" + url + ", views=" + views + ", numFavorers="
				+ numFavorers + ", shippingTemplateId=" + shippingTemplateId
				+ ", processingMin=" + processingMin + ", processingMax="
				+ processingMax + ", whoMade=" + whoMade + ", isSupply="
				+ isSupply + ", whenMade=" + whenMade + ", isPrivate="
				+ isPrivate + ", recipient=" + recipient + ", occasion="
				+ occasion + ", style=" + style + ", nonTaxable=" + nonTaxable
				+ ", isCustomizable=" + isCustomizable + ", isDigital="
				+ isDigital + ", fileData=" + fileData + ", language="
				+ language + ", hasVariations=" + hasVariations
				+ ", usedManufacturer=" + usedManufacturer
				+ ", additionalProperties=" + additionalProperties + "]";
	}

@JsonProperty("listing_id")
private Integer listingId;
@JsonProperty("state")
private String state;
@JsonProperty("user_id")
private Integer userId;
@JsonProperty("category_id")
private Integer categoryId;
@JsonProperty("title")
private String title;
@JsonProperty("description")
private String description;
@JsonProperty("creation_tsz")
private Integer creationTsz;
@JsonProperty("ending_tsz")
private Integer endingTsz;
@JsonProperty("original_creation_tsz")
private Integer originalCreationTsz;
@JsonProperty("last_modified_tsz")
private Integer lastModifiedTsz;
@JsonProperty("price")
private String price;
@JsonProperty("currency_code")
private String currencyCode;
@JsonProperty("quantity")
private Integer quantity;
@JsonProperty("tags")
private List<String> tags = new ArrayList<String>();
@JsonProperty("category_path")
private List<String> categoryPath = new ArrayList<String>();
@JsonProperty("category_path_ids")
private List<Integer> categoryPathIds = new ArrayList<Integer>();
@JsonProperty("materials")
private List<Object> materials = new ArrayList<Object>();
@JsonProperty("shop_section_id")
private Integer shopSectionId;
@JsonProperty("featured_rank")
private Object featuredRank;
@JsonProperty("state_tsz")
private Integer stateTsz;
@JsonProperty("url")
private String url;
@JsonProperty("views")
private Integer views;
@JsonProperty("num_favorers")
private Integer numFavorers;
@JsonProperty("shipping_template_id")
private Object shippingTemplateId;
@JsonProperty("processing_min")
private Integer processingMin;
@JsonProperty("processing_max")
private Integer processingMax;
@JsonProperty("who_made")
private String whoMade;
@JsonProperty("is_supply")
private String isSupply;
@JsonProperty("when_made")
private String whenMade;
@JsonProperty("is_private")
private Boolean isPrivate;
@JsonProperty("recipient")
private String recipient;
@JsonProperty("occasion")
private String occasion;
@JsonProperty("style")
private List<String> style = new ArrayList<String>();
@JsonProperty("non_taxable")
private Boolean nonTaxable;
@JsonProperty("is_customizable")
private Boolean isCustomizable;
@JsonProperty("is_digital")
private Boolean isDigital;
@JsonProperty("file_data")
private String fileData;
@JsonProperty("language")
private String language;
@JsonProperty("has_variations")
private Boolean hasVariations;
@JsonProperty("used_manufacturer")
private Boolean usedManufacturer;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("listing_id")
public Integer getListingId() {
return listingId;
}

@JsonProperty("listing_id")
public void setListingId(Integer listingId) {
this.listingId = listingId;
}

public ItemResults withListingId(Integer listingId) {
this.listingId = listingId;
return this;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

public ItemResults withState(String state) {
this.state = state;
return this;
}

@JsonProperty("user_id")
public Integer getUserId() {
return userId;
}

@JsonProperty("user_id")
public void setUserId(Integer userId) {
this.userId = userId;
}

public ItemResults withUserId(Integer userId) {
this.userId = userId;
return this;
}

@JsonProperty("category_id")
public Integer getCategoryId() {
return categoryId;
}

@JsonProperty("category_id")
public void setCategoryId(Integer categoryId) {
this.categoryId = categoryId;
}

public ItemResults withCategoryId(Integer categoryId) {
this.categoryId = categoryId;
return this;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

public ItemResults withTitle(String title) {
this.title = title;
return this;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

public ItemResults withDescription(String description) {
this.description = description;
return this;
}

@JsonProperty("creation_tsz")
public Integer getCreationTsz() {
return creationTsz;
}

@JsonProperty("creation_tsz")
public void setCreationTsz(Integer creationTsz) {
this.creationTsz = creationTsz;
}

public ItemResults withCreationTsz(Integer creationTsz) {
this.creationTsz = creationTsz;
return this;
}

@JsonProperty("ending_tsz")
public Integer getEndingTsz() {
return endingTsz;
}

@JsonProperty("ending_tsz")
public void setEndingTsz(Integer endingTsz) {
this.endingTsz = endingTsz;
}

public ItemResults withEndingTsz(Integer endingTsz) {
this.endingTsz = endingTsz;
return this;
}

@JsonProperty("original_creation_tsz")
public Integer getOriginalCreationTsz() {
return originalCreationTsz;
}

@JsonProperty("original_creation_tsz")
public void setOriginalCreationTsz(Integer originalCreationTsz) {
this.originalCreationTsz = originalCreationTsz;
}

public ItemResults withOriginalCreationTsz(Integer originalCreationTsz) {
this.originalCreationTsz = originalCreationTsz;
return this;
}

@JsonProperty("last_modified_tsz")
public Integer getLastModifiedTsz() {
return lastModifiedTsz;
}

@JsonProperty("last_modified_tsz")
public void setLastModifiedTsz(Integer lastModifiedTsz) {
this.lastModifiedTsz = lastModifiedTsz;
}

public ItemResults withLastModifiedTsz(Integer lastModifiedTsz) {
this.lastModifiedTsz = lastModifiedTsz;
return this;
}

@JsonProperty("price")
public String getPrice() {
return price;
}

@JsonProperty("price")
public void setPrice(String price) {
this.price = price;
}

public ItemResults withPrice(String price) {
this.price = price;
return this;
}

@JsonProperty("currency_code")
public String getCurrencyCode() {
return currencyCode;
}

@JsonProperty("currency_code")
public void setCurrencyCode(String currencyCode) {
this.currencyCode = currencyCode;
}

public ItemResults withCurrencyCode(String currencyCode) {
this.currencyCode = currencyCode;
return this;
}

@JsonProperty("quantity")
public Integer getQuantity() {
return quantity;
}

@JsonProperty("quantity")
public void setQuantity(Integer quantity) {
this.quantity = quantity;
}

public ItemResults withQuantity(Integer quantity) {
this.quantity = quantity;
return this;
}

@JsonProperty("tags")
public List<String> getTags() {
return tags;
}

@JsonProperty("tags")
public void setTags(List<String> tags) {
this.tags = tags;
}

public ItemResults withTags(List<String> tags) {
this.tags = tags;
return this;
}

@JsonProperty("category_path")
public List<String> getCategoryPath() {
return categoryPath;
}

@JsonProperty("category_path")
public void setCategoryPath(List<String> categoryPath) {
this.categoryPath = categoryPath;
}

public ItemResults withCategoryPath(List<String> categoryPath) {
this.categoryPath = categoryPath;
return this;
}

@JsonProperty("category_path_ids")
public List<Integer> getCategoryPathIds() {
return categoryPathIds;
}

@JsonProperty("category_path_ids")
public void setCategoryPathIds(List<Integer> categoryPathIds) {
this.categoryPathIds = categoryPathIds;
}

public ItemResults withCategoryPathIds(List<Integer> categoryPathIds) {
this.categoryPathIds = categoryPathIds;
return this;
}

@JsonProperty("materials")
public List<Object> getMaterials() {
return materials;
}

@JsonProperty("materials")
public void setMaterials(List<Object> materials) {
this.materials = materials;
}

public ItemResults withMaterials(List<Object> materials) {
this.materials = materials;
return this;
}

@JsonProperty("shop_section_id")
public Integer getShopSectionId() {
return shopSectionId;
}

@JsonProperty("shop_section_id")
public void setShopSectionId(Integer shopSectionId) {
this.shopSectionId = shopSectionId;
}

public ItemResults withShopSectionId(Integer shopSectionId) {
this.shopSectionId = shopSectionId;
return this;
}

@JsonProperty("featured_rank")
public Object getFeaturedRank() {
return featuredRank;
}

@JsonProperty("featured_rank")
public void setFeaturedRank(Object featuredRank) {
this.featuredRank = featuredRank;
}

public ItemResults withFeaturedRank(Object featuredRank) {
this.featuredRank = featuredRank;
return this;
}

@JsonProperty("state_tsz")
public Integer getStateTsz() {
return stateTsz;
}

@JsonProperty("state_tsz")
public void setStateTsz(Integer stateTsz) {
this.stateTsz = stateTsz;
}

public ItemResults withStateTsz(Integer stateTsz) {
this.stateTsz = stateTsz;
return this;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

public ItemResults withUrl(String url) {
this.url = url;
return this;
}

@JsonProperty("views")
public Integer getViews() {
return views;
}

@JsonProperty("views")
public void setViews(Integer views) {
this.views = views;
}

public ItemResults withViews(Integer views) {
this.views = views;
return this;
}

@JsonProperty("num_favorers")
public Integer getNumFavorers() {
return numFavorers;
}

@JsonProperty("num_favorers")
public void setNumFavorers(Integer numFavorers) {
this.numFavorers = numFavorers;
}

public ItemResults withNumFavorers(Integer numFavorers) {
this.numFavorers = numFavorers;
return this;
}

@JsonProperty("shipping_template_id")
public Object getShippingTemplateId() {
return shippingTemplateId;
}

@JsonProperty("shipping_template_id")
public void setShippingTemplateId(Object shippingTemplateId) {
this.shippingTemplateId = shippingTemplateId;
}

public ItemResults withShippingTemplateId(Object shippingTemplateId) {
this.shippingTemplateId = shippingTemplateId;
return this;
}

@JsonProperty("processing_min")
public Integer getProcessingMin() {
return processingMin;
}

@JsonProperty("processing_min")
public void setProcessingMin(Integer processingMin) {
this.processingMin = processingMin;
}

public ItemResults withProcessingMin(Integer processingMin) {
this.processingMin = processingMin;
return this;
}

@JsonProperty("processing_max")
public Integer getProcessingMax() {
return processingMax;
}

@JsonProperty("processing_max")
public void setProcessingMax(Integer processingMax) {
this.processingMax = processingMax;
}

public ItemResults withProcessingMax(Integer processingMax) {
this.processingMax = processingMax;
return this;
}

@JsonProperty("who_made")
public String getWhoMade() {
return whoMade;
}

@JsonProperty("who_made")
public void setWhoMade(String whoMade) {
this.whoMade = whoMade;
}

public ItemResults withWhoMade(String whoMade) {
this.whoMade = whoMade;
return this;
}

@JsonProperty("is_supply")
public String getIsSupply() {
return isSupply;
}

@JsonProperty("is_supply")
public void setIsSupply(String isSupply) {
this.isSupply = isSupply;
}

public ItemResults withIsSupply(String isSupply) {
this.isSupply = isSupply;
return this;
}

@JsonProperty("when_made")
public String getWhenMade() {
return whenMade;
}

@JsonProperty("when_made")
public void setWhenMade(String whenMade) {
this.whenMade = whenMade;
}

public ItemResults withWhenMade(String whenMade) {
this.whenMade = whenMade;
return this;
}

@JsonProperty("is_private")
public Boolean getIsPrivate() {
return isPrivate;
}

@JsonProperty("is_private")
public void setIsPrivate(Boolean isPrivate) {
this.isPrivate = isPrivate;
}

public ItemResults withIsPrivate(Boolean isPrivate) {
this.isPrivate = isPrivate;
return this;
}

@JsonProperty("recipient")
public String getRecipient() {
return recipient;
}

@JsonProperty("recipient")
public void setRecipient(String recipient) {
this.recipient = recipient;
}

public ItemResults withRecipient(String recipient) {
this.recipient = recipient;
return this;
}

@JsonProperty("occasion")
public String getOccasion() {
return occasion;
}

@JsonProperty("occasion")
public void setOccasion(String occasion) {
this.occasion = occasion;
}

public ItemResults withOccasion(String occasion) {
this.occasion = occasion;
return this;
}

@JsonProperty("style")
public List<String> getStyle() {
return style;
}

@JsonProperty("style")
public void setStyle(List<String> style) {
this.style = style;
}

public ItemResults withStyle(List<String> style) {
this.style = style;
return this;
}

@JsonProperty("non_taxable")
public Boolean getNonTaxable() {
return nonTaxable;
}

@JsonProperty("non_taxable")
public void setNonTaxable(Boolean nonTaxable) {
this.nonTaxable = nonTaxable;
}

public ItemResults withNonTaxable(Boolean nonTaxable) {
this.nonTaxable = nonTaxable;
return this;
}

@JsonProperty("is_customizable")
public Boolean getIsCustomizable() {
return isCustomizable;
}

@JsonProperty("is_customizable")
public void setIsCustomizable(Boolean isCustomizable) {
this.isCustomizable = isCustomizable;
}

public ItemResults withIsCustomizable(Boolean isCustomizable) {
this.isCustomizable = isCustomizable;
return this;
}

@JsonProperty("is_digital")
public Boolean getIsDigital() {
return isDigital;
}

@JsonProperty("is_digital")
public void setIsDigital(Boolean isDigital) {
this.isDigital = isDigital;
}

public ItemResults withIsDigital(Boolean isDigital) {
this.isDigital = isDigital;
return this;
}

@JsonProperty("file_data")
public String getFileData() {
return fileData;
}

@JsonProperty("file_data")
public void setFileData(String fileData) {
this.fileData = fileData;
}

public ItemResults withFileData(String fileData) {
this.fileData = fileData;
return this;
}

@JsonProperty("language")
public String getLanguage() {
return language;
}

@JsonProperty("language")
public void setLanguage(String language) {
this.language = language;
}

public ItemResults withLanguage(String language) {
this.language = language;
return this;
}

@JsonProperty("has_variations")
public Boolean getHasVariations() {
return hasVariations;
}

@JsonProperty("has_variations")
public void setHasVariations(Boolean hasVariations) {
this.hasVariations = hasVariations;
}

public ItemResults withHasVariations(Boolean hasVariations) {
this.hasVariations = hasVariations;
return this;
}

@JsonProperty("used_manufacturer")
public Boolean getUsedManufacturer() {
return usedManufacturer;
}

@JsonProperty("used_manufacturer")
public void setUsedManufacturer(Boolean usedManufacturer) {
this.usedManufacturer = usedManufacturer;
}

public ItemResults withUsedManufacturer(Boolean usedManufacturer) {
this.usedManufacturer = usedManufacturer;
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

public ItemResults withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}