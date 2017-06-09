package com.tmall.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.UserDAO;
import com.tmall.model.User;
import com.tmall.util.Page;

public class UserService extends BaseBackServlet {
	private static final long serialVersionUID = 1L;
	public void list(HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException{
		String pageStart = request.getParameter("page.start");
		int start = 0 ;
		 if (pageStart != null ){
			 start = Integer.parseInt(pageStart);
		 }
		List<User> listUser = new UserDAO().list( start , 5);
		request.setAttribute("us", listUser);
		
		//分页
		Page p =  (Page)request.getAttribute("page");
		
		p.setTotalPage(new UserDAO().getTotal());
		
		
		request.getRequestDispatcher("/admin/listUser.jsp").forward(request, response);
	}
}
