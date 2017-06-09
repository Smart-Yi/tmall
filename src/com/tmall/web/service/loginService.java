package com.tmall.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tmall.dao.UserDAO;
import com.tmall.model.User;

public class loginService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws  ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name") ;
		String password = request.getParameter("password");
		System.out.println(name + password);
		User bean = new UserDAO().get(name, password);
		if (bean == null){
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			System.out.println("用户名密码错误");
			out.print("用户名密码错误?");
			out.flush();
			out.close();
		}else{
			
			HttpSession session = request.getSession();
			session.setAttribute("user", bean);
			session.setAttribute("username", bean.getUsername());
//			Cookie cookie = new Cookie("user", name);
//			cookie.setPath("/");
//			cookie.setMaxAge(30*60*2);
//			response.addCookie(cookie);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print("true");
			out.flush();
			out.close();
		}
	}
	@Override
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
}
