package com.anilemrah.dolap.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "product")
public class Product {
	@DynamoDBHashKey(attributeName = "productId")
	@DynamoDBAutoGeneratedKey
	private String productId;
	@DynamoDBAttribute(attributeName = "productName")
	private String productName;
	@DynamoDBAttribute(attributeName = "price")
	private String price;
	@DynamoDBAttribute(attributeName = "productType")
	private String productType;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", productType="
				+ productType + "]";
	}
}