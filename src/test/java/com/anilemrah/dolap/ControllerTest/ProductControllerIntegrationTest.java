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

/**
 * Product Integrations Tests
 * @author eanil
 *
 */
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
		saveProductUrl = "http://localhost:8090/product/save";

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		productJsonObject = new JSONObject();
		productJsonObject.put("price", "9999");
		productJsonObject.put("productName", "Test");
		productJsonObject.put("productType", "Test");
	}

	/**
	 * This test method tests the entire CRUD functionality of the Product class
	 * 
	 * @throws JSONException
	 */
	@Test
	public void CreateProduct_ReadProduct_IntegrationTest() throws JSONException {

		// CREATE
		HttpEntity<String> requestCreate = new HttpEntity<String>(productJsonObject.toString(), headers);
		ResponseEntity<Product> responseCreate = restTemplate.postForEntity(saveProductUrl, requestCreate,
				Product.class);
		assertNotNull(responseCreate.getBody());
		assertNotNull(responseCreate.getBody().getProductName());
		String testProductId = responseCreate.getBody().getProductId();
		// GET
		String responseGetAll = this.restTemplate.getForObject("/product/" + testProductId, String.class);
		JSONAssert.assertEquals("{price:'9999'}", responseGetAll, false);
		// UPDATE
		productJsonObject.put("price", "8888");
		productJsonObject.put("productId", testProductId);
		HttpEntity<String> requestUpdate = new HttpEntity<String>(productJsonObject.toString(), headers);
		restTemplate.put("http://localhost:8090/product/update", requestUpdate);
		// GET
		String responseGetAll2 = this.restTemplate.getForObject("/product/" + testProductId, String.class);
		JSONAssert.assertEquals("{price:'8888'}", responseGetAll2, false);
		// DELETE
		restTemplate.delete("http://localhost:8090/product/" + testProductId);
		// GET
		String responseGetAll3 = this.restTemplate.getForObject("/product/" + testProductId, String.class);
		JSONAssert.assertEquals(null, responseGetAll3, false);
	}
}