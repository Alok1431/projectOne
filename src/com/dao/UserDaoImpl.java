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
    public static ArrayList<User> userList;
    
	public Connection getDbConnection() {
		Connection connection = null;
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find the Driver");
		}
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Unable to Connect with DB");
		}
		return connection;

	}

	@Override
	public String addUserDetail(User user) {
		Connection connection = getDbConnection();
		if (connection != null) {
			String sql = "insert into user(name,password,email,mobile) values(?,?,?,?)";
			try {
				PreparedStatement psmt = connection.prepareStatement(sql);
				psmt.setString(1, user.getName());
				psmt.setString(2, user.getPassword());
				psmt.setString(3, user.getEmail());
				psmt.setLong(4, user.getMobile());
				int noOfRowsAffected = psmt.executeUpdate();
				if (noOfRowsAffected > 0) {
					return "Data Inserted Sucessfully";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
	public ArrayList<User> getAllUserDetails() {
		Connection connection=getDbConnection();
		String sql="select * from user";
		try {
			PreparedStatement psmt=connection.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				User user=new User();
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
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userList;
	}

	@Override
	public User checkLoginCredentail(String username,String password) {
		Connection connection=getDbConnection();
		User user =null;
		String sql="select * from user where name=? and password=?";
		try {
			PreparedStatement psmt=connection.prepareStatement(sql);
			psmt.setString(1,username);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();
			while( rs.next()) {
				user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getLong("mobile"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}

}
