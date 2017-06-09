package com.tmall.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.PropertyDAO;
import com.tmall.dao.PropertyValueDAO;
import com.tmall.dao.ReviewDAO;
import com.tmall.model.Product;
import com.tmall.model.Property;
import com.tmall.model.PropertyValue;
import com.tmall.model.Review;

public class ProductService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException  {
		request.setCharacterEncoding("utf-8");
		System.out.println("ProductService");
		
		int pid = Integer.parseInt(request.getParameter("pid")) ;
		ReviewDAO        rDAO =new ReviewDAO();
		ProductDAO       pDAO =new ProductDAO();
		PropertyDAO      peDAO=new PropertyDAO();
		PropertyValueDAO pvDAO=new PropertyValueDAO();
		
		List<Review> rbeans = rDAO.getTotal(pid);//评论
		
		Product      pbean  = pDAO.get(pid);//product
		 
		List<Property> pebeans = peDAO.list(pbean.getCategory().getId());
		
		List<PropertyValue> pvbeans = new ArrayList<PropertyValue>();
		
		for (Property property : pebeans) {
			pvbeans.add(pvDAO.get(pid, property.getId())); 
		}
		
		request.setAttribute("pbean", pbean);//单个产品
		request.setAttribute("rbeans", rbeans);//评论列表
		request.setAttribute("pvbeans", pvbeans);//属性列表
		request.setAttribute("reviewTotal",rbeans.size());//评论总数
		
		
		
		request.getRequestDispatcher("/product.jsp").forward(request, response);
	}
	
	
	@Override
	public void doPost(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException  {
		doGet(request,response);
	}
}

