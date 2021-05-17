package model;

import java.sql.*;

public class DatabaseConnection {
	private Connection conn = null;
	
	public DatabaseConnection() {}
	
	public DatabaseConnection(String dsn, String user, String password) {
		this.setConnection(dsn, user, password);
	}
	
	public Connection connect(String dsn, String user, String password) {
		this.setConnection(dsn, user, password);
		return this.conn;
	}
	
	public void setConnection(String dsn, String user, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			this.conn = DriverManager.getConnection(dsn, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Connection error: " + e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}
