package com.tmall.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.CategoryDAO;
import com.tmall.dao.OrderDAO;
import com.tmall.model.Category;
import com.tmall.model.Order;
import com.tmall.util.Page;

public class OrderService extends BaseBackServlet{
	private static final long serialVersionUID = 1L;
	public void list(HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException{
		String pageStart = request.getParameter("page.start");
		int start = 0 ;
		 if (pageStart != null ){
			 start = Integer.parseInt(pageStart);
		 }
		List<Order> listOrder = new OrderDAO().list( start , 5);

		request.setAttribute("os", listOrder);
		
		//分页
		Page p =  (Page)request.getAttribute("page");
		
		p.setTotalPage(new OrderDAO().getTotal());
		
		request.getRequestDispatcher("/admin/listOrder.jsp").forward(request, response);
	}
	public void edit(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Order o  = new OrderDAO().get(id);
		request.setAttribute("c", o);
		request.getRequestDispatcher("./admin/editCategory.jsp").forward(request, response);
	}
}
