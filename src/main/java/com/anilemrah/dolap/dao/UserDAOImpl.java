package com.anilemrah.dolap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.anilemrah.dolap.entity.DolapUser;

/**
 * 
 * @author Anil Emrah
 *
 */
@Component
public class UserDAOImpl implements UserDAO {

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	public UserDAOImpl(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public DolapUser getUser(String userEmail) {
		return dynamoDBMapper.load(DolapUser.class, userEmail);
	}

	@Override
	public DolapUser registerUser(DolapUser user) {
		dynamoDBMapper.save(user);
		return user;
	}
}