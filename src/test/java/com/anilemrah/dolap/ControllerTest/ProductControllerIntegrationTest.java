package com.anilemrah.dolap.ControllerTest;

import static org.junit.Assert.assertNotNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.anilemrah.dolap.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ProductControllerIntegrationTest {

	static String saveProductUrl;
	static JSONObject productJsonObject;
	static HttpHeaders headers;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeClass
	public static void runBeforeAllTestMethods() throws JSONException {
//		saveProductUrl = "http://localhost:8080/product/save";
//
//		headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		productJsonObject = new JSONObject();
//		productJsonObject.put("price", "9999");
//		productJsonObject.put("productName", "Test");
//		productJsonObject.put("productType", "Test");
	}

	@Test
	public void CreateProduct_ReadProduct_IntegrationTest() throws JSONException {
//		HttpEntity<String> request = new HttpEntity<String>(productJsonObject.toString(), headers);
//		System.out.println("productJsonObject: " + productJsonObject.toString());
//		System.out.println("saveProductUrl: " + saveProductUrl.toString());
//		ResponseEntity<Product> responseProduct = restTemplate.postForEntity(saveProductUrl, request, Product.class);
//		System.out.println("postedProduct: " + responseProduct.toString());
//		assertNotNull(responseProduct.getBody());
//		assertNotNull(responseProduct.getBody().getProductName());
//		String testProductId = responseProduct.getBody().getProductId();
//		System.out.println("Product ID: " + testProductId);
//		String response = this.restTemplate.getForObject("/product/all", String.class);
//		JSONAssert.assertEquals("[{price:'9999'}]", response, false);
		
//		restTemplate.delete("http://localhost:8080/product/" + testProductId);
//
//		String response3 = this.restTemplate.getForObject("/product/all", String.class);
//		JSONAssert.assertEquals("[{}]", response3, false);
	}

//	@Test
//	public void getObject() throws JSONException {
//		String response = this.restTemplate.getForObject("/product/all", String.class);
//		JSONAssert.assertEquals("[{price:'9999'}]", response, false);
//	}
//
//	@Test
//	public void updateObject() throws JSONException {
//		productJsonObject.put("price", "8888");
//		HttpEntity<String> request = new HttpEntity<String>(productJsonObject.toString(), headers);
//		restTemplate.put("http://localhost:8080/product/" + testProductId, request);
//
//		String response = this.restTemplate.getForObject("/product/all", String.class);
//		JSONAssert.assertEquals("[{price:'8888'}]", response, false);
//	}
//
//	@Test
//	public void deleteObject() throws JSONException {
//		restTemplate.delete("http://localhost:8080/product/" + testProductId);
//
//		String response = this.restTemplate.getForObject("/product/all", String.class);
//		JSONAssert.assertEquals("[]", response, false);
//	}
}