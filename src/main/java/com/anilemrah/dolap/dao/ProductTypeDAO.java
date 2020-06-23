package com.anilemrah.dolap.dao;

import java.util.List;

import com.anilemrah.dolap.entity.ProductType;

public interface ProductTypeDAO {

	public List<ProductType> getProductTypes();

	public ProductType saveProductType(ProductType theProductType);

	public ProductType updateProductType(ProductType theProductType);

	public ProductType getProductType(String theproductTypeId);

	public void deleteProductType(String theproductTypeId);
}