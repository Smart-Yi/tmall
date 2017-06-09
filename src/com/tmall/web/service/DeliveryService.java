package com.tmall.web.service;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.model.Order;

public class DeliveryService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		String orderid = request.getParameter("id");
		int  oid = Integer.parseInt(orderid);
		Order obean = new OrderDAO().get(oid);
		obean.setStatus("waitConfirm");
		obean.setDeliveryDate(new Date());
		System.out.println("waitconfirm");
		new OrderDAO().update(obean);
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);}
}
