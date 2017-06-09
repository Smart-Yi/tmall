package com.tmall.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmall.dao.CategoryDAO;
import com.tmall.model.Category;
import com.tmall.util.Page;

public class CategoryService extends BaseBackServlet{
	private static final long serialVersionUID = 1L;
	
	public void list(HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException{
		String pageStart = request.getParameter("page.start");
		int start = 0 ;
		 if (pageStart != null ){
			 start = Integer.parseInt(pageStart);
		 }
		List<Category> listCategory = new CategoryDAO().list( start , 5);
		request.setAttribute("thecs", listCategory);
		
		//分页
		Page p =  (Page)request.getAttribute("page");
		
		p.setTotalPage(new CategoryDAO().getTotal());
		
		
		request.getRequestDispatcher("/admin/listCategory.jsp").forward(request, response);
	}
	public void edit(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		int cid = Integer.parseInt(request.getParameter("id"));
		Category  cbean = new CategoryDAO().get(cid);
		request.setAttribute("c", cbean);
		request.getRequestDispatcher("./admin/editCategory.jsp").forward(request, response);
	}
	
	
}
