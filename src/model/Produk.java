package model;

import java.sql.SQLException;

import model.data.ProdukData;

public class Produk extends BaseModel {
	
	public Produk(DatabaseConnection dbconn) {
		super(dbconn);
	}
	
	public String [][] getAllProduk() {
		String data[][] = null;
		
		String query = "SELECT p.kode_produk, p.nama_produk, p.satuan, p.harga_satuan, s.nama, s.kode_supplier FROM produk p INNER JOIN supplier s ON p.kode_supplier = s.kode_supplier";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getProduk(String kode_produk) {
		String data[][] = null;
		
		String query = "SELECT p.kode_produk, p.nama_produk, p.satuan, p.harga_satuan, s.nama, s.kode_supplier FROM produk p INNER JOIN supplier s ON p.kode_supplier = s.kode_supplier WHERE kode_produk='"+ kode_produk +"'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] searchProdukByNama(String query_nama) {
		String data[][] = null;
		
		String query = "SELECT p.kode_produk, p.nama_produk, p.satuan, p.harga_satuan, s.nama, s.kode_supplier FROM produk p INNER JOIN supplier s ON p.kode_supplier = s.kode_supplier WHERE nama_produk LIKE '%" + query_nama + "%'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] searchProdukByNama(String query_nama, int kode_supplier) {
		String data[][] = null;
		
		String query = "SELECT p.kode_produk, p.nama_produk, p.satuan, p.harga_satuan, s.nama, s.kode_supplier FROM produk p INNER JOIN supplier s ON p.kode_supplier = s.kode_supplier WHERE nama_produk LIKE '%" + query_nama + "%' AND p.kode_supplier = '"+ kode_supplier + "'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}

	public int updateProduk(ProdukData produkData) {
		String kode_produk = produkData.getKode_produk();
		String query = "UPDATE produk SET nama_produk=?, satuan=?, harga_satuan=?, kode_supplier=? WHERE kode_produk='" + kode_produk + "'";
		prepare(query);
		int updateCount = 0;
		
		try {
			prestmt.setString(1, produkData.getNama_produk());
			prestmt.setString(2, produkData.getSatuan());
			prestmt.setDouble(3, produkData.getHarga_satuan());
			prestmt.setInt(4, produkData.getKode_supplier());
			
			prestmt.executeUpdate();
			
			updateCount = prestmt.getUpdateCount();
			
			close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	public String[][] addProduk(String kode_produk, String nama, String satuan, Double harga_satuan, int kode_supplier) {
		String query = "INSERT INTO produk(kode_produk, nama_produk, satuan, harga_satuan, kode_supplier) VALUES(?,?,?,?,?)";
		prepare(query);
		String[][] generatedKeys = null;
		
		try {
			prestmt.setString(1, kode_produk);
			prestmt.setString(2, nama);
			prestmt.setString(3, satuan);
			prestmt.setDouble(4, harga_satuan);
			prestmt.setInt(5, kode_supplier);
			
			prestmt.executeUpdate();
			
			generatedKeys = resultSetToArray( prestmt.getGeneratedKeys() );
			
			close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return generatedKeys;
	}
	
	public int deleteProduk(String kode_produk) {
		String query = "DELETE FROM produk WHERE kode_produk='" + kode_produk + "'";
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
	
	public int getProdukCount() {
		String[][] data = null;
		
		String query = "SELECT COUNT(kode_produk) FROM produk";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}
}
