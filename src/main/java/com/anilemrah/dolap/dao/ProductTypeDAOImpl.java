package com.anilemrah.dolap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.anilemrah.dolap.entity.ProductType;

/**
 * 
 * @author Anil Emrah
 *
 */
@Component
public class ProductTypeDAOImpl implements ProductTypeDAO {

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	public ProductTypeDAOImpl(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public List<ProductType> getProductTypes() {
		return this.dynamoDBMapper.scan(ProductType.class, new DynamoDBScanExpression());
	}

	@Override
	public ProductType saveProductType(ProductType productType) {
		dynamoDBMapper.save(productType);
		return productType;
	}

	@Override
	public ProductType getProductType(String productTypeId) {
		return dynamoDBMapper.load(ProductType.class, productTypeId);
	}

	@Override
	public void deleteProductType(ProductType productType) {
		dynamoDBMapper.delete(productType);
	}

	@Override
	public ProductType updateProductType(ProductType theProductType) {
		dynamoDBMapper.save(theProductType);
		return theProductType;
	}
}