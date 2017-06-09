package com.tmall.web.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.model.Order;

public class PayedService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		int  oid = Integer.parseInt(request.getParameter("oid")) ;
		System.out.println("payed 订单 ID" + oid);
		
		
		Order obean =  new OrderDAO().get(oid);
		obean.setPayDate(new Date());
		obean.setStatus("waitDelivery");
		new OrderDAO().update(obean);
		obean = new OrderDAO().get(oid);
		request.setAttribute("o", obean);
		request.getRequestDispatcher("/payed.jsp").forward(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		doGet(request ,response );
	}
}
