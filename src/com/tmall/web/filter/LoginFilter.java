package com.tmall.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.model.User;

public class LoginFilter implements Filter{
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res;  
        User ubean = null ;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null){
//        	for (Cookie cookie : cookies) {
//				if(cookie.getName().equals("user")){
//					 ubean = new UserDAO().get(cookie.getValue());
//				}
//			}
//        }
       if( null==request.getSession().getAttribute("user")){
    	   request.getRequestDispatcher("/login.jsp").forward(request, response);  
       		return ;
       }
       ubean = (User)request.getSession().getAttribute("user");
//        if (ubean == null){
//        	request.getRequestDispatcher("/login.jsp").forward(request, response);  
//        	return ;
//        }
        request.setAttribute("user", ubean);
        chain.doFilter(request, response); 
      //  request.getRequestDispatcher("/tmall").forward(request, response);  
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter 启动");
	}


	
}
