package com.tmall.web.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ProductImageDAO;
import com.tmall.model.Product;

public class SearchService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("keyword");
		name = "%" + name + "%" ;
		List<Product> pbeans = new ProductDAO().get(name);
		for (Product product : pbeans) {
			product.setFirstProductImage(new ProductImageDAO().getBypid(product.getId(), "type_single").get(0));
		}
		request.setAttribute("pbeans", pbeans);
		request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
		
	}
	@Override
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		doGet(request , response);
	}
}
