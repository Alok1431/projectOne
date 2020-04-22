package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.User;

public class UserDaoImpl implements UserDao {

	public static String url = "jdbc:mysql://localhost:3306/cognizant";
	public static String driverName = "com.mysql.cj.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	

	public Connection getDbConnection() {
		Connection connection = null;
		// Step 0 : load Driver
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find the Driver");
		}

		try {
			// Step 1 : Get Connection Object using DriverManager
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Unable to Connect with DB" + e);
		}
		return connection;

	}
	
	@Override
	public boolean updateUserById(User user) {
		Connection connection=getDbConnection();
		String sql="update user set name=?,password=?,email=?,mobile=? where id=?";
		try {
			PreparedStatement psmt=connection.prepareStatement(sql);
			psmt.setString(1, user.getName());
			psmt.setString(2,user.getPassword());
			psmt.setString(3, user.getEmail());
			psmt.setLong(4, user.getMobile());
			psmt.setInt(5,user.getId());
						
			int executeUpdate = psmt.executeUpdate();
			if(executeUpdate>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	
	@Override
	public User getUserById(Integer userId) {
        User user=null; 
        Connection connection=getDbConnection();
        
        try {
        	//step 3
			PreparedStatement psmt=connection.prepareStatement("select * from user where id=?");
			psmt.setInt(1, userId);
			//step 4
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getLong("mobile"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//step 5
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
		return user;
	}
	

	@Override
	public ArrayList<User> getAllUserDetails() {
		ArrayList<User> userList=new ArrayList<User>();
		Connection connection = getDbConnection();
		//String sql = "select * from user";
		try {
			// Step 2 : Get Statement Object using connection
			PreparedStatement psmt = connection.prepareStatement("select * from user");
			// Step 3 : Execute the Query
			ResultSet rs = psmt.executeQuery();
			// Step 4 : Process the ResultSet and print the values
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getLong("mobile"));
				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        System.out.println("--> " + userList);
		return userList;
	}

	@Override
	public String addUserDetail(User user) {
		System.out.println("inside addUserdetail");
		System.out.println(user);

		Connection connection = getDbConnection();

		System.out.println(connection);

		if (connection != null) {
			String sql = "insert into user(name,password,email,mobile) values(?,?,?,?)";
			try {
				PreparedStatement psmt = connection.prepareStatement(sql);
				psmt.setString(1, user.getName());
				psmt.setString(2, user.getPassword());
				psmt.setString(3, user.getEmail());
				psmt.setLong(4, user.getMobile());
				int noOfRowsAffected = psmt.executeUpdate();
				System.out.println(noOfRowsAffected);

				if (noOfRowsAffected > 0) {
					System.out.println("Data Inserted Sucessfully\";");
					return "Data Inserted Sucessfully";

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return null;
	}

	@Override
	public User checkLoginCredentail(String username, String password) {
		System.out.println("inside checklogincree");
		System.out.println(username);
		System.out.println(password);

		Connection connection = getDbConnection();
		// check connection object ->it should not be null
		System.out.println(connection);// if it prints null we hv to check getDBconnection() code

		User user = null;
		String sql = "select * from user where name=? and password=?";
		try {
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getLong("mobile"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean deleteUserByid(Integer userId) {
		Connection connection=getDbConnection();
		String sql="delete from user where id=?";
		
		try {
			PreparedStatement psmt=connection.prepareStatement(sql);
			psmt.setInt(1, userId);
			int executeUpdate = psmt.executeUpdate();
			if(executeUpdate>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    return false;
	}

	
	

}
