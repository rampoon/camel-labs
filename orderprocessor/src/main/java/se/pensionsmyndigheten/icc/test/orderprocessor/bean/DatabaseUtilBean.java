package se.pensionsmyndigheten.icc.test.orderprocessor.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A first very simple bean for persiting orders from messages in a database
 *
 */
public class DatabaseUtilBean {
	private final static String CONNECTION_URL="jdbc:mysql://localhost:3306/camel_labs?serverTimezone=CET";
	private final static String JDBC_USERNAME = "root";
	private final static String JDBC_PASSWORD = "root";
	
	public void save(String order) throws SQLException {
		Statement stmt=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		System.out.println("DatabaseUtilBean starts");
		Connection con = DriverManager.getConnection(CONNECTION_URL, JDBC_USERNAME, JDBC_PASSWORD);
		String sql = "insert into order id values ?";
		
		ps = con.prepareStatement(sql);
		ps.setString(1,order);
		ps.executeUpdate();
		
		con.close();	
	}
}
