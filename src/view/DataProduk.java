package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Produk;
import model.Supplier;
import model.data.ProdukData;
import javax.swing.ScrollPaneConstants;

public class DataProduk extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField tfSearchQuery;
	private JLabel lblJumlah;
	
	private ArrayList<JPanel> produkListPanel = new ArrayList<JPanel>();
	
	private Dashboard viewDashboard;
	
	private Produk produkModel;
	private Supplier supplierModel;

	/**
	 * Create the frame.
	 */
	public DataProduk() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 50, 0, 75, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 15, 0, 15, 0, 200, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDataProduk = new JLabel("Data Produk");
		lblDataProduk.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblDataProduk = new GridBagConstraints();
		gbc_lblDataProduk.anchor = GridBagConstraints.WEST;
		gbc_lblDataProduk.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataProduk.gridx = 1;
		gbc_lblDataProduk.gridy = 1;
		contentPane.add(lblDataProduk, gbc_lblDataProduk);
		
		tfSearchQuery = new JTextField();
		GridBagConstraints gbc_tfSearchQuery = new GridBagConstraints();
		gbc_tfSearchQuery.gridwidth = 4;
		gbc_tfSearchQuery.insets = new Insets(0, 0, 5, 5);
		gbc_tfSearchQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSearchQuery.gridx = 1;
		gbc_tfSearchQuery.gridy = 3;
		contentPane.add(tfSearchQuery, gbc_tfSearchQuery);
		tfSearchQuery.setColumns(10);
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = tfSearchQuery.getText();
				
				searchProduk(query);
			}
		});
		btnCari.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnCari = new GridBagConstraints();
		gbc_btnCari.anchor = GridBagConstraints.EAST;
		gbc_btnCari.insets = new Insets(0, 0, 5, 5);
		gbc_btnCari.gridx = 5;
		gbc_btnCari.gridy = 3;
		contentPane.add(btnCari, gbc_btnCari);
		
		lblJumlah = new JLabel("Jumlah:");
		GridBagConstraints gbc_lblJumlah = new GridBagConstraints();
		gbc_lblJumlah.anchor = GridBagConstraints.EAST;
		gbc_lblJumlah.gridwidth = 3;
		gbc_lblJumlah.insets = new Insets(0, 0, 5, 5);
		gbc_lblJumlah.gridx = 3;
		gbc_lblJumlah.gridy = 5;
		contentPane.add(lblJumlah, gbc_lblJumlah);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{2, 0, 2, 0};
		gbl_panel.rowHeights = new int[]{2, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnTambahProduk = new JButton("Tambah Produk");
		btnTambahProduk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProduk();
			}
		});
		btnTambahProduk.setForeground(SystemColor.text);
		btnTambahProduk.setBackground(new Color(0, 120, 215));
		GridBagConstraints gbc_btnTambahProduk = new GridBagConstraints();
		gbc_btnTambahProduk.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTambahProduk.insets = new Insets(0, 0, 5, 5);
		gbc_btnTambahProduk.gridx = 1;
		gbc_btnTambahProduk.gridy = 7;
		contentPane.add(btnTambahProduk, gbc_btnTambahProduk);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewDashboard.back();
				setVisible(false);
			}
		});
		btnKembali.setForeground(Color.WHITE);
		btnKembali.setBackground(new Color(255, 69, 0));
		GridBagConstraints gbc_btnKembali = new GridBagConstraints();
		gbc_btnKembali.gridwidth = 2;
		gbc_btnKembali.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnKembali.insets = new Insets(0, 0, 5, 5);
		gbc_btnKembali.gridx = 4;
		gbc_btnKembali.gridy = 7;
		contentPane.add(btnKembali, gbc_btnKembali);
	}
	
	public DataProduk(Dashboard dashboard) {
		this();
		
		viewDashboard = dashboard;
		produkModel = viewDashboard.getProdukModel();
		supplierModel = viewDashboard.getSupplierModel();
	}
	
	public void showProdukList(String[][] produk) {
		
		this.panel.removeAll();
		
		for (JPanel panel : produkListPanel) {
			panel.setVisible(false);
		}
		
		for (int i=0; i<produk.length; i++) {
			// panel container
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
			gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.5, 0.5, 0.5, 0.0, 0.0};
			gbl_panel_1.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0, 0.0};
			panel_1.setLayout(gbl_panel_1);
			
			JLabel lblKodeProduk = new JLabel(produk[i][0]);
			lblKodeProduk.setFont(new Font("Consolas", Font.PLAIN, 11));
			GridBagConstraints gbc_lblKodeProduk = new GridBagConstraints();
			gbc_lblKodeProduk.anchor = GridBagConstraints.WEST;
			gbc_lblKodeProduk.insets = new Insets(0, 0, 5, 5);
			gbc_lblKodeProduk.gridx = 1;
			gbc_lblKodeProduk.gridy = 1;
			panel_1.add(lblKodeProduk, gbc_lblKodeProduk);
			
			String nama_produk = produk[i][1];
			nama_produk = nama_produk.length() > 30 ? nama_produk.substring(0, 27) + "..." : nama_produk;
			
			JLabel lblNamaProduk = new JLabel(nama_produk);
			lblNamaProduk.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblNamaProduk = new GridBagConstraints();
			gbc_lblNamaProduk.anchor = GridBagConstraints.WEST;
			gbc_lblNamaProduk.insets = new Insets(0, 0, 5, 5);
			gbc_lblNamaProduk.gridx = 1;
			gbc_lblNamaProduk.gridy = 2;
			panel_1.add(lblNamaProduk, gbc_lblNamaProduk);
			
			JButton btnEdit = new JButton("Edit");
			btnEdit.setForeground(new Color(255,255,255));
			btnEdit.setBackground(new Color(0,120,215));
			GridBagConstraints gbc_btnEdit = new GridBagConstraints();
			gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnEdit.gridheight = 1;
			gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
			gbc_btnEdit.gridx = 5;
			gbc_btnEdit.gridy = 1;
			panel_1.add(btnEdit, gbc_btnEdit);
			
			String kode_produk = produk[i][0];
			
			btnEdit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					editProduk(kode_produk);
				}
			});
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.setForeground(new Color(255,255,255));
			btnDelete.setBackground(new Color(255,69,0));
			GridBagConstraints gbc_btnDelete = new GridBagConstraints();
			gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDelete.gridheight = 1;
			gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
			gbc_btnDelete.gridx = 5;
			gbc_btnDelete.gridy = 2;
			panel_1.add(btnDelete, gbc_btnDelete);
			
			btnDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					produkModel.deleteProduk(kode_produk);
					refresh();
				}
			});
			
			produkListPanel.add(panel_1);
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public void showAllProduk() {
		String[][] produk = produkModel.getAllProduk();
		
		showProdukList(produk);
	}

	public void searchProduk(String query) {
		String[][] searchResult = produkModel.searchProdukByNama(query);
		
		showProdukList(searchResult);
	}
		
	public void viewShow() {
		refresh();
		
		this.setVisible(true);
		this.viewDashboard.getFrame().setVisible(false);
	}
	
	private void addProduk() {
		FormProduk formProduk = new FormProduk();
		
		formProduk.viewShow(this);
	}
	
	private void editProduk(String kode_produk) {
		ProdukData produkData = new ProdukData(produkModel, kode_produk);
		
		FormProduk formProduk = new FormProduk();
		formProduk.viewShow(this);
		formProduk.tampilkanDataProduk(produkData);
		
	}
	
	public void refresh() {
		showAllProduk();
		showProdukCount();
	}

	public Produk getProdukModel() {
		return produkModel;
	}

	public Supplier getSupplierModel() {
		return supplierModel;
	}
	
	public void showProdukCount() {
		int jml = produkModel.getProdukCount();
		
		lblJumlah.setText("Jumlah: " + jml);
	}
}
