package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi extends BaseModel {
	
	public Transaksi(DatabaseConnection dbconn) {
		super(dbconn);
	}
	
	public String[][] getTransaksi(String kode_transaksi) {
		String data[][] = null;
		
		String query = "SELECT t.kode_transaksi, t.waktu_transaksi, t.kode_user, u.nama, t.kode_supplier, s.nama FROM transaksi t INNER JOIN user u ON t.kode_user = u.kode_user LEFT JOIN supplier s ON t.kode_supplier = s.kode_supplier WHERE t.kode_transaksi='"+kode_transaksi+"'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getAllPembelianBetween(Date fromDate, Date toDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String from = formatter.format(fromDate);
		String to = formatter.format(toDate);
		
		String data[][] = null;
		
		String query = "SELECT t.kode_transaksi, t.waktu_transaksi, t.kode_user, u.nama, t.kode_supplier, s.nama, SUM(dt.jumlah) qty, SUM(dt.jumlah*dt.harga_satuan) nilai_transaksi FROM transaksi t INNER JOIN user u ON t.kode_user = u.kode_user INNER JOIN supplier s ON t.kode_supplier = s.kode_supplier INNER JOIN data_transaksi dt ON t.kode_transaksi = dt.kode_transaksi WHERE t.waktu_transaksi BETWEEN '" + from + "' AND '" + to +"' AND t.kode_supplier IS NOT NULL GROUP BY t.kode_transaksi";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getAllPenjualanBetween(Date fromDate, Date toDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String from = formatter.format(fromDate);
		String to = formatter.format(toDate);
		
		String data[][] = null;
		
		String query = "SELECT t.kode_transaksi, t.waktu_transaksi, t.kode_user, u.nama, SUM(dt.jumlah) qty, SUM(dt.jumlah*dt.harga_satuan) nilai_transaksi FROM transaksi t INNER JOIN user u ON t.kode_user = u.kode_user INNER JOIN data_transaksi dt ON t.kode_transaksi = dt.kode_transaksi WHERE t.waktu_transaksi BETWEEN '" + from + "' AND '" + to +"' AND t.kode_supplier IS NULL GROUP BY t.kode_transaksi";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getAllTransaksi() {
		String data[][] = null;
		
		String query = "SELECT t.kode_transaksi, t.waktu_transaksi, t.kode_user, u.nama, t.kode_supplier, s.nama FROM transaksi t INNER JOIN user u ON t.kode_user = u.kode_user LEFT JOIN supplier s ON t.kode_supplier = s.kode_supplier";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getAllTransaksiPembelian() {
		String data[][] = null;
		
		String query = "SELECT t.kode_transaksi, t.waktu_transaksi, t.kode_user, u.nama, t.kode_supplier, s.nama, SUM(dt.jumlah) qty, SUM(dt.jumlah*dt.harga_satuan) nilai_transaksi FROM transaksi t INNER JOIN user u ON t.kode_user = u.kode_user INNER JOIN supplier s ON t.kode_supplier = s.kode_supplier INNER JOIN data_transaksi dt ON t.kode_transaksi = dt.kode_transaksi WHERE t.kode_supplier IS NOT NULL GROUP BY t.kode_transaksi";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String[][] getAllTransaksiPenjualan() {
		String data[][] = null;
		
		String query = "SELECT t.kode_transaksi, t.waktu_transaksi, t.kode_user, u.nama, SUM(dt.jumlah) qty, SUM(dt.jumlah*dt.harga_satuan) nilai_satuan FROM transaksi t INNER JOIN user u ON t.kode_user = u.kode_user INNER JOIN data_transaksi dt ON t.kode_transaksi = dt.kode_transaksi WHERE t.kode_supplier IS NULL GROUP BY t.kode_transaksi";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public int getTransaksiCount() {
		String[][] data = null;
		
		String query = "SELECT COUNT(kode_transaksi) FROM transaksi";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}
	
	public int getTransaksiPembelianCount() {
		String[][] data = null;
		
		String query = "SELECT COUNT(kode_transaksi) FROM transaksi WHERE kode_supplier IS NOT NULL";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}
	
	public int getAllPembelianSum() {
		String[][] data = null;
		
		String query = "SELECT SUM(dt.jumlah*dt.harga_satuan) FROM transaksi t INNER JOIN data_transaksi dt ON t.kode_transaksi = dt.kode_transaksi WHERE kode_supplier IS NOT NULL";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}
	
	public int getTransaksiPenjualanCount() {
		String[][] data = null;
		
		String query = "SELECT COUNT(kode_transaksi) FROM transaksi WHERE kode_supplier IS NULL";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}
	
	public int getAllPenjualanSum() {
		String[][] data = null;
		
		String query = "SELECT SUM(dt.jumlah*dt.harga_satuan) FROM transaksi t INNER JOIN data_transaksi dt ON t.kode_transaksi = dt.kode_transaksi WHERE kode_supplier IS NULL";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return Integer.valueOf(data[0][0]);
	}

	public String[][] addTransaksiPembelian(String kode_user, String kode_supplier) {
		String query = "INSERT INTO transaksi(kode_user, kode_supplier) VALUES (?,?)";
		prepare(query);
		String[][] generatedKeys = null;
		
		try {
			prestmt.setInt(1, Integer.valueOf(kode_user));
			prestmt.setInt(2, Integer.valueOf(kode_supplier));
			
			prestmt.executeUpdate();
			generatedKeys = resultSetToArray( prestmt.getGeneratedKeys() );
			
			close();
		} catch (SQLException e) {
			System.out.println("Error binding value: " + e.getMessage());
		}
		
		return generatedKeys;
	}

	public String[][] addTransaksiPenjualan(String kode_user) {
		String query = "INSERT INTO transaksi(kode_user) VALUES (?)";
		prepare(query);
		String[][] generatedKeys = null;
		
		try {
			prestmt.setInt(1, Integer.valueOf(kode_user));
			
			prestmt.executeUpdate();
			generatedKeys = resultSetToArray( prestmt.getGeneratedKeys() );
			
			close();
		} catch (SQLException e) {
			System.out.println("Error binding value: " + e.getMessage());
		}
		
		return generatedKeys;
	}

	public static String[][] newTransaksiPembelian(DatabaseConnection dbconn, String kode_user, String kode_supplier) {
		Transaksi trx = new Transaksi(dbconn);
		
		return trx.addTransaksiPembelian(kode_user, kode_supplier);
	}

	public static String[][] newTransaksiPenjualan(DatabaseConnection dbconn, String kode_user) {
		Transaksi trx = new Transaksi(dbconn);
		
		return trx.addTransaksiPenjualan(kode_user);
	}
	
	public int deleteTransaksi(String kode_transaksi) {
		if (deleteDataTransaksi(kode_transaksi) == 0) return 0;
		
		String query = "DELETE FROM transaksi WHERE kode_transaksi='" + kode_transaksi + "'";
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
	
	public int deleteDataTransaksi(String kode_transaksi) {
		String query = "DELETE FROM data_transaksi WHERE kode_transaksi='" + kode_transaksi + "'";
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
}
