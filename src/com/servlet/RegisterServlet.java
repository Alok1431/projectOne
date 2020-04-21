package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao=new UserDaoImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("psw");
		String email = request.getParameter("email");
		Long mobile = Long.parseLong(request.getParameter("mobile"));

		//print username ,password,email,mobile
		
		User user=new User();
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setMobile(mobile);
		
		//print user
		
		String addUserDetail = userDao.addUserDetail(user);
		System.out.println("inside register servlet");
		System.out.println(addUserDetail);
		
		if(addUserDetail!=null) {
			request.setAttribute("message", "Registered Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("/register-success.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("/register-error.jsp");
			rd.forward(request, response);
		}
		
	}

}
