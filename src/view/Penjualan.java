package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import model.DataTransaksi;
import model.DatabaseConnection;
import model.Produk;
import model.Transaksi;
import model.data.ProdukData;
import model.data.UserData;

import java.awt.SystemColor;

public class Penjualan extends JFrame {

	private JPanel contentPane;
	private JTextField tfNamaBarang;
	private JTextField tfQty;
	private JTextField tfUangDiterima;
	
	private ArrayList<ProdukData> transaksiProduk;
	private ArrayList<JPanel> transaksiPanel;
	
	private DatabaseConnection dbconn = null;
	
	private Dashboard viewDashboard;
	
	private UserData sessionUser;
	private Produk produkModel;
	
	private JLabel lblKasir;
	private JLabel lblTransTime;
	private JLabel lblTotalHitung;
	private JLabel lblPpnHitung;
	private JLabel lblTotalBayarHitung;
	private JTextField tfKodeBarang;
	private JPanel panel;
	
	private long total;
	private long totalBayar;
	private double ppn;
	
	private NumberFormat format = NumberFormat.getCurrencyInstance();

	/**
	 * Create the frame.
	 */
	public Penjualan() {
		setResizable(false);
		transaksiProduk = new ArrayList<ProdukData>();
		transaksiPanel = new ArrayList<JPanel>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 25, 640, 640);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 328, 75, 95, 0, 0};
		gridBagLayout.rowHeights = new int[]{15, 27, 14, 14, 15, 20, 33, 0, 0, 229, 0, 0, 0, 0, 5, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel("Transaksi Penjualan");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		lblKasir = new JLabel("Kasir: ");
		GridBagConstraints gbc_lblKasir = new GridBagConstraints();
		gbc_lblKasir.anchor = GridBagConstraints.WEST;
		gbc_lblKasir.insets = new Insets(0, 0, 5, 5);
		gbc_lblKasir.gridx = 1;
		gbc_lblKasir.gridy = 2;
		contentPane.add(lblKasir, gbc_lblKasir);
		
		lblTransTime = new JLabel("Waktu transaksi: ");
		GridBagConstraints gbc_lblTransTime = new GridBagConstraints();
		gbc_lblTransTime.anchor = GridBagConstraints.WEST;
		gbc_lblTransTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTransTime.gridx = 1;
		gbc_lblTransTime.gridy = 3;
		contentPane.add(lblTransTime, gbc_lblTransTime);
		
		JLabel lblNamaBarang = new JLabel("Nama barang");
		GridBagConstraints gbc_lblNamaBarang = new GridBagConstraints();
		gbc_lblNamaBarang.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNamaBarang.insets = new Insets(0, 0, 5, 5);
		gbc_lblNamaBarang.gridx = 1;
		gbc_lblNamaBarang.gridy = 5;
		contentPane.add(lblNamaBarang, gbc_lblNamaBarang);
		
		tfNamaBarang = new JTextField();
		GridBagConstraints gbc_tfNamaBarang = new GridBagConstraints();
		gbc_tfNamaBarang.gridwidth = 2;
		gbc_tfNamaBarang.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNamaBarang.insets = new Insets(0, 0, 5, 5);
		gbc_tfNamaBarang.gridx = 1;
		gbc_tfNamaBarang.gridy = 6;
		contentPane.add(tfNamaBarang, gbc_tfNamaBarang);
		tfNamaBarang.setColumns(3);
		
		Penjualan self = this;
		
		JButton btnCariBarang = new JButton("Cari barang");
		btnCariBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HasilPencarian hp = new HasilPencarian();
				hp.setProdukModel(produkModel);

				hp.viewShow(self);
				
				hp.cari(tfNamaBarang.getText());
			}
		});
		btnCariBarang.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnCariBarang = new GridBagConstraints();
		gbc_btnCariBarang.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCariBarang.insets = new Insets(0, 0, 5, 5);
		gbc_btnCariBarang.gridx = 3;
		gbc_btnCariBarang.gridy = 6;
		contentPane.add(btnCariBarang, gbc_btnCariBarang);
		
		JLabel lblKodeBarang = new JLabel("Kode barang");
		GridBagConstraints gbc_lblKodeBarang = new GridBagConstraints();
		gbc_lblKodeBarang.anchor = GridBagConstraints.WEST;
		gbc_lblKodeBarang.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodeBarang.gridx = 1;
		gbc_lblKodeBarang.gridy = 7;
		contentPane.add(lblKodeBarang, gbc_lblKodeBarang);
		
		JLabel lblQty = new JLabel("Qty");
		GridBagConstraints gbc_lblQty = new GridBagConstraints();
		gbc_lblQty.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblQty.insets = new Insets(0, 0, 5, 5);
		gbc_lblQty.gridx = 2;
		gbc_lblQty.gridy = 7;
		contentPane.add(lblQty, gbc_lblQty);
		
		tfKodeBarang = new JTextField();
		GridBagConstraints gbc_tfKodeBarang = new GridBagConstraints();
		gbc_tfKodeBarang.insets = new Insets(0, 0, 5, 5);
		gbc_tfKodeBarang.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKodeBarang.gridx = 1;
		gbc_tfKodeBarang.gridy = 8;
		contentPane.add(tfKodeBarang, gbc_tfKodeBarang);
		tfKodeBarang.setColumns(10);
		
		tfQty = new JTextField();
		GridBagConstraints gbc_tfQty = new GridBagConstraints();
		gbc_tfQty.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQty.insets = new Insets(0, 0, 5, 5);
		gbc_tfQty.gridx = 2;
		gbc_tfQty.gridy = 8;
		contentPane.add(tfQty, gbc_tfQty);
		tfQty.setColumns(10);
		
		JButton btnTambahItem = new JButton("Tambah Item");
		btnTambahItem.setBackground(new Color(175, 238, 238));
		GridBagConstraints gbc_btnTambahItem = new GridBagConstraints();
		gbc_btnTambahItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTambahItem.anchor = GridBagConstraints.NORTH;
		gbc_btnTambahItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnTambahItem.gridx = 3;
		gbc_btnTambahItem.gridy = 8;
		contentPane.add(btnTambahItem, gbc_btnTambahItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 9;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {2, 0, 2, 0};
		gbl_panel.rowHeights = new int[]{2, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		ActionListener tambahItem = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addProdukTransaksi(tfKodeBarang.getText(), tfQty.getText());
			}
		};
		
		btnTambahItem.addActionListener(tambahItem);
		
		JLabel lblTotal = new JLabel("Total");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 2;
		gbc_lblTotal.gridy = 10;
		contentPane.add(lblTotal, gbc_lblTotal);
		
		lblTotalHitung = new JLabel("0");
		GridBagConstraints gbc_lblTotalHitung = new GridBagConstraints();
		gbc_lblTotalHitung.anchor = GridBagConstraints.EAST;
		gbc_lblTotalHitung.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalHitung.gridx = 3;
		gbc_lblTotalHitung.gridy = 10;
		contentPane.add(lblTotalHitung, gbc_lblTotalHitung);
		
		JLabel lblPpn = new JLabel("PPN");
		GridBagConstraints gbc_lblPpn = new GridBagConstraints();
		gbc_lblPpn.anchor = GridBagConstraints.EAST;
		gbc_lblPpn.insets = new Insets(0, 0, 5, 5);
		gbc_lblPpn.gridx = 2;
		gbc_lblPpn.gridy = 11;
		contentPane.add(lblPpn, gbc_lblPpn);
		
		lblPpnHitung = new JLabel("0");
		GridBagConstraints gbc_lblPpnHitung = new GridBagConstraints();
		gbc_lblPpnHitung.anchor = GridBagConstraints.EAST;
		gbc_lblPpnHitung.insets = new Insets(0, 0, 5, 5);
		gbc_lblPpnHitung.gridx = 3;
		gbc_lblPpnHitung.gridy = 11;
		contentPane.add(lblPpnHitung, gbc_lblPpnHitung);
		
		JLabel lblTotalBayar = new JLabel("Total bayar");
		GridBagConstraints gbc_lblTotalBayar = new GridBagConstraints();
		gbc_lblTotalBayar.anchor = GridBagConstraints.EAST;
		gbc_lblTotalBayar.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalBayar.gridx = 2;
		gbc_lblTotalBayar.gridy = 12;
		contentPane.add(lblTotalBayar, gbc_lblTotalBayar);
		
		lblTotalBayarHitung = new JLabel("0");
		GridBagConstraints gbc_lblTotalBayarHitung = new GridBagConstraints();
		gbc_lblTotalBayarHitung.anchor = GridBagConstraints.EAST;
		gbc_lblTotalBayarHitung.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalBayarHitung.gridx = 3;
		gbc_lblTotalBayarHitung.gridy = 12;
		contentPane.add(lblTotalBayarHitung, gbc_lblTotalBayarHitung);
		
		JLabel lblPembayaranDiterima = new JLabel("Pembayaran diterima");
		GridBagConstraints gbc_lblPembayaranDiterima = new GridBagConstraints();
		gbc_lblPembayaranDiterima.insets = new Insets(0, 0, 5, 5);
		gbc_lblPembayaranDiterima.anchor = GridBagConstraints.EAST;
		gbc_lblPembayaranDiterima.gridx = 1;
		gbc_lblPembayaranDiterima.gridy = 13;
		contentPane.add(lblPembayaranDiterima, gbc_lblPembayaranDiterima);
		
		tfUangDiterima = new JTextField();
		GridBagConstraints gbc_tfUangDiterima = new GridBagConstraints();
		gbc_tfUangDiterima.gridwidth = 2;
		gbc_tfUangDiterima.insets = new Insets(0, 0, 5, 5);
		gbc_tfUangDiterima.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUangDiterima.gridx = 2;
		gbc_tfUangDiterima.gridy = 13;
		contentPane.add(tfUangDiterima, gbc_tfUangDiterima);
		tfUangDiterima.setColumns(10);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.setForeground(new Color(255, 255, 255));
		btnKembali.setBackground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnKembali = new GridBagConstraints();
		gbc_btnKembali.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnKembali.insets = new Insets(0, 0, 0, 5);
		gbc_btnKembali.gridx = 2;
		gbc_btnKembali.gridy = 15;
		contentPane.add(btnKembali, gbc_btnKembali);
		
		btnKembali.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				back();
			}
		});
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (transaksiProduk.size() == 0) return;
				
				if (pembayaranValid()) {
										
					PenjualanKembalian pk = new PenjualanKembalian();
					
					long kembalian = hitungKembalian();
					
					pk.tampilkanKembalian(kembalian);
					
					pk.viewShow(self);
				}
			}
		});
		btnSimpan.setBackground(new Color(0, 250, 154));
		GridBagConstraints gbc_btnSimpan = new GridBagConstraints();
		gbc_btnSimpan.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSimpan.insets = new Insets(0, 0, 0, 5);
		gbc_btnSimpan.gridx = 3;
		gbc_btnSimpan.gridy = 15;
		contentPane.add(btnSimpan, gbc_btnSimpan);
	}
	
	public Penjualan(Dashboard viewDashboard) {
		this();
		this.produkModel = viewDashboard.getProdukModel();
		this.viewDashboard = viewDashboard;
		this.dbconn = this.viewDashboard.getConnection();
	}

	public void viewShow(UserData sessionUser) {
		this.sessionUser = sessionUser;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		lblKasir.setText("Kasir: " + sessionUser.getNama());
		lblTransTime.setText("Waktu transaksi: " +formatter.format(date) + " WIB");
		
		this.setVisible(true);
		this.viewDashboard.getFrame().setVisible(false);
	}
	
	public void produkDipilih(ProdukData produk) {
		this.tfNamaBarang.setText(produk.getNama_produk());
		this.tfKodeBarang.setText(produk.getKode_produk());
	}
	
	public void addProdukTransaksi(String kode_produk, String qty) {
		int qtyInt = 0;
		
		if (kode_produk.length() == 0) return;
		if (qty.length() == 0) return; 
		if (qty.equals("0")) return;
		
		try {
			
			qtyInt = Integer.valueOf(qty);
			
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			clearTextField();
			return;
		}
		
		ProdukData produk = new ProdukData(produkModel, kode_produk);
		
		if (produk.isValid()) {
			boolean isDuplicate = false;
			ProdukData duplicateProduk = null;
			
			produk.setQty(qty.length() > 0 ? qtyInt : 1);
			
			for (ProdukData pd : transaksiProduk) {
				if (pd.getKode_produk().equals( produk.getKode_produk() )) {
					isDuplicate = true;
					duplicateProduk = pd;
					
					break;
				}
			}
			
			if (isDuplicate) {
				
				int existingProdukQty = duplicateProduk.getQty();
				int addedProdukQty = produk.getQty();
				duplicateProduk.setQty( existingProdukQty + addedProdukQty );
			} else {
				
				transaksiProduk.add(produk);				
			}
			
			refresh();
			clearTextField();
		}
	}
	
	public void updateListProdukTransaksi() {
		clearAllProdukTransaksi();
		
		for (int i=0; i<transaksiProduk.size(); i++) {
			ProdukData produk = transaksiProduk.get(i);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.insets = new Insets(5, 5, 5, 5);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 1;
			gbc_panel_1.gridy = 1 + i;
			panel.add(panel_1, gbc_panel_1);
			
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{10, 30, 50, 25, 25, 0, 5};
			gbl_panel_1.rowHeights = new int[]{5, 10, 10, 5};
			gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0, 0, 0, 0.0, 0.0};
			gbl_panel_1.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0, 0.0};
			panel_1.setLayout(gbl_panel_1);
			
			JLabel lblKodeProduk = new JLabel(produk.getKode_produk());
			lblKodeProduk.setFont(new Font("Consolas", Font.PLAIN, 11));
			GridBagConstraints gbc_lblKodeProduk = new GridBagConstraints();
			gbc_lblKodeProduk.anchor = GridBagConstraints.WEST;
			gbc_lblKodeProduk.insets = new Insets(0, 0, 5, 5);
			gbc_lblKodeProduk.gridx = 1;
			gbc_lblKodeProduk.gridy = 1;
			panel_1.add(lblKodeProduk, gbc_lblKodeProduk);
			
			String nama_produk = produk.getNama_produk();
			nama_produk = nama_produk.length() > 30 ? nama_produk.substring(0, 27) + "..." : nama_produk;
			
			JLabel lblNamaProduk = new JLabel(nama_produk);
			lblNamaProduk.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblNamaProduk = new GridBagConstraints();
			gbc_lblNamaProduk.anchor = GridBagConstraints.WEST;
			gbc_lblNamaProduk.insets = new Insets(0, 0, 5, 5);
			gbc_lblNamaProduk.gridx = 1;
			gbc_lblNamaProduk.gridy = 2;
			panel_1.add(lblNamaProduk, gbc_lblNamaProduk);
			
			JLabel lblHargaSatuan = new JLabel(String.valueOf(produk.getHarga_satuan()) );
			GridBagConstraints gbc_lblHargaSatuan = new GridBagConstraints();
			gbc_lblHargaSatuan.gridheight = 2;
			gbc_lblHargaSatuan.insets = new Insets(0, 0, 5, 5);
			gbc_lblHargaSatuan.gridx = 2;
			gbc_lblHargaSatuan.gridy = 1;
			panel_1.add(lblHargaSatuan, gbc_lblHargaSatuan);
			
			JLabel lblQty = new JLabel(String.valueOf(produk.getQty()));
			GridBagConstraints gbc_lblQty = new GridBagConstraints();
			gbc_lblQty.gridheight = 2;
			gbc_lblQty.insets = new Insets(0, 0, 5, 5);
			gbc_lblQty.gridx = 3;
			gbc_lblQty.gridy = 1;
			panel_1.add(lblQty, gbc_lblQty);
			
			JLabel lblSubtotal = new JLabel(format.format( Double.valueOf(produk.getHarga_satuan()) * produk.getQty() ) );
			GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
			gbc_lblSubtotal.gridheight = 2;
			gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
			gbc_lblSubtotal.gridx = 4;
			gbc_lblSubtotal.gridy = 1;
			panel_1.add(lblSubtotal, gbc_lblSubtotal);
			
			JButton btnHapus = new JButton("Hapus");
			btnHapus.setForeground(new Color(255, 255, 255));
			btnHapus.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_btnHapus = new GridBagConstraints();
			gbc_btnHapus.gridheight = 2;
			gbc_btnHapus.insets = new Insets(0, 0, 5, 5);
			gbc_btnHapus.gridx = 5;
			gbc_btnHapus.gridy = 1;
			panel_1.add(btnHapus, gbc_btnHapus);
			
			btnHapus.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					hapusProduk(produk, panel_1);
				}
			});
			
			transaksiPanel.add(panel_1);
			
			this.revalidate();
		}	
	}
	
	public void hapusProduk(ProdukData produk, JPanel panelProduk) {
		transaksiProduk.remove(produk);
		panelProduk.setVisible(false);
		refresh();
	}
	
	public void calculateTotal() {
		long total = 0l;
		long totalBayar = 0l;
		
		for (ProdukData produk : transaksiProduk) {
			total += (long) produk.getHarga_satuan() * produk.getQty();
		}
		
		double ppn = 0.1 * total;
		
		totalBayar = total + (long) ppn;
		
		this.total = total;
		this.totalBayar = totalBayar;
		this.ppn = ppn;
		
		lblTotalHitung.setText(String.valueOf(this.total));
		lblPpnHitung.setText(String.valueOf(this.ppn));
		lblTotalBayarHitung.setText(format.format(this.totalBayar));
	}
	
	public void clearTextField() {
		tfNamaBarang.setText("");
		tfKodeBarang.setText("");
		tfQty.setText("");
	}
	
	public void clearUangDiterima() {
		tfUangDiterima.setText("");
	}
	
	public long hitungKembalian() {
		try {
			
			long uangDiterima = Long.valueOf(tfUangDiterima.getText());
			
			if (uangDiterima >= totalBayar) {
				return uangDiterima - totalBayar;
			} else {
				return 0l;
			}
			
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return 0l;
		}
	}
	
	public boolean pembayaranValid() {
		try {
			
			long uangDiterima = Long.valueOf(tfUangDiterima.getText());
			
			return uangDiterima >= totalBayar;
			
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public void simpanTransaksi() {
		Transaksi trx = new Transaksi(dbconn);
		
		String generatedKeys = trx.addTransaksiPenjualan(String.valueOf( sessionUser.getKode_user() ))[0][0];
		
		for (ProdukData produk : transaksiProduk) {
			DataTransaksi.newDataTransaksi(dbconn,
					generatedKeys, produk.getKode_produk(), produk.getQty(), produk.getHarga_satuan());
		}
		
		hapusSemuaProduk();
		clearUangDiterima();
		back();
	}
	
	public void hapusSemuaProduk() {
		for(Iterator<ProdukData> itr = transaksiProduk.iterator(); itr.hasNext();){            
            itr.next();            
            itr.remove();
        }
		
		transaksiPanel = new ArrayList<JPanel>();
		
		clearAllProdukTransaksi();
		calculateTotal();
	}
	
	public void refresh() {
		updateListProdukTransaksi();
		calculateTotal();
	}
	
	public void clearAllProdukTransaksi() {
		this.panel.removeAll();
		
		for (JPanel panel : transaksiPanel) {
			panel.setVisible(false);
		}
		
		transaksiPanel = new ArrayList<JPanel>();
	
		this.revalidate();
		this.repaint();
	}
	
	public void back() {
		viewDashboard.back();
		setVisible(false);
	}
	
}
