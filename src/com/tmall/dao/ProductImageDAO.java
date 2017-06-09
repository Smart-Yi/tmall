package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tmall.model.ProductImage;
import com.tmall.util.DBUtil;

public class ProductImageDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(ProductImage bean){
	String sql = "insert into ProductImage values(null , ? , ?)";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bean.getProduct().getId());
			pstmt.setString(2,bean.getType());
			pstmt.execute();
			ResultSet rs =  pstmt.getGeneratedKeys(); 
			///获取Category主键
			if(rs.next()){
				bean.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * ----删------  
	 * 
	 */
	public void delete(int id){
		String sql = "delete from ProductImage where id = " + id  ;
		try (Connection conn = DBUtil.getConnection();Statement stmt = conn.createStatement();){
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * ----改------  
	 * 
	 */
	public void update(ProductImage bean){
	}
	/*
	 * 
	 * ----查------  
	 * 
	 */
	public ProductImage get(int id){
	String sql = "select * from ProductImage where id =? ";	
	ProductImage bean = null ;
	try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()){
		bean = new ProductImage();
		bean.setType(rs.getString("type"));
		bean.setId(id);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		return bean ;
	}
	public List<ProductImage> getBypid(int pid){
	String sql = "select * from ProductImage where pid =? ";	
	List<ProductImage> beans = new ArrayList<ProductImage>(); 
	ProductImage bean = null; 
	try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
		pstmt.setInt(1, pid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()){
		bean = new ProductImage();
		bean.setType(rs.getString("type"));
		bean.setId(rs.getInt("id"));
		beans.add(bean);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		return beans ;
	}
	public List<ProductImage> getBypid(int pid,String type){
		String sql = "select * from ProductImage where pid =? and type = ?";	
		List<ProductImage> beans = new ArrayList<ProductImage>(); 
		ProductImage bean = null; 
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, pid);
			pstmt.setString(2, type);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new ProductImage();
			bean.setType(type);
			bean.setId(rs.getInt("id"));
			beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return beans ;
		}
}
