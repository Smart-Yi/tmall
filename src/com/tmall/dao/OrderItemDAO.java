package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tmall.model.OrderItem;
import com.tmall.util.DBUtil;

public class OrderItemDAO {
	public void add(OrderItem bean){
		String sql = "insert  into  OrderItem  values (null , ? , ? , ? , ?)";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bean.getProduct().getId());
			pstmt.setInt(2, bean.getOrder().getId());
			pstmt.setInt(3, bean.getUser().getId());
			pstmt.setInt(4, bean.getNumber());
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				bean.setId(rs.getInt(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void delete(int id){
		String sql ="delete from OrderItem where id = ? ";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void update(OrderItem  bean){
		String sql = "update OrderItem set pid = ? , oid = ? , uid =? , number = ? where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bean.getProduct().getId());
			pstmt.setInt(2, bean.getOrder().getId());
			pstmt.setInt(3, bean.getUser().getId());
			pstmt.setInt(4, bean.getNumber());
			pstmt.setInt(5, bean.getId());
			pstmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public OrderItem getById(int id){
		String sql = "select * from OrderItem where id = ?" ;
		OrderItem bean = null ;
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new OrderItem();
				bean.setId(id);
				bean.setNumber(rs.getInt("number"));
				//bean.setOrder(new OrderDAO().get(rs.getInt("oid")));
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean ;
	}
	public List<OrderItem> getByOrder(int oid){
		String sql = "select * from OrderItem where oid = ?" ;
		OrderItem bean = null ;
		List<OrderItem> oibeans = new ArrayList<OrderItem>();
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, oid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new OrderItem();
				bean.setId(rs.getInt("id"));
				bean.setNumber(rs.getInt("number"));
				//bean.setOrder(new OrderDAO().get(oid));
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
				
				oibeans.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return oibeans ;
	}
	public OrderItem getByNumber(int number){
		String sql = "select * from OrderItem where number = ?" ;
		OrderItem bean = null ;
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, number);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new OrderItem();
				bean.setId(rs.getInt("id"));
				bean.setNumber(number);
				//bean.setOrder(new OrderDAO().get(rs.getInt("oid")));
				bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bean ;
	}
	//获取订单总额
	public float getTotalByOrder(int oid){
		String sql = "select * from OrderItem where oid = ?";
		float total = 0 ;
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, oid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				total = total + new ProductDAO().get(rs.getInt("pid")).getPromotePrice();
			}
		}catch(SQLException e){e.printStackTrace();}
		
		
		return total;
	}
	//获取订单数量
	public int getTotalNumberByOrder(int oid){
		String sql = "select * from OrderItem where oid = ?";
		int totalNumber = 0 ;
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, oid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				totalNumber = totalNumber + rs.getInt("Number");
			}
		}catch(SQLException e){e.printStackTrace();}
		
		
		return totalNumber;
	}
}

