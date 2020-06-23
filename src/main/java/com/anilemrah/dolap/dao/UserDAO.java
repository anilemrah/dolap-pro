package com.anilemrah.dolap.dao;

import com.anilemrah.dolap.entity.DolapUser;

public interface UserDAO {

	public DolapUser registerUser(DolapUser user);

	public boolean loginUser(DolapUser user);

	DolapUser getUser(String userEmail);
}