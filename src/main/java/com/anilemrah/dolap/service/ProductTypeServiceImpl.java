package com.anilemrah.dolap.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anilemrah.dolap.dao.ProductTypeDAO;
import com.anilemrah.dolap.entity.ProductType;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	private ProductTypeDAO productTypeDAO;
	
	@Autowired
    public ProductTypeServiceImpl(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

	@Override
	public Collection<ProductType> getProductTypes() {
		return productTypeDAO.getProductTypes();
	}

	@Override
	public ProductType saveProductType(ProductType productType) {
		return productTypeDAO.saveProductType(productType);
	}

	@Override
	public ProductType getProductType(String productTypeId) {
		return productTypeDAO.getProductType(productTypeId);
	}

	@Override
	public ProductType updateProductType(ProductType productType) {
		return productTypeDAO.updateProductType(productType);
	}

	@Override
	public void deleteProductType(String productTypeId) {
		productTypeDAO.deleteProductType(productTypeId);
	}
}