package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.model.Category;
import com.tmall.model.Property;
import com.tmall.util.DBUtil;

public class PropertyDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(Property bean){
	String sql = "insert into Property values(null , ? , ?)";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bean.getCategory().getId());
			pstmt.setString(2, bean.getName());
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
		String sql = "delete from Property where id = " + id  ;
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
	public void update(Property bean){
		String sql = "update Property set name = ? ,cid = ? where id = ?" ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getCategory().getId());
			pstmt.setInt(3, bean.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * ----查------  
	 * 
	 */
	public Property get(int cid ,String name){
	String sql = "select * from Property where cid =? and name = ? ";	
	Property bean = null ;
	try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
		pstmt.setInt(1, cid);
		pstmt.setString(2, name);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()){
		bean = new Property();
		bean.setName(rs.getString(name));
		bean.setCategory(new CategoryDAO().get(cid));
		bean.setId(rs.getInt("id"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		return bean ;
	}
	public int getTotal(int cid){
		String sql = "select count(*) from Property where cid = " +cid ;
		int total = 0 ;
		try (Connection conn = DBUtil.getConnection();Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return total ;
	}
	 public Property get(int id) {
	        Property bean = new Property();
	  
	        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
	  
	            String sql = "select * from Property where id = " + id;
	  
	            ResultSet rs = s.executeQuery(sql);
	  
	            if (rs.next()) {
	 
	                String name = rs.getString("name");
	                int cid = rs.getInt("cid");
	                bean.setName(name);
	                Category category = new CategoryDAO().get(cid);
	                bean.setCategory(category);
	                bean.setId(id);
	            }
	  
	        } catch (SQLException e) {
	  
	            e.printStackTrace();
	        }
	        return bean;
	    }
	 public List<Property> list(int cid , int start ,int count){//, int start ,int count
		 String sql = "select * from property where cid = ? order by id desc limit ? , ?" ;
		 Property bean = null ;
		 Category ca    = null ;
		 CategoryDAO  cd = new CategoryDAO();
		 List<Property> beans = new ArrayList<Property>() ;
		 try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			 pstmt.setInt(1, cid);
			 pstmt.setInt(2, start);
			 pstmt.setInt(3, count);
			 ResultSet rs = pstmt.executeQuery();
			 	while (rs.next()){
					bean = new Property();
					bean.setId(rs.getInt("id"));
					bean.setName(rs.getString("name"));
					ca =  cd.get(cid);
					bean.setCategory(ca);
					beans.add(bean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return beans ;
	 }
	 public List<Property> list(int cid){
		 return list(cid , 0 , Short.MAX_VALUE);
	 }
}
