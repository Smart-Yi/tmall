package com.tmall.web.service.order;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import com.tmall.dao.OrderDAO;
import com.tmall.dao.OrderItemDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.UserDAO;
import com.tmall.model.Order;
import com.tmall.model.OrderItem;
import com.tmall.model.Product;
import com.tmall.model.User;

public class OrderDeal {

	public Order create(HttpServletRequest request,String username) {
		String address = request.getParameter("address");
		String receiver= request.getParameter("receiver");
		String mobile  = request.getParameter("mobile");
		String post    = request.getParameter("post");
		String userMessage   = request.getParameter("userMessage");
		String orderCode=createOrderCode();
		User  user   = new UserDAO().get(username);
		Float total = 0.00F ;
		Product pbean = new ProductDAO().get(Integer.parseInt(request.getParameter("pid")));
		total = total + pbean.getPromotePrice();

/////////////////////////////////////////////////////////////////////////////////////////////////////
	Order  order  =  new Order();

	order.setAddress(address);
	order.setReceiver(receiver);
	order.setMobile(mobile);
	order.setPost(post);
	order.setOrderCode(orderCode);
	order.setCreateDate(new Date());
	order.setUser(user);
	order.setUserMessage(userMessage);
	order.setTotal(total);
	order.setStatus("waitPay");
	new OrderDAO().add(order);
/////////////////////////////////////////////////////////////////////////////////////////////////////
	OrderItem oi  =  new OrderItem();
	if (request.getParameter("number") == null){ 
		oi.setNumber(1);
	}	
	oi.setProduct(pbean);	
	oi.setUser(user);
	oi.setOrder(order);
	new OrderItemDAO().add(oi);
	return order;
	}
	
	String createOrderCode(){
		  Integer i = (int) Math.random()*10000 ;
		  String s = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		  return s + i.toString();
	}

}
