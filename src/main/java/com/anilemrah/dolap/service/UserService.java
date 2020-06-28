package com.anilemrah.dolap.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.anilemrah.dolap.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto registerUser(UserDto userDto);

	boolean isPasswordCorrect(String rawPassword, String encryptedPassword);
}