package app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "current_price")
public class CurrentPrice {
private Double value;
private String currency_code;
private Long productId;
@Id
private String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Long getProductId() {
	return productId;
}
public void setProductId(Long productId) {
	this.productId = productId;
}
public Double getValue() {
	return value;
}
public void setValue(Double value) {
	this.value = value;
}
public String getCurrency_code() {
	return currency_code;
}
public void setCurrency_code(String currency_code) {
	this.currency_code = currency_code;
}

}
