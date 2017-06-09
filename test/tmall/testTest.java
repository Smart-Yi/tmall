package tmall;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.tmall.dao.CategoryDAO;
import com.tmall.dao.OrderDAO;
import com.tmall.dao.ProductDAO;
import com.tmall.dao.ReviewDAO;
import com.tmall.model.Category;
import com.tmall.model.Order;
import com.tmall.model.Product;
import com.tmall.model.Review;
import com.tmall.model.User;
import com.tmall.util.DBUtil;


public class testTest {
	@Test
	public void test7(){
		String s = StringUtils.substringBetween("/admin_category_list", "_" );
		System.out.println(s);
	}
	
	public void test6(){
		String s = new SimpleDateFormat().format("");
		System.out.println(s);
	}
	public void test5(){
		String s1 =  "你好";
		String s2 = "你好";
		System.out.println(s1 == s2 );
		System.out.println(s1.equals(s2));
	}
	public void test4(){
		
		for (Object iterable_element : list()) {
			System.out.println(iterable_element);
		}
	}
	public List<Object> list(){
		System.out.println("---");
		List<Object> l  = new ArrayList<Object>();
		l.add("2");
		l.add("2");
		l.add("2");
		l.add("2");
		l.add("2");
		return l;
	}
	public void test2() {
		CategoryDAO cdao =new CategoryDAO();
		List<Category> cbeans = cdao.list();
		ProductDAO pdao =  new ProductDAO();
		int number = 0 ;
		int index = 0;
		for (Category category : cbeans) {
			category.setProduct(pdao.getList(category.getId()));
			List<Product> pbeans =new ArrayList<Product>();
			for (Product product : category.getProduct()) {
				
							  pbeans.add(product);
							  System.out.println(index);
				if (number == 4){
					List<List<Product>> pbeanss =new ArrayList<List<Product>>();
					pbeanss.add(pbeans);
					category.setProductByRow(pbeanss);
					
					number = 0 ;
				}
				number ++ ;
				index ++ ;
			}
			number = 0 ;
		}
		index = 0;
		for (Category category : cbeans) {
			for (List<Product> lp : category.getProductByRow()) {
				for (Product product : lp) {
					System.out.println(product.getSubTitle());
					System.out.println(index);
					index++;
				}
			}
		}
	}

	public void test3(){
		Product p  =new ProductDAO().get(87);
		
		System.out.println(p.getFirstProductImage().getId());
	}
	
	
	
	
	
	
	
	
	
	
	
	public void test() {
		String sql = "select * from user where id =1"  ;	
		try (Connection conn = DBUtil.getConnection();Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}	
		}
}


