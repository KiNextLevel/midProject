package model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	static final String driverName = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/test";
	static final String userName = "root";
	static final String password = "1234";
	
	public static Connection connect() {
	    Connection conn = null;
	    try {
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url,userName,password);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return conn;
	}

	public static void disconnect(Connection conn, PreparedStatement pstmt) {
		try {
			// pstmt가 null이 아닌 경우에만 close() 호출
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        // conn이 null이 아닌 경우에만 close() 호출
	        if (conn != null) {
	            conn.close();
	        }
        } catch(SQLException e) {
            e.printStackTrace();
        }
	}
}
