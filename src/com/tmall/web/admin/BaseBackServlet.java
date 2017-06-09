package com.tmall.web.admin;

import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.util.Page;

public class BaseBackServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res){
		HttpServletRequest request =  (HttpServletRequest) req ;
		HttpServletResponse response = (HttpServletResponse) res ;
		//分页
		String pageStart = request.getParameter("page.start");
		int start = 0 ;
		 if (pageStart != null ){
			 start = Integer.parseInt(pageStart);
		 }
		 Page p = new Page() ;
		 p.setStart(start);
		 p.setCount(5);
		 request.setAttribute("page", p);
		//借助反射 调用对应方法.
		String method = (String) request.getAttribute("method") ;
		System.out.println("BaseBackServlet");
		try {
			Method m = this.getClass().getMethod(method,HttpServletRequest.class ,HttpServletResponse.class);
			m.invoke(this,request,response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
	}
}
