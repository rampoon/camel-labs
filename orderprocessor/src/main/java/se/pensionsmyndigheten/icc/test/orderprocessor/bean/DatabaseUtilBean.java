package se.pensionsmyndigheten.icc.test.orderprocessor.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import se.pensionsmyndigheten.icc.test.order.OrderType;
import se.pensionsmyndigheten.icc.test.order.Orders;

/**
 * A first very simple bean for persiting orders from messages in a database
 *
 */
public class DatabaseUtilBean {
	private final static String CONNECTION_URL="jdbc:mysql://localhost:3306/camel_labs?serverTimezone=CET";
	private final static String JDBC_USERNAME = "root";
	private final static String JDBC_PASSWORD = "root";
	
	public void persist(String order) {
		 	
	}
	
	/**
	 * A first version of db-persist method
	 */
	public void save(Orders orders) throws SQLException {
		Statement stmt=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<OrderType> orderTypeList = orders.getOrder();
		OrderType orderType = orderTypeList.iterator().next();
		
		System.out.println("DatabaseUtilBean starts");
		Connection con = DriverManager.getConnection(CONNECTION_URL, JDBC_USERNAME, JDBC_PASSWORD);
		String sql = "insert into pm_order (id,date,productName,quantity) values (?,?,?,?)";
		
		ps = con.prepareStatement(sql);
		ps.setLong(1,orderType.getId().longValue());
		ps.setDate(2, new Date(orderType.getDate().toGregorianCalendar().getTime().getTime()));
		ps.setString(3,orderType.getProductName());
		ps.setLong(4,orderType.getQuantity().longValue());
		ps.executeUpdate();
		
		con.close();	
	}
}
