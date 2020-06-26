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

import com.anilemrah.dolap.entity.ProductType;

/**
 * Product Type Integrations Tests
 * 
 * @author eanil
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ProductTypeControllerIntegrationTest {

	static String saveProductTypeUrl;
	static JSONObject productTypeJsonObject;
	static HttpHeaders headers;

	@Autowired
	private TestRestTemplate restTemplate;
 
	@BeforeClass
	public static void runBeforeAllTestMethods() throws JSONException {
		saveProductTypeUrl = "http://localhost:8090/productType/save";

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		productTypeJsonObject = new JSONObject();
		productTypeJsonObject.put("productTypeName", "TestProductType");
	}

	/**
	 * This test method tests the entire CRUD functionality of the Product class
	 * 
	 * @throws JSONException
	 */
	@Test
	public void Create_Read_Update_Delete_ProductType_IntegrationTest() throws JSONException {

		// CREATE
		HttpEntity<String> requestCreate = new HttpEntity<String>(productTypeJsonObject.toString(), headers);
		ResponseEntity<ProductType> responseCreate = restTemplate.postForEntity(saveProductTypeUrl, requestCreate,
				ProductType.class);
		assertNotNull(responseCreate.getBody());
		assertNotNull(responseCreate.getBody().getProductTypeName());
		String testProductTypeId = responseCreate.getBody().getProductTypeId();
		// GET
		String responseGetAll = this.restTemplate.getForObject("/productType/" + testProductTypeId, String.class);
		JSONAssert.assertEquals("{productTypeName:'TestProductType'}", responseGetAll, false);
		// UPDATE
		productTypeJsonObject.put("productTypeName", "UpdatedTestProductType");
		productTypeJsonObject.put("productTypeId", testProductTypeId);
		HttpEntity<String> requestUpdate = new HttpEntity<String>(productTypeJsonObject.toString(), headers);
		restTemplate.put("http://localhost:8090/productType/update", requestUpdate);
		// GET
		String responseGetAll2 = this.restTemplate.getForObject("/productType/" + testProductTypeId, String.class);
		JSONAssert.assertEquals("{productTypeName:'UpdatedTestProductType'}", responseGetAll2, false);
		// DELETE
		restTemplate.delete("http://localhost:8090/productType/" + testProductTypeId);
		// GET
		String responseGetAll3 = this.restTemplate.getForObject("/productType/" + testProductTypeId, String.class);
		System.out.println("responseGetAll3: " + responseGetAll3);
		JSONAssert.assertEquals(null, responseGetAll3, false);
	}
}