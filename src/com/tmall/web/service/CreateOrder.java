package com.tmall.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.model.Order;
import com.tmall.web.service.order.OrderDeal;

public class CreateOrder extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		if (request.getParameter("pid") == null){response.sendRedirect("/tmall");}
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
		System.out.println((String)request.getAttribute(username));
		if (username == null ) {
			response.sendRedirect("/tmall");
		} else {
		Order obean = new OrderDeal().create(request,username);
		request.setAttribute("order", obean);
		System.out.println("订单ID"+ obean.getId());
		System.out.println("订单价格"+ obean.getTotal());
		request.getRequestDispatcher("/alipay.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
}
