package com.anilemrah.dolap.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anilemrah.dolap.dto.UserDto;
import com.anilemrah.dolap.entity.DolapUser;
import com.anilemrah.dolap.model.UserLoginRequest;
import com.anilemrah.dolap.model.UserRequest;
import com.anilemrah.dolap.model.UserResponse;
import com.anilemrah.dolap.service.UserService;

/**
 * This class is responsible from the all functionalities of Users
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
	 * @return registered user
	 */
//	@PostMapping(path = "/register")
//	public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
//		// User password need to be encrypted
//		User encryptedUser = new User(user.getUserName(), new BCryptPasswordEncoder().encode(user.getUserPassword()));
//		// Check if user already exists
//		if (userService.registerUser(encryptedUser) == null) {
//			// User name already exists
//			return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}
//		// User registered successfully
//		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
//	}
	@PostMapping(path = "/register")
	public UserResponse registerUser(@RequestBody UserRequest userRequest) {
		UserResponse response = new UserResponse();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		UserDto createdUser = userService.registerUser(userDto);
		BeanUtils.copyProperties(createdUser, response);

		return response;
	}

	/**
	 * User login method If userName is not registered or password is wrong 403
	 * FORBIDDEN will be sent if everything is right 200 OK will be sent
	 * 
	 * @param user
	 * @return OK or FORBIDDEN
	 */
	@PostMapping(path = "/login")
	public ResponseEntity<DolapUser> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
		try {
			UserDetails user = userService.loadUserByUsername(userLoginRequest.getEmail());

			if (userService.loginUser(userLoginRequest.getPassword(), user.getPassword())) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}