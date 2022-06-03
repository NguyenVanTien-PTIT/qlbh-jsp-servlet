package net.javaguides.qlbanhang.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
	
	public static Connection getMySQLConnection() throws ClassNotFoundException,SQLException{
		String hostname="localhost";
		String dbName="quanlibanhang";
		String username="root";
		String password="Tt123456789@";
		return getMySQLConnection(hostname,dbName,username, password);
	}

	private static Connection getMySQLConnection(String hostname, String dbName, String username, String password)
	throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL="jdbc:mysql://localhost:3306/quanlibanhang";
		Connection conn=DriverManager.getConnection(connectionURL, username, password);
		return conn;
	}
}
