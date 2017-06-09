package com.tmall.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.dao.OrderItemDAO;
import com.tmall.model.Order;

public class AliPayService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		String orderid = request.getParameter("orderid");
		int  oid = Integer.parseInt(orderid);
		float total = new OrderItemDAO().getTotalByOrder(oid);
		Order obean = new OrderDAO().get(oid);
		obean.setTotal(total);
		request.setAttribute("order", obean);
		request.getRequestDispatcher("/alipay.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);}

}
