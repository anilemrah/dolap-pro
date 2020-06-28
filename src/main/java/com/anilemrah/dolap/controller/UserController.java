package com.anilemrah.dolap.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anilemrah.dolap.dto.UserDto;
import com.anilemrah.dolap.entity.DolapUser;
import com.anilemrah.dolap.exceptions.user.DolapUserException;
import com.anilemrah.dolap.model.UserLoginRequest;
import com.anilemrah.dolap.model.UserRequest;
import com.anilemrah.dolap.model.UserResponse;
import com.anilemrah.dolap.service.UserService;

/**
 * This class is responsible from the all functionalities of Users TODO This
 * part is the most important part of the backend. Authentication must be added.
 * But for now it stays as a TODO
 * 
 * @author Anil Emrah
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * With this method, user can register If username already exists, method will
	 * return 409 CONFLICT otherwise user will be registered
	 * 
	 * @param user
	 * @return registered user or CONFLICT
	 */
	@PostMapping(path = "/register")
	public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
		try {
			// Get the information from the request
			// Create objects
			UserResponse response = new UserResponse();
			UserDto userDto = new UserDto();
			// Copy request to DTO
			BeanUtils.copyProperties(userRequest, userDto);
			// Register user with User Service
			UserDto createdUser = userService.registerUser(userDto);
			// Fill the response if everything is OK
			// Otherwise Exception will be thrown
			BeanUtils.copyProperties(createdUser, response);
			// Return the response
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DolapUserException e) {
			// Registration process failed
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * User login method If userName is not registered or password is wrong 403
	 * FORBIDDEN will be sent if everything is right 200 OK will be sent
	 * 
	 * @param user
	 * @return OK or FORBIDDEN or NOT_FOUND
	 */
	@PostMapping(path = "/login")
	public ResponseEntity<DolapUser> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
		try {
			// Get the user from DB
			UserDetails user = userService.loadUserByUsername(userLoginRequest.getEmail());
			// If user found let's check is password correct
			if (userService.isPasswordCorrect(userLoginRequest.getPassword(), user.getPassword())) {
				// Password is correct, let user GO
				return new ResponseEntity<>(HttpStatus.OK);
			}
			// Password is incorrect, return FORBIDDEN
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (UsernameNotFoundException e) {
			// User with mail couldn't found
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}