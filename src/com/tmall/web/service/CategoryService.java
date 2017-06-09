package com.tmall.web.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.CategoryDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.model.Category;
import com.tmall.model.Product;

public class CategoryService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int  cid = Integer.parseInt(request.getParameter("cid")) ;
		List<Product> pbeans = new ProductDAO().getList(cid);
	    Category cbean = new CategoryDAO().get(cid)	;				
		request.setAttribute("cbean", cbean);
		request.setAttribute("pbeans", pbeans);
		request.getRequestDispatcher("/category.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
