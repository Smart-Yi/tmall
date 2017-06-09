package com.tmall.web.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.model.Order;

public class ConfirmPay extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = new OrderDAO().get(oid);
		o.setConfirmDate(new Date());
		o.setStatus("done");
		new OrderDAO().update(o);
		
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);}
}
