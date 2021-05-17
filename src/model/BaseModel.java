package model;

import java.sql.*;

public class BaseModel {
	Connection dbconn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement prestmt;
	
	public BaseModel(DatabaseConnection dbconn) {
		this.dbconn = dbconn.getConnection();
	}
	
	public ResultSet executeQuery(String query) {
		try {
			stmt = dbconn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Cannot execute query: " + e.getMessage());
		}
		
		return rs;
	}
	
	public void prepare(String query) {
		try {
			prestmt = dbconn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			System.out.println("Cannot prepare statement: " + e.getMessage());
		}
	}
	
	public void executeUpdate(String query) {
		try {
			stmt = dbconn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void preparedExecuteUpdate(String query) {
		try {
			if (prestmt != null) {
				prestmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String[][] resultSetToArray() {
		return resultSetToArray(this.rs);
	}
	
	public String[][] resultSetToArray(ResultSet rs) {
		String[][] arr = null;
		
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int jmlKolom = meta.getColumnCount();
			int jmlBaris = getRowCount(rs);
			arr = new String[jmlBaris][jmlKolom];
			
			for (int r=0; r<jmlBaris; r++) {
				for (int c=0; c<jmlKolom; c++) {
					arr[r][c] = rs.getString(c+1);
				}
				rs.next();
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return arr;
	}
	
	public void close() {
		try {
			if (rs != null) {
				rs.close();					
			}
			
			rs = null;
			
			if (stmt != null) {
				stmt.close();									
			}
			
			stmt = null;
			
			if (prestmt != null) {
				prestmt.close();
			}
			
			prestmt = null;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
 	
	public int getRowCount(ResultSet r) {
		int rowCount = 0;
		
		try {
			while(r.next()) {
				rowCount++;
			}
			
			r.absolute(1);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return rowCount;
	}
}
