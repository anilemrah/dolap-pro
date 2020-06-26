package com.anilemrah.dolap.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anilemrah.dolap.entity.ProductType;
import com.anilemrah.dolap.service.ProductTypeService;

/**
 * This class is responsible from the all CRUD functionalities of productTypes
 * 
 * @author Anil Emrah
 *
 */
@RestController
@RequestMapping("productType")
public class ProductTypeController {

	@Autowired
	ProductTypeService productTypeService;

	/**
	 * With this method, all products can be listed
	 * 
	 * @return Collection<ProductType>
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<Collection<ProductType>> getProductTypes() {

		return new ResponseEntity<>(productTypeService.getProductTypes(), HttpStatus.OK);
	}

	/**
	 * With this method, user can add new productType
	 * 
	 * @param productType
	 * @return Saved productType
	 */
	@PostMapping(path = "/save")
	public ResponseEntity<ProductType> saveProductType(@RequestBody ProductType productType) {

		return new ResponseEntity<>(productTypeService.saveProductType(productType), HttpStatus.OK);
	}

	/**
	 * If there is a product type with given ID, this method will return the type
	 * 
	 * @param productTypeId
	 * @return product type with given ID, if none 404 NOT FOUND
	 */
	@GetMapping(path = "/{productTypeId}")
	public ResponseEntity<ProductType> getProductType(@PathVariable String productTypeId) {

		ProductType selectedProductType = productTypeService.getProductType(productTypeId);
		if (selectedProductType == null) {
			// There is no product type with given ID
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// Product type found, return with 200OK
		return new ResponseEntity<>(productTypeService.getProductType(productTypeId), HttpStatus.OK);
	}

	/**
	 * Product type can be updated
	 * 
	 * @param productType
	 * @return Updated product type
	 */
	@PutMapping(path = "/update")
	public ResponseEntity<ProductType> updateProductType(@RequestBody ProductType productType) {

		return new ResponseEntity<>(productTypeService.updateProductType(productType), HttpStatus.OK);
	}

	/**
	 * Product type can be deleted with it's ID
	 * 
	 * @param productTypeId
	 * @return
	 */
	@DeleteMapping(path = "/{productTypeId}")
	public ResponseEntity<ProductType> deleteProductType(@PathVariable String productTypeId) {
		productTypeService.deleteProductType(productTypeId);
		return ResponseEntity.noContent().build();
	}
}