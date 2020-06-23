package com.anilemrah.dolap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.anilemrah.dolap.entity.Product;

/**
 * 
 * @author eanil
 *
 */
@Component
public class ProductDAOImpl implements ProductDAO {

	private DynamoDBMapper dynamoDBMapper;

	@Override
	public List<Product> getProducts() {
		return this.dynamoDBMapper.scan(Product.class, new DynamoDBScanExpression());
	}

	/**
	 * This class is scanning DynamoDB Table to find products under the given type.
	 * TODO: You can consider to change scan method to Query. That may cause serious
	 * performance issues.
	 */
	@Override
	public List<Product> getByProductType(String productType) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.addFilterCondition("productType", new Condition().withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS(productType)));
		return dynamoDBMapper.scan(Product.class, scanExpression);
	}

	@Autowired
	public ProductDAOImpl(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public Product saveProduct(Product product) {
		dynamoDBMapper.save(product);
		return product;
	}

	@Override
	public Product getProduct(String productId) {
		return dynamoDBMapper.load(Product.class, productId);
	}

	@Override
	public void deleteProduct(String productId) {
		dynamoDBMapper.delete(productId);
	}

	@Override
	public Product updateProduct(Product theProduct) {
		dynamoDBMapper.save(theProduct);
		return theProduct;
	}
}