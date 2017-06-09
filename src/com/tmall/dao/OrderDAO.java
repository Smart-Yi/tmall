package com.tmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmall.model.Order;
import com.tmall.util.DBUtil;
import com.tmall.util.DateUtil;

public class OrderDAO {
	/*
	 * 
	 * ----增------  
	 * 
	 */
	public void add(Order bean){
		String sql = "insert into Order_ values(null , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ?)";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bean.getOrderCode());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getPost());
			pstmt.setString(4, bean.getReceiver());
			pstmt.setString(5,bean.getMobile());
			pstmt.setString(6, bean.getUserMessage());
			System.out.println(bean.getPayDate());
			//pstmt.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
			pstmt.setString(7, DateUtil.d2s(bean.getCreateDate()));
			pstmt.setString(8, DateUtil.d2s(bean.getPayDate()));
			pstmt.setString(9, DateUtil.d2s(bean.getDeliveryDate()));
			pstmt.setString(10, DateUtil.d2s(bean.getConfirmDate()));
			pstmt.setInt(11,bean.getUser().getId());
			System.out.println(bean.getUser().getId());
			pstmt.setString(12, bean.getStatus());
			pstmt.execute();
			ResultSet rs =  pstmt.getGeneratedKeys(); 
			///获取Category主键
			if(rs.next()){
				bean.setId(rs.getInt(1));
				System.out.println(rs.getInt(1));
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
		String sql = "delete from Order_ where id = "+ id ;
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
	public void update(Order bean){
		String sql ="update Order_ set orderCode = ? , address = ?,post = ? ,receiver = ? ,mobile = ? ,userMessage = ? ,createDate = ? ,payDate = ? ,deliveryDate = ? ,confirmDate =? ,uid = ? ,status = ?  where id = ?";
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, bean.getOrderCode());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getPost());
			pstmt.setString(4, bean.getReceiver());
			pstmt.setString(5,bean.getMobile());
			pstmt.setString(6, bean.getUserMessage());
			pstmt.setString(7, DateUtil.d2s(bean.getCreateDate()));
			pstmt.setString(8, DateUtil.d2s(bean.getPayDate()));
			pstmt.setString(9, DateUtil.d2s(bean.getDeliveryDate()));
			pstmt.setString(10, DateUtil.d2s(bean.getConfirmDate()));
			System.out.println("uid" + bean.getUser().getId());
			pstmt.setInt(11,bean.getUser().getId());
			System.out.println("orderStatus:" + bean.getStatus());
			pstmt.setString(12, bean.getStatus());
			System.out.println("oid:" + bean.getId());
			pstmt.setInt(13, bean.getId());
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
	public List<Order> getByUser(int uid ){
		String sql = "select * from order_ where uid = ?" ;
		Order bean = null ;
		List<Order>  obeans = new ArrayList<Order>() ; 
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				bean = new Order() ;
				bean.setId(rs.getInt("id"));
				bean.setOrderCode(rs.getString("orderCode"));
				bean.setAddress(rs.getString("address"));
				bean.setPost(rs.getString("post"));
				bean.setReceiver(rs.getString("receiver"));
				bean.setMobile(rs.getString("mobile"));
				bean.setUserMessage(rs.getString("userMessage"));
				bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				bean.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				bean.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				bean.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				bean.setUser(new UserDAO().get(uid));
				bean.setStatus(rs.getString("status"));
				bean.setOrderItems(new OrderItemDAO().getByOrder(rs.getInt("id")));
				bean.setTotal(new OrderItemDAO().getTotalByOrder(rs.getInt("id")));
				bean.setTotalNumber(new OrderItemDAO().getTotalNumberByOrder(rs.getInt("id")));
				bean.setTotalNumber(new OrderItemDAO().getTotalNumberByOrder(rs.getInt("id")));
				bean.setTotal(new OrderItemDAO().getTotalByOrder(rs.getInt("id")));
				obeans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return obeans ;
	}
	
	
	public Order get(int id ){
		String sql = "select * from order_ where id = ?" ;
		Order bean = null ;
		try (Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				bean = new Order() ;
				bean.setId(rs.getInt("id"));
				bean.setOrderCode(rs.getString("orderCode"));
				bean.setAddress(rs.getString("address"));
				bean.setPost(rs.getString("post"));
				bean.setReceiver(rs.getString("receiver"));
				bean.setMobile(rs.getString("mobile"));
				bean.setUserMessage(rs.getString("userMessage"));
				bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				bean.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				bean.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				bean.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
				bean.setStatus(rs.getString("status"));
				bean.setOrderItems(new OrderItemDAO().getByOrder(id));
				bean.setTotalNumber(new OrderItemDAO().getTotalNumberByOrder(rs.getInt("id")));
				bean.setTotal(new OrderItemDAO().getTotalByOrder(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			return bean ;
	}
	//分页
	public List<Order> list(int start , int count){
		String sql = "select * from Order_ order by id desc limit ? , ?";
		List<Order> beans = new ArrayList<Order>();
		try(Connection conn = DBUtil.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			ResultSet rs = pstmt.executeQuery();
			
			Order bean = null ;
			while(rs.next()){
				bean = new Order();
				bean.setId(rs.getInt("id"));
				bean.setOrderCode(rs.getString("orderCode"));
				bean.setAddress(rs.getString("address"));
				bean.setPost(rs.getString("post"));
				bean.setReceiver(rs.getString("receiver"));
				bean.setMobile(rs.getString("mobile"));
				bean.setUserMessage(rs.getString("userMessage"));
				bean.setCreateDate(DateUtil.t2d(rs.getTimestamp("createDate")));
				bean.setPayDate(DateUtil.t2d(rs.getTimestamp("payDate")));
				bean.setDeliveryDate(DateUtil.t2d(rs.getTimestamp("deliveryDate")));
				bean.setConfirmDate(DateUtil.t2d(rs.getTimestamp("confirmDate")));
				bean.setUser(new UserDAO().get(rs.getInt("uid")));
				bean.setStatus(rs.getString("status"));
				bean.setOrderItems(new OrderItemDAO().getByOrder(rs.getInt("id")));
				bean.setTotalNumber(new OrderItemDAO().getTotalNumberByOrder(rs.getInt("id")));
				bean.setTotal(new OrderItemDAO().getTotalByOrder(rs.getInt("id")));
				beans.add(bean);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return beans ;
	}
	public List<Order> list(){
		return list(0,Short.MAX_VALUE);
	}
	public int getTotal(){
		String sql = "select count(*) from order_ " ;
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
}
