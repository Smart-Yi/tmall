package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.model.User;
import com.tmall.util.DBUtil;

public class UserDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(User bean){
		String sql = "insert into user values(null , ? , ?)";
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bean.getUsername());
			pstmt.setString(2, bean.getPassword());
			pstmt.executeUpdate();
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
		String sql = "delete from user where id = " + id  ;
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
	public void update(User bean){
		String sql = "update category set name = ? where id = ?" ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bean.getUsername());
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
	public User get(int id ){
	String sql = "select * from User where id = " +id ;	
	User bean = null ;
	try (Connection conn = DBUtil.getConnection();Statement stmt = conn.createStatement();){
		ResultSet rs = stmt.executeQuery(sql);
		bean = new User();
		if (rs.next()){
			bean.setUsername(rs.getString(2));
			bean.setPassword(rs.getString(3));
		}
		bean.setId(id);
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		return bean ;
	}
	public User get(String name ){
		String sql = "select * from User where name = ?"  ;	
		User bean =null ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			bean = new User();
			if (rs.next()){
				bean.setId(rs.getInt(1));
				bean.setUsername(rs.getString(2));
				bean.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return bean ;
	}
	public boolean isExist(String name){
		String sql = "select * from User where name = ?" ;	
		boolean b = false ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery(sql);
			if (rs.next()){
				b = true ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return b ; 
	}
	public User get(String name ,String password ){
		String sql = "select * from User where name = ? and password = ?";
		User bean = null ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,name);
			pstmt.setString(2,password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				bean = new User() ;
				bean.setId(rs.getInt("id"));
				bean.setUsername(name);
				bean.setPassword(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean ;
	}
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from User";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();
  
        String sql = "select * from User order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                User bean = new User();
                int id = rs.getInt(1);
 
                String name = rs.getString("name");
                bean.setUsername(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                 
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
	
}
