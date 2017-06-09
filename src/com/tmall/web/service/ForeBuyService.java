package com.tmall.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tmall.dao.ProductDAO;
import com.tmall.model.Product;

public class ForeBuyService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException{
			int pid =Integer.parseInt(request.getParameter("pid"));
			 	Product pbean = new ProductDAO().get(pid);
			 	request.setAttribute("pbean", pbean);
			 request.getRequestDispatcher("/forebuy.jsp").forward(request, response);	
	}
	public void doPost(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
