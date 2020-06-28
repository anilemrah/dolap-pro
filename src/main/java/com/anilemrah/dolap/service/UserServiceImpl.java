package com.anilemrah.dolap.service;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anilemrah.dolap.dao.UserDAO;
import com.anilemrah.dolap.dto.UserDto;
import com.anilemrah.dolap.entity.DolapUser;
import com.anilemrah.dolap.exceptions.user.DolapUserException;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Users can regiter with that method
	 */
	@Override
	public UserDto registerUser(UserDto userDto) throws DolapUserException {

		if (userDAO.getUser(userDto.getEmail()) != null) {
			throw new DolapUserException("Email is already in use!");
		}

		DolapUser user = new DolapUser();
		BeanUtils.copyProperties(userDto, user);

		user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		DolapUser registeredUser = userDAO.registerUser(user);

		UserDto resultUserDto = new UserDto();
		BeanUtils.copyProperties(registeredUser, resultUserDto);

		return resultUserDto;
	}

	@Override
	public boolean isPasswordCorrect(String rawPassword, String encryptedPassword) {

		return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		DolapUser user = userDAO.getUser(email);

		if (user == null) {
			throw new UsernameNotFoundException("User couldn't find");
		}
		return new User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
	}
}