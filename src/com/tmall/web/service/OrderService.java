package com.tmall.web.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.dao.UserDAO;
import com.tmall.model.Order;
import com.tmall.model.User;


public class OrderService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		String username = null ;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("user")){
				username = cookie.getValue();
			}
		}
		if(username == null){
			 username  = (String)request.getSession().getAttribute("username");
		}
		if (username == null ) {
			response.sendRedirect("/tmall");
		} else {
		User user  = new UserDAO().get(username);
		int uid = user.getId();
		List<Order> obeans = new OrderDAO().getByUser(uid);
		request.setAttribute("obeans", obeans);
		request.getRequestDispatcher("/myOrder.jsp").forward(request, response);}
	}
	@Override
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		doGet(request , response);
	}
}
