package com.tmall.web.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.OrderDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ReviewDAO;
import com.tmall.dao.UserDAO;
import com.tmall.model.Order;
import com.tmall.model.Review;
import com.tmall.model.User;


public class DoReviewService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		int oid = Integer.parseInt(request.getParameter("oid")); 
		int pid = Integer.parseInt(request.getParameter("pid")); 
		String  content = request.getParameter("content");
		if (content.equals("")){ content ="该用户没有写评论";}
		Order o = new OrderDAO().get(oid);
		o.setStatus("done");
		new OrderDAO().update(o);
		Review r = new Review();
		r.setContent(content);
		r.setCreateDate(new Date());
		r.setProduct(new ProductDAO().get(pid));
		String name = null  ;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("user")){
				name = cookie.getValue();
			}
		}
		if (name == null ){
			request.getRequestDispatcher("/tmall").forward(request, response);
		}
		User u = new UserDAO().get(name) ;
		if (u == null ){
			request.getRequestDispatcher("/tmall").forward(request, response);
		}
		r.setUser(u);
		new ReviewDAO().add(r);
		List<Review> rlist = new ReviewDAO().getTotal(pid);
		request.setAttribute("reviews", rlist);;
		String url = "/review?oid=" + oid + "&pid=" + pid +"&showonly=true";
		request.getRequestDispatcher(url).forward(request, response);
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);}
}
