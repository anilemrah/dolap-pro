package com.anilemrah.dolap.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anilemrah.dolap.dao.ProductDAO;
import com.anilemrah.dolap.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

	@Override
	public Collection<Product> getProducts() {
		return productDAO.getProducts();
	}
	
	@Override
	public Collection<Product> getProductByProductType(String productType) {
		return productDAO.getByProductType(productType);
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
    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
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
