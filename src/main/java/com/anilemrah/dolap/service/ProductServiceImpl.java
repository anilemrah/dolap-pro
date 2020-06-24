package com.anilemrah.dolap.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anilemrah.dolap.dao.ProductDAO;
import com.anilemrah.dolap.entity.Product;
import com.anilemrah.dolap.exceptions.product.DolapProductException;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	@Override
	public Collection<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public Collection<Product> getProductByProductType(String productType) throws DolapProductException {

		List<Product> productList = productDAO.getByProductType(productType);

		if (productList.isEmpty()) {
			throw new DolapProductException("There is no product with the type: " + productType);
		}

		return productList;
	}

	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public Product saveProduct(Product product) {
		return productDAO.saveProduct(product);
	}

	@Override	
	public Product getProduct(String productId) throws DolapProductException {

		Product product = productDAO.getProduct(productId);

		if (product == null) {
			throw new DolapProductException("Product not found with the ID: " + productId);
		}

		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}

	@Override
	public void deleteProduct(String productId) {
		productDAO.deleteProduct(productId);
	}
}
