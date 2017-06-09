package com.tmall.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.UserDAO;
import com.tmall.model.User;

public class RegisterService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println(name);
		UserDAO  beanDAO = new UserDAO();
		User bean =  beanDAO.get(name);
		if (bean.getUsername() != null){
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("用户名已经存在");
		}else{
			User newbean = new User();
			newbean.setUsername(name);
			newbean.setPassword(password);
			beanDAO.add(newbean);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("注册成功");
		}
		
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException{
		doGet(request,response);
	}
	
}
