package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.DataTransaksi;
import model.DatabaseConnection;
import model.Produk;
import model.Supplier;
import model.Transaksi;
import model.User;
import model.data.UserData;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

public class Dashboard {

	private JFrame frmApilkasiKasir;
	private DatabaseConnection dbconn;
	
	private Login viewLogin;
	private Pembelian viewPembelian;
	private Penjualan viewPenjualan;
	private DataSupplier viewDataSupplier;
	private DataProduk viewDataProduk;
	private LaporanTransaksi viewLaporanTransaksi;
	
	private User userModel;
	private Produk produkModel;
	private Supplier supplierModel;
	private Transaksi transaksiModel;
	private DataTransaksi dataTransaksiModel;
	
	private UserData sessionUser;

	private JLabel lblGreeting;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					
					if (window.sessionUser == null) {
						
						window.viewLogin.viewShow();
						
					} else {
						window.frmApilkasiKasir.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		dbconn = new DatabaseConnection();
		dbconn.connect("jdbc:mysql://localhost:3306/diantifani_kasir?useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta", "root", "");
		
		userModel = new User(dbconn);
		produkModel = new Produk(dbconn);
		supplierModel = new Supplier(dbconn);
		transaksiModel = new Transaksi(dbconn);
		dataTransaksiModel = new DataTransaksi(dbconn);
		
		sessionUser = null;
		
		viewLogin = new Login(this);
		viewPembelian = new Pembelian(this);
		viewPenjualan = new Penjualan(this);
		viewDataSupplier = new DataSupplier(this);
		viewDataProduk = new DataProduk(this);
		viewLaporanTransaksi = new LaporanTransaksi(this);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmApilkasiKasir = new JFrame();
		frmApilkasiKasir.setTitle("Apilkasi Kasir");
		frmApilkasiKasir.setResizable(false);
		frmApilkasiKasir.setBounds(100, 25, 640, 500);
		frmApilkasiKasir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 25, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 25, 25, 0, 15, 0, 15, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmApilkasiKasir.getContentPane().setLayout(gridBagLayout);
		
		ActionListener penjualanOnClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				viewPenjualan.viewShow(sessionUser);
			}
		};
		
		ActionListener pembelianOnClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				viewPembelian.viewShow(sessionUser);
			}
		};
		
		JLabel lblTitle = new JLabel("Aplikasi Kasir");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		frmApilkasiKasir.getContentPane().add(lblTitle, gbc_lblTitle);
		
		lblGreeting = new JLabel("");
		GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
		gbc_lblGreeting.anchor = GridBagConstraints.WEST;
		gbc_lblGreeting.insets = new Insets(0, 0, 5, 5);
		gbc_lblGreeting.gridx = 1;
		gbc_lblGreeting.gridy = 2;
		frmApilkasiKasir.getContentPane().add(lblGreeting, gbc_lblGreeting);
		
		JButton btnPenjualan = new JButton("Transaksi Penjualan");
		btnPenjualan.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnPenjualan = new GridBagConstraints();
		gbc_btnPenjualan.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPenjualan.insets = new Insets(0, 0, 5, 5);
		gbc_btnPenjualan.gridx = 1;
		gbc_btnPenjualan.gridy = 4;
		frmApilkasiKasir.getContentPane().add(btnPenjualan, gbc_btnPenjualan);
		
		JButton btnPembelian = new JButton("Transaksi Pembelian");
		btnPembelian.addActionListener(pembelianOnClick);
		btnPembelian.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnPembelian = new GridBagConstraints();
		gbc_btnPembelian.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPembelian.insets = new Insets(0, 0, 5, 5);
		gbc_btnPembelian.gridx = 3;
		gbc_btnPembelian.gridy = 4;
		frmApilkasiKasir.getContentPane().add(btnPembelian, gbc_btnPembelian);
		
		JButton btnDataProduk = new JButton("Data Produk");
		btnDataProduk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewDataProduk.viewShow();
			}
		});
		btnDataProduk.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnDataProduk = new GridBagConstraints();
		gbc_btnDataProduk.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDataProduk.insets = new Insets(0, 0, 5, 5);
		gbc_btnDataProduk.gridx = 1;
		gbc_btnDataProduk.gridy = 6;
		frmApilkasiKasir.getContentPane().add(btnDataProduk, gbc_btnDataProduk);
		
		JButton btnLaporanTransaksi = new JButton("Laporan Transaksi");
		btnLaporanTransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewLaporanTransaksi.viewShow();
			}
		});
		btnLaporanTransaksi.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnLaporanTransaksi = new GridBagConstraints();
		gbc_btnLaporanTransaksi.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLaporanTransaksi.insets = new Insets(0, 0, 5, 5);
		gbc_btnLaporanTransaksi.gridx = 3;
		gbc_btnLaporanTransaksi.gridy = 6;
		frmApilkasiKasir.getContentPane().add(btnLaporanTransaksi, gbc_btnLaporanTransaksi);
		
		JButton btnKeluar = new JButton("KELUAR");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sessionUser = null;
				
				viewLogin = new Login(new Dashboard());
				frmApilkasiKasir.setVisible(false);
				viewLogin.setVisible(true);
			}
		});
		
		JButton btnDataSupplier = new JButton("Data Supplier");
		btnDataSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewDataSupplier.viewShow();
			}
		});
		btnDataSupplier.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnDataSupplier = new GridBagConstraints();
		gbc_btnDataSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDataSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_btnDataSupplier.gridx = 1;
		gbc_btnDataSupplier.gridy = 8;
		frmApilkasiKasir.getContentPane().add(btnDataSupplier, gbc_btnDataSupplier);
		btnKeluar.setForeground(Color.WHITE);
		btnKeluar.setBackground(Color.RED);
		GridBagConstraints gbc_btnKeluar = new GridBagConstraints();
		gbc_btnKeluar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnKeluar.insets = new Insets(0, 0, 5, 5);
		gbc_btnKeluar.gridx = 3;
		gbc_btnKeluar.gridy = 8;
		frmApilkasiKasir.getContentPane().add(btnKeluar, gbc_btnKeluar);
		
		btnPenjualan.addActionListener(penjualanOnClick);
	}

	public JFrame getFrame() {
		return this.frmApilkasiKasir;
	}

	public UserData getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(UserData sessionUser) {
		this.sessionUser = sessionUser;
	}
	
	public void back() {
		this.lblGreeting.setText("Halo, "+sessionUser.getNama());
		this.frmApilkasiKasir.setVisible(true);
	}
	
	public DatabaseConnection getConnection() {
		return this.dbconn;
	}

	public User getUserModel() {
		return userModel;
	}

	public Produk getProdukModel() {
		return produkModel;
	}

	public Supplier getSupplierModel() {
		return supplierModel;
	}

	public Transaksi getTransaksiModel() {
		return transaksiModel;
	}

	public DataTransaksi getDataTransaksiModel() {
		return dataTransaksiModel;
	}
}
