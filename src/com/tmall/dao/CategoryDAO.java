package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tmall.model.Category;
import com.tmall.util.DBUtil;

public class CategoryDAO {
	//获取category数量
	public int getTotal(){
		String sql = "select count(*) from category " ;
		int total = 0 ;
		try{
			Statement stmt = DBUtil.getConnection().createStatement();
			ResultSet	rs = stmt.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total ;
	}
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(Category bean){
	String sql = "insert into Category values(null , ? )";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bean.getName());
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
		String sql = "delete from category where id = " + id  ;
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
	public void update(Category bean){
		String sql = "update category set name = ? where id = ?" ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getId());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * ----查------  
	 * 
	 */
	public Category get(int id ){
	String sql = "select * from Category where id = " +id ;	
	Category bean = null ;
	try (Connection conn = DBUtil.getConnection();Statement stmt = conn.createStatement();){
		ResultSet rs = stmt.executeQuery(sql);
		bean = new Category();
		while (rs.next()){
		bean.setName(rs.getString(2));
		}
		bean.setId(id);
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		return bean ;
	}
	/*
	 * 
	 * ----获得全部的category------  
	 * ----查询所有----------------
	 */
	public List<Category> list(){
		return list(0,Short.MAX_VALUE);
	}
	/*
	 * 
	 * ----分页查询------  
	 * 
	 */
	public List<Category> list(int start ,int count ){
		String sql = "select * from Category order by id desc limit ? ,? " ;
		List<Category> beans = new ArrayList<Category>() ;
		Category bean = null ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new Category() ;
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans ;
	}
}
