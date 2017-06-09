package com.tmall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static String url 		=	"jdbc:mysql://localhost:3306/tmall";
	static String user		=	"root" ;
	static String password  =	"root";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn =null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			return conn ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn ;
	}
	
	
}
