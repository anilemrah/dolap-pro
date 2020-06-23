package com.anilemrah.dolap.service;

import java.util.Collection;

import com.anilemrah.dolap.entity.Product;

public interface ProductService {

	Product saveProduct(Product product);

	Collection<Product> getProducts();

	Product getProduct(String productId);

	Product updateProduct(Product product);

	void deleteProduct(String productId);

	Collection<Product> getProductByProductType(String productType);
}
