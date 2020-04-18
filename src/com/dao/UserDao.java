package com.dao;

import java.util.ArrayList;

import com.model.User;

public interface UserDao {

	public String addUserDetail(User user);
	
	public User checkLoginCredentail(String username, String password);

	public ArrayList<User> getAllUserDetails();

}
