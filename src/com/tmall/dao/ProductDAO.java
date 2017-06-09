package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tmall.model.Product;
import com.tmall.util.DBUtil;
import com.tmall.util.DateUtil;

public class ProductDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(Product bean) {
		String sql = "insert into Product values(null , ? , ? , ? , ? , ? , ? , ?)";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getSubTitle());
			pstmt.setFloat(3, bean.getOrignalPrice());
			pstmt.setFloat(4, bean.getPromotePrice());
			pstmt.setInt(5,bean.getStock());
			pstmt.setInt(6, bean.getCid());
			pstmt.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
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
		String sql = "delete from product where id = " + id  ;
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
	public void update(Product bean){
		String sql = "update Property set name = ? ,subTitle = ? orignalPrice = ? ,promotePrice =? ,stock = ?, cid = ? , createDate = ? where id = ?" ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getSubTitle());
			pstmt.setFloat(3, bean.getOrignalPrice());
			pstmt.setFloat(4, bean.getPromotePrice());
			pstmt.setInt(5,bean.getStock());
			pstmt.setInt(6, bean.getCid());
				//pstmt.setDate(7, DateUtil.t2d(bean.getCreateDate()));
					/*
					 * java.sql.Date 为过时方法
					 * java.util.Date 转 java.sql.Timestamp
					 * ----------到数据库---------------------
					 * */
				//pstmt.setDate(7, new java.sql.Date(date).getCreateDate());
			pstmt.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
			pstmt.setInt(8, bean.getId());
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
	public List<Product> getList(int cid ){
		String sql = "select * from Product where cid = ?" ;	
		Product bean = null ;
		List<Product> beans = new ArrayList<Product>();
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, cid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new Product();
			bean.setName(rs.getString("name"));
			bean.setSubTitle(rs.getString("subTitle"));
			bean.setOrignalPrice(rs.getFloat("orignalPrice"));
			bean.setPromotePrice(rs.getFloat("promotePrice"));
			bean.setStock(rs.getInt("stock"));
			bean.setCid(cid);
			bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
			bean.setCategory(new CategoryDAO().get(cid));
			bean.setId(rs.getInt("id"));
			ProductImageDAO pIDAO = new ProductImageDAO();
			bean.setProductSingleImage(pIDAO.getBypid(rs.getInt("id"), "type_single"));
			bean.setProductDetailImage(pIDAO.getBypid(rs.getInt("id"), "type_detail"));
			bean.setProductImage(pIDAO.getBypid(rs.getInt("id")));
			bean.setFirstProductImage(bean.getProductSingleImage().get(0));
			bean.setReviewCount(new ReviewDAO().getCountByProduct(rs.getInt("id")));
			beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return beans ;
	}
	public Product get(int id ){
		String sql = "select * from Product where id = ?" ;	
		Product bean = null ;
		List<Product> beans = new ArrayList<Product>();
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new Product();
			bean.setName(rs.getString("name"));
			bean.setSubTitle(rs.getString("subTitle"));
			bean.setOrignalPrice(rs.getFloat("orignalPrice"));
			bean.setPromotePrice(rs.getFloat("promotePrice"));
			bean.setStock(rs.getInt("stock"));
			bean.setCid(rs.getInt("cid"));
			bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
			bean.setCategory(new CategoryDAO().get(rs.getInt("cid")));
			bean.setId(id);
			ProductImageDAO pIDAO = new ProductImageDAO();
			bean.setProductSingleImage(pIDAO.getBypid(rs.getInt("id"), "type_single"));
			bean.setProductDetailImage(pIDAO.getBypid(rs.getInt("id"), "type_detail"));
			bean.setProductImage(pIDAO.getBypid(rs.getInt("id")));
			bean.setFirstProductImage(bean.getProductSingleImage().get(0));
			bean.setReviewCount(new ReviewDAO().getCountByProduct(rs.getInt("id")));
			beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return bean ;
	}
	//name
	public List<Product> get(String name){
		String sql = "select * from Product where name like ?" ;	
		Product bean = null ;
		List<Product> beans = new ArrayList<Product>(); 
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
			bean = new Product();
			bean.setName(rs.getString("name"));
			bean.setSubTitle(rs.getString("subTitle"));
			bean.setOrignalPrice(rs.getFloat("orignalPrice"));
			bean.setPromotePrice(rs.getFloat("promotePrice"));
			bean.setStock(rs.getInt("stock"));
			bean.setCid(rs.getInt("cid"));
			bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
			bean.setCategory(new CategoryDAO().get(rs.getInt("cid")));
			bean.setId(rs.getInt("id"));
			ProductImageDAO pIDAO = new ProductImageDAO();
			bean.setProductSingleImage(pIDAO.getBypid(rs.getInt("id"), "type_single"));
			bean.setProductDetailImage(pIDAO.getBypid(rs.getInt("id"), "type_detail"));
			bean.setProductImage(pIDAO.getBypid(rs.getInt("id")));
			bean.setFirstProductImage(bean.getProductSingleImage().get(0));
			bean.setReviewCount(new ReviewDAO().getCountByProduct(rs.getInt("id")));
			beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return beans ;
	}
	
	
	
	
	
	
	
	
	
	
	
}
