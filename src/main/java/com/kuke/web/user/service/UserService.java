package com.kuke.web.user.service;

import java.util.List;

import com.kuke.web.user.model.User;

public interface UserService {
	public List<User> findUser();
	public String saveUsers(String fileName);
}
