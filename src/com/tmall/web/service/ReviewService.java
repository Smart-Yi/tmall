package com.tmall.web.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.model.Order;
import com.tmall.model.Product;


public class ReviewService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		int oid = Integer.parseInt(request.getParameter("oid")) ;
		int pid = Integer.parseInt(request.getParameter("pid")) ;
		Order o = new OrderDAO().get(oid);
		Product p = new ProductDAO().get(pid);
		request.setAttribute("o", o);
		request.setAttribute("p", p);
		System.out.println(p.getReviewCount());
		request.getRequestDispatcher("/review.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		doGet(request, response);}
}
