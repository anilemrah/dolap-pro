package com.anilemrah.dolap.service;

import java.util.Collection;

import com.anilemrah.dolap.entity.ProductType;

public interface ProductTypeService {

	ProductType saveProductType(ProductType productType);

	Collection<ProductType> getProductTypes();

	ProductType getProductType(String productTypeId);

	ProductType updateProductType(ProductType productType);

	void deleteProductType(String productTypeId);
}
