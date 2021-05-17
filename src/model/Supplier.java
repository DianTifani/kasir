package model;

import java.sql.SQLException;

import model.data.SupplierData;

public class Supplier extends BaseModel {
	
	public Supplier(DatabaseConnection dbconn) {
		super(dbconn);
	}
	
	public String [][] getAllSupplier() {
		String data[][] = null;
		
		String query = "SELECT kode_supplier, nama, no_telp, alamat FROM supplier";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getSupplier(String kode_supplier) {
		String data[][] = null;
		
		String query = "SELECT kode_supplier, nama, no_telp, alamat FROM supplier WHERE kode_supplier='"+ kode_supplier +"'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] searchSupplierByNama(String query_nama) {
		String data[][] = null;
		
		String query = "SELECT kode_supplier, nama, no_telp, alamat FROM supplier WHERE nama LIKE '%" + query_nama + "%'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public int updateSupplier(SupplierData supplierData) {
		String kode_supplier = supplierData.getKode_supplier();
		String query = "UPDATE supplier SET nama=?, no_telp=?, alamat=? WHERE kode_supplier='" + kode_supplier + "'";
		prepare(query);
		int updateCount = 0;
		
		try {
			prestmt.setString(1, supplierData.getNama());
			prestmt.setString(2, supplierData.getNo_telp());
			prestmt.setString(3, supplierData.getAlamat());
			
			prestmt.executeUpdate();
			
			updateCount = prestmt.getUpdateCount();
			
			close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	public String[][] addSupplier(String nama, String no_telp, String alamat) {
		String query = "INSERT INTO supplier(nama, no_telp, alamat) VALUES(?,?,?)";
		prepare(query);
		String[][] generatedKeys = null;
		
		try {
			prestmt.setString(1, nama);
			prestmt.setString(2, no_telp);
			prestmt.setString(3, alamat);
			
			prestmt.executeUpdate();
			
			generatedKeys = resultSetToArray( prestmt.getGeneratedKeys() );
			
			close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return generatedKeys;
	}
	
	public int deleteSupplier(String kode_supplier) {
		String query = "DELETE FROM supplier WHERE kode_supplier='" + kode_supplier + "'";
		int updateCount = 0;
		
		try {
			executeUpdate(query);
			updateCount = stmt.getUpdateCount();
			
			close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return updateCount;
	}
	
	public int getSupplierCount() {
		String[][] data = null;
		
		String query = "SELECT COUNT(kode_supplier) FROM supplier";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}
}
