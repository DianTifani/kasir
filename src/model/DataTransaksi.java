package model;

import java.math.BigDecimal;
import java.sql.SQLException;

public class DataTransaksi extends BaseModel {
	
	public DataTransaksi(DatabaseConnection dbconn) {
		super(dbconn);
	}
	
	public int addDataTransaksi(String kode_transaksi, String kode_produk, int jumlah, double harga_satuan) {
		String query = "INSERT INTO data_transaksi(kode_transaksi, kode_produk, jumlah, harga_satuan) VALUES (?, ?, ?, ?)";
		prepare(query);
		int updateCount = 0;
		
		try {
			prestmt.setInt(1, Integer.valueOf(kode_transaksi));
			prestmt.setString(2, kode_produk);
			prestmt.setInt(3, jumlah);
			prestmt.setBigDecimal(4, BigDecimal.valueOf(harga_satuan));
			
			prestmt.executeUpdate();
			
			updateCount = prestmt.getUpdateCount();
			
			close();
		} catch (SQLException e) {
			System.out.println("Error binding value: " + e.getMessage());
		}
		
		return updateCount;
	}
	
	public static int newDataTransaksi(DatabaseConnection dbconn, String kode_transaksi, String kode_produk, int jumlah, double harga_satuan) {
		DataTransaksi dtrx = new DataTransaksi(dbconn);
		
		return dtrx.addDataTransaksi(kode_transaksi, kode_produk, jumlah, harga_satuan);
	}
	
	public String[][] getDetailTransaksi(String kode_transaksi) {
		String[][] data = null;
		
		String query = "SELECT dt.kode_produk, p.nama_produk, dt.jumlah, SUM(dt.jumlah*dt.harga_satuan) subtotal FROM data_transaksi dt INNER JOIN produk p ON p.kode_produk = dt.kode_produk WHERE kode_transaksi='"+ kode_transaksi + "' GROUP BY kode_produk";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
}
