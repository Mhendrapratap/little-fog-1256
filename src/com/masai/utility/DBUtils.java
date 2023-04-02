package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	public static Connection getConnectionToDatabase(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
			conn =  DriverManager.getConnection(rb.getString("url"), rb.getString("username"),rb.getString("password"));
		}
		catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (conn == null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}
	
	public static void closeConnection(Connection con) throws SQLException{
		if(con != null) {
			con.close();
		}
	}
	static boolean isResultSetEmpty(ResultSet rs) throws SQLException{
		if(!rs.isBeforeFirst() && rs.getRow() == 0) {
			return true;
		}
		return false;
	}
}
