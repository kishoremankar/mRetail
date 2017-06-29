package app.model;


public class Product {
private ProductInfo info;
private CurrentPrice currentPrice;
public ProductInfo getInfo() {
	return info;
}
public void setInfo(ProductInfo info) {
	this.info = info;
}
public CurrentPrice getCurrentPrice() {
	return currentPrice;
}
public void setCurrentPrice(CurrentPrice currentPrice) {
	this.currentPrice = currentPrice;
}

}
