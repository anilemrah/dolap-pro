package com.anilemrah.dolap.ControllerTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.anilemrah.dolap.model.UserResponse;

/**
 * User Integrations Tests
 * 
 * @author Anil Emrah
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserControllerIntegrationTest {

	static String registerUserUrl;
	static String loginUserUrl;
	static String testUserEmailRegistered;
	static JSONObject userRegisterJsonObject;
	static JSONObject userLoginJsonObject;
	static HttpHeaders headers;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeClass
	public static void runBeforeAllTestMethods() throws JSONException {
		registerUserUrl = "http://localhost:8090/user/register";
		loginUserUrl = "http://localhost:8090/user/login";

		Random rand = new Random();

		int userNumber = rand.nextInt(5000);
		testUserEmailRegistered = "test" + userNumber + "@user.com";

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		userRegisterJsonObject = new JSONObject();
		userRegisterJsonObject.put("email", testUserEmailRegistered);
		userRegisterJsonObject.put("firstName", "Test");
		userRegisterJsonObject.put("lastName", "User");
		userRegisterJsonObject.put("password", "testUserPassword");
		// Login request
		userLoginJsonObject = new JSONObject();
		userLoginJsonObject.put("email", testUserEmailRegistered);
		userLoginJsonObject.put("password", "testUserPassword");
	}

	/**
	 * This test method tests the entire Register and Login functionality of the
	 * User class
	 * 
	 * @throws JSONException
	 */
	@Test
	public void RegisterUser_LoginUser_IntegrationTest() throws JSONException {

		// REGISTER
		HttpEntity<String> requestRegister = new HttpEntity<String>(userRegisterJsonObject.toString(), headers);
		ResponseEntity<UserResponse> responseRegister = restTemplate.postForEntity(registerUserUrl, requestRegister,
				UserResponse.class);
		assertNotNull(responseRegister.getBody());
		assertNotNull(responseRegister.getBody().getEmail());
		String testUserEmail = responseRegister.getBody().getEmail();
		assertEquals(testUserEmail, testUserEmailRegistered);
		// CONFLICT
		HttpEntity<String> requestRegisterConflict = new HttpEntity<String>(userRegisterJsonObject.toString(), headers);
		ResponseEntity<UserResponse> responseRegisterConflict = restTemplate.postForEntity(registerUserUrl,
				requestRegisterConflict, UserResponse.class);
		assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), responseRegisterConflict.getStatusCode().getReasonPhrase());
		// Login Success
		HttpEntity<String> requestLogin = new HttpEntity<String>(userLoginJsonObject.toString(), headers);
		ResponseEntity<String> responseLogin = restTemplate.postForEntity(loginUserUrl, requestLogin, String.class);
		assertEquals(HttpStatus.OK.getReasonPhrase(), responseLogin.getStatusCode().getReasonPhrase());
		// Failed Success, wrong password
		userLoginJsonObject.put("password", "testUserWrongPassword");
		HttpEntity<String> requestLoginWrongPassword = new HttpEntity<String>(userLoginJsonObject.toString(), headers);
		ResponseEntity<String> responseLoginWrongPassword = restTemplate.postForEntity(loginUserUrl,
				requestLoginWrongPassword, String.class);
		assertEquals(HttpStatus.FORBIDDEN.getReasonPhrase(),
				responseLoginWrongPassword.getStatusCode().getReasonPhrase());
		// Failed Success, wrong email
		userLoginJsonObject.put("email", "wrongEmail@user.com");
		HttpEntity<String> requestLoginWrongEmail = new HttpEntity<String>(userLoginJsonObject.toString(), headers);
		ResponseEntity<String> responseLoginWrongEmail = restTemplate.postForEntity(loginUserUrl,
				requestLoginWrongEmail, String.class);
		assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseLoginWrongEmail.getStatusCode().getReasonPhrase());

	}
}