package com.kuke.web.user.dao;

import java.util.List;

import com.kuke.web.user.model.User;

public interface UserDao {
	public List<User> findUser();
	public void saveUser(User us);
}
