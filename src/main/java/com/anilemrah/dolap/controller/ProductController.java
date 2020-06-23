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

import com.anilemrah.dolap.entity.Product;
import com.anilemrah.dolap.service.ProductService;

/**
 * This class is responsible from the all CRUD functionalities of products
 * 
 * @author Anil Emrah
 *
 */
@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * With this method, all products can be listed
	 * 
	 * @return Collection<Product>
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<Collection<Product>> getProducts() {

		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	/**
	 * Products can be searched by product type for special listings. If there is no
	 * product there for the given type, method will return empty array
	 * 
	 * @param productType
	 * @return List of products, if none empty array.
	 */
	@GetMapping(path = "/type/{productType}")
	public ResponseEntity<Collection<Product>> getProductByProductType(@PathVariable String productType) {

		return new ResponseEntity<>(productService.getProductByProductType(productType), HttpStatus.OK);
	}

	/**
	 * With this method, user can add new product
	 * 
	 * @param product
	 * @return Saved product
	 */
	@PostMapping(path = "/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

		return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
	}

	/**
	 * If there is a product with given ID, this method will return the product
	 * 
	 * @param productId
	 * @return product with given ID, if none 404 NOT FOUND
	 */
	@GetMapping(path = "/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable String productId) {

		Product selectedProduct = productService.getProduct(productId);
		if (selectedProduct == null) {
			// There is no product with given ID
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// Product found, return with 200OK
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}

	/**
	 * Product can be updated
	 * 
	 * @param product
	 * @return Updated product
	 */
	@PutMapping(path = "update/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

		return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
	}

	/**
	 * Product can be deleted with it's ID
	 * 
	 * @param productId
	 * @return
	 */
	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
}
