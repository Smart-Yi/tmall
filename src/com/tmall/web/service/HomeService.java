package com.tmall.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tmall.dao.CategoryDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.model.Category;
import com.tmall.model.Product;

public class HomeService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
//		
//		String name = "";
//		
//		Cookie[] cookies =request.getCookies();
//		System.out.println("cookies" + cookies);
//		if (cookies != null){
//		for (Cookie cookie : cookies) {
//			System.out.println("cookieName" + cookie.getName());
//			if(cookie.getName().equals("user")){
//				name = cookie.getValue();
//				System.out.println(name);
//			}
//		}
//		}
		CategoryDAO cdao =new CategoryDAO();
		List<Category> cbeans = cdao.list();
		ProductDAO pdao =  new ProductDAO();
		int number = 0 ;
		for (Category category : cbeans) {
			category.setProduct(pdao.getList(category.getId()));
			List<Product> pbeans =new ArrayList<Product>();
			for (Product product : category.getProduct()) {
					pbeans.add(product);
				if (number == 4){
					List<List<Product>> pbeanss =new ArrayList<List<Product>>();
					pbeanss.add(pbeans);
					category.setProductByRow(pbeanss);
					number = 0 ;
				}
				number ++ ;
			}
			number = 0 ;
		}
		//List<Product> pbeans = new ProductDAO().get(name);
		//for (Product product : pbeans) {
		//	product.setFirstProductImage(new ProductImageDAO().getBypid(product.getId(), "type_single").get(0));
		//}
		request.setAttribute("cbeans", cbeans);
		//request.setAttribute("name", name);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		doGet(request , response);
	}
}
