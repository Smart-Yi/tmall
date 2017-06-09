package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.tmall.model.PropertyValue;
import com.tmall.util.DBUtil;

public class PropertyValueDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(PropertyValue bean){
	String sql = "insert into PropertyValue values(null , ? , ? , ? )";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bean.getProduct().getId());
			pstmt.setInt(2, bean.getProperty().getId());
			pstmt.setString(3, bean.getValue());
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
		String sql = "delete from PropertyValue where id = " + id  ;
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
	public void update(PropertyValue bean) {
		String sql = "update PropertyValue set pid = ? ,ptid = ? ,value = ? where id = ?" ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bean.getProduct().getId());
			pstmt.setInt(2, bean.getProperty().getId());
			pstmt.setString(3, bean.getValue());
			pstmt.setInt(4, bean.getId());
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
	public  PropertyValue get(int id){
		String sql = "select * from PropertyValue where id = ? ";	
		PropertyValue bean = null ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new PropertyValue();
			bean.setProperty(new PropertyDAO().get(rs.getInt("ptid")));
			bean.setValue(rs.getString("value"));
			bean.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return bean ;
	}
	public  PropertyValue get(int pid,int ptid){
		String sql = "select * from PropertyValue where pid = ? and ptid = ?";	
		PropertyValue bean = null ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, pid);
			pstmt.setInt(2, ptid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new PropertyValue();
			bean.setProperty(new PropertyDAO().get(ptid));
			bean.setValue(rs.getString("value"));
			bean.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return bean ;
	}
	
	
	
	
	
}
