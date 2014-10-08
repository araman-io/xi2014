
package com.xi.entities;

import java.util.HashMap;
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
"feedback_id",
"creation_tsz",
"message",
"value",
"seller_user_id",
"transaction_id",
"image_feedback_id",
"image_url_25x25",
"image_url_155x125",
"image_url_fullxfull",
"target_user_id"
})
public class ReviewAttributes {

@JsonProperty("feedback_id")
private Integer feedbackId;
@JsonProperty("creation_tsz")
private Integer creationTsz;
@JsonProperty("message")
private String message;
@JsonProperty("value")
private Integer value;
@JsonProperty("seller_user_id")
private Integer sellerUserId;
@JsonProperty("transaction_id")
private Integer transactionId;
@JsonProperty("image_feedback_id")
private Object imageFeedbackId;
@JsonProperty("image_url_25x25")
private String imageUrl25x25;
@JsonProperty("image_url_155x125")
private String imageUrl155x125;
@JsonProperty("image_url_fullxfull")
private String imageUrlFullxfull;
@JsonProperty("target_user_id")
private Integer targetUserId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("feedback_id")
public Integer getFeedbackId() {
return feedbackId;
}

@JsonProperty("feedback_id")
public void setFeedbackId(Integer feedbackId) {
this.feedbackId = feedbackId;
}

public ReviewAttributes withFeedbackId(Integer feedbackId) {
this.feedbackId = feedbackId;
return this;
}

@Override
public String toString() {
	return "ReviewAttributes [feedbackId=" + feedbackId + ", creationTsz="
			+ creationTsz + ", message=" + message + ", value=" + value
			+ ", sellerUserId=" + sellerUserId + ", transactionId="
			+ transactionId + ", imageFeedbackId=" + imageFeedbackId
			+ ", imageUrl25x25=" + imageUrl25x25 + ", imageUrl155x125="
			+ imageUrl155x125 + ", imageUrlFullxfull=" + imageUrlFullxfull
			+ ", targetUserId=" + targetUserId + ", additionalProperties="
			+ additionalProperties + "]";
}

@JsonProperty("creation_tsz")
public Integer getCreationTsz() {
return creationTsz;
}

@JsonProperty("creation_tsz")
public void setCreationTsz(Integer creationTsz) {
this.creationTsz = creationTsz;
}

public ReviewAttributes withCreationTsz(Integer creationTsz) {
this.creationTsz = creationTsz;
return this;
}

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String message) {
this.message = message;
}

public ReviewAttributes withMessage(String message) {
this.message = message;
return this;
}

@JsonProperty("value")
public Integer getValue() {
return value;
}

@JsonProperty("value")
public void setValue(Integer value) {
this.value = value;
}

public ReviewAttributes withValue(Integer value) {
this.value = value;
return this;
}

@JsonProperty("seller_user_id")
public Integer getSellerUserId() {
return sellerUserId;
}

@JsonProperty("seller_user_id")
public void setSellerUserId(Integer sellerUserId) {
this.sellerUserId = sellerUserId;
}

public ReviewAttributes withSellerUserId(Integer sellerUserId) {
this.sellerUserId = sellerUserId;
return this;
}

@JsonProperty("transaction_id")
public Integer getTransactionId() {
return transactionId;
}

@JsonProperty("transaction_id")
public void setTransactionId(Integer transactionId) {
this.transactionId = transactionId;
}

public ReviewAttributes withTransactionId(Integer transactionId) {
this.transactionId = transactionId;
return this;
}

@JsonProperty("image_feedback_id")
public Object getImageFeedbackId() {
return imageFeedbackId;
}

@JsonProperty("image_feedback_id")
public void setImageFeedbackId(Object imageFeedbackId) {
this.imageFeedbackId = imageFeedbackId;
}

public ReviewAttributes withImageFeedbackId(Object imageFeedbackId) {
this.imageFeedbackId = imageFeedbackId;
return this;
}

@JsonProperty("image_url_25x25")
public String getImageUrl25x25() {
return imageUrl25x25;
}

@JsonProperty("image_url_25x25")
public void setImageUrl25x25(String imageUrl25x25) {
this.imageUrl25x25 = imageUrl25x25;
}

public ReviewAttributes withImageUrl25x25(String imageUrl25x25) {
this.imageUrl25x25 = imageUrl25x25;
return this;
}

@JsonProperty("image_url_155x125")
public String getImageUrl155x125() {
return imageUrl155x125;
}

@JsonProperty("image_url_155x125")
public void setImageUrl155x125(String imageUrl155x125) {
this.imageUrl155x125 = imageUrl155x125;
}

public ReviewAttributes withImageUrl155x125(String imageUrl155x125) {
this.imageUrl155x125 = imageUrl155x125;
return this;
}

@JsonProperty("image_url_fullxfull")
public String getImageUrlFullxfull() {
return imageUrlFullxfull;
}

@JsonProperty("image_url_fullxfull")
public void setImageUrlFullxfull(String imageUrlFullxfull) {
this.imageUrlFullxfull = imageUrlFullxfull;
}

public ReviewAttributes withImageUrlFullxfull(String imageUrlFullxfull) {
this.imageUrlFullxfull = imageUrlFullxfull;
return this;
}

@JsonProperty("target_user_id")
public Integer getTargetUserId() {
return targetUserId;
}

@JsonProperty("target_user_id")
public void setTargetUserId(Integer targetUserId) {
this.targetUserId = targetUserId;
}

public ReviewAttributes withTargetUserId(Integer targetUserId) {
this.targetUserId = targetUserId;
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

public ReviewAttributes withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}