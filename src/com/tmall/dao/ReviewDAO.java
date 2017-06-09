package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tmall.model.Review;
import com.tmall.util.DBUtil;
import com.tmall.util.DateUtil;

public class ReviewDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(Review bean){
		String sql = "insert into Review values(null , ? , ? , ? ,?)";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bean.getContent());
			pstmt.setInt(2, bean.getUser().getId());
			pstmt.setInt(3, bean.getProduct().getId());
			pstmt.setString(4, DateUtil.d2s(bean.getCreateDate()));
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
		String sql = "delete from Review where id = " + id  ;
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
	public void update(Review bean){
		String sql = "update review set  content = ?, uid =? ,pid=?, createDate = ?  where id = ?"  ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bean.getContent());
			pstmt.setInt(2, bean.getUser().getId());
			pstmt.setInt(3, bean.getProduct().getId());
			pstmt.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));
			pstmt.setInt(5, bean.getId());
				//pstmt.setDate(7, DateUtil.t2d(bean.getCreateDate()));
					/*
					 * java.sql.Date 为过时方法
					 * java.util.Date 转 java.sql.Timestamp
					 * ----------到数据库---------------------
					 * */
				//pstmt.setDate(7, new java.sql.Date(date).getCreateDate());
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
	public List<Review> get(int pid,int start,int count){
		String sql = "select * from Review  where pid = ? Order By createDate desc limit ? , ?" ;
		Review bean = null ;
		List<Review> beans = new ArrayList<Review>();
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, pid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, count);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new Review();
			bean.setContent(rs.getString("content"));
			bean.setUser(new UserDAO().get(rs.getInt("uid")));
			bean.setProduct(new ProductDAO().get(pid));
			bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
			bean.setId(rs.getInt("id"));
			beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return beans ;
	}
	public List<Review> getTotal(int pid){
		return get(pid,0,Short.MAX_VALUE);
	}
	public int getCountByProduct(int pid){
		String sql = "select count(*) from review where pid = ?";
		int re = 0 ;
		try(Connection conn =DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, pid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				re = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return re ;
	}
}	
