package com.anilemrah.dolap.dao;

import java.util.List;

import com.anilemrah.dolap.entity.Product;

public interface ProductDAO {

	public List<Product> getProducts();

	public Product saveProduct(Product theProduct);

	public Product updateProduct(Product theProduct);

	public Product getProduct(String theId);

	public void deleteProduct(Product theProduct);

	List<Product> getByProductType(String productType);
}
