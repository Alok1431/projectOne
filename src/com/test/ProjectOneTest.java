package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.model.User;

public class ProjectOneTest {

	@Test
	public void testAddUserDetail() {
		
		User user=new User();
		user.setName("y");
		user.setEmail("y@gmail.com");
		user.setMobile(956623458l);
		user.setPassword("y");
		
		UserDao userDao=new UserDaoImpl();
		String addUserDetail = userDao.addUserDetail(user);
		System.out.println(addUserDetail);
		
		assertEquals("Data Inserted Sucessfully", addUserDetail);
		
	}

	@Test
	public void testCheckLoginCredentail() {
		UserDao userDao=new UserDaoImpl();
		User user = userDao.checkLoginCredentail("x","x");
		System.out.println(user);
		assertNotNull(user);

	}

}
