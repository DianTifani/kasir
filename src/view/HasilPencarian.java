package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Produk;
import model.data.ProdukData;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class HasilPencarian extends JFrame {

	private JPanel contentPane;
	private JTextField tfQueryPencarian;
	private JPanel panel;
	
	private ArrayList<JPanel> searchListPanel = new ArrayList<JPanel>();
	
	private Penjualan fromPenjualan = null;
	private Pembelian fromPembelian = null;

	private Produk produkModel;
	
	private int kode_supplier;

	/**
	 * Create the frame.
	 */
	public HasilPencarian() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 300, 100, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 150, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPencarian = new JLabel("Pencarian Barang");
		lblPencarian.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPencarian = new GridBagConstraints();
		gbc_lblPencarian.anchor = GridBagConstraints.WEST;
		gbc_lblPencarian.insets = new Insets(0, 0, 5, 5);
		gbc_lblPencarian.gridx = 1;
		gbc_lblPencarian.gridy = 1;
		contentPane.add(lblPencarian, gbc_lblPencarian);
		
		tfQueryPencarian = new JTextField();
		GridBagConstraints gbc_tfQueryPencarian = new GridBagConstraints();
		gbc_tfQueryPencarian.gridwidth = 2;
		gbc_tfQueryPencarian.insets = new Insets(0, 0, 5, 5);
		gbc_tfQueryPencarian.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQueryPencarian.gridx = 1;
		gbc_tfQueryPencarian.gridy = 2;
		contentPane.add(tfQueryPencarian, gbc_tfQueryPencarian);
		tfQueryPencarian.setColumns(10);
		
		JButton btnCari = new JButton("Cari");
		btnCari.setBackground(SystemColor.control);
		
		GridBagConstraints gbc_btnCari = new GridBagConstraints();
		gbc_btnCari.insets = new Insets(0, 0, 5, 5);
		gbc_btnCari.gridx = 3;
		gbc_btnCari.gridy = 2;
		contentPane.add(btnCari, gbc_btnCari);
		
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cari();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {2, 0, 2, 0};
		gbl_panel.rowHeights = new int[]{2, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
	}
	
	public HasilPencarian(Dashboard viewDashboard) {
		this();
		this.produkModel = viewDashboard.getProdukModel();
	}
	
	public HasilPencarian(int kode_supplier) {
		this();
		this.kode_supplier = kode_supplier;
	}
	
	public void cari(String query_nama) {
		this.tfQueryPencarian.setText(query_nama);
		cari();
	}
	
	public void viewShow(Penjualan from) {
		this.setVisible(true);
		this.fromPenjualan = from;
		this.fromPembelian = null;
	}
	
	public void viewShow(Pembelian from) {
		this.setVisible(true);
		this.fromPembelian = from;
		this.fromPenjualan = null;
	}
	
	public void cari() {
		String query_pencarian = tfQueryPencarian.getText();
		
		this.panel.removeAll();
		
		for (JPanel panel : searchListPanel) {
			panel.setVisible(false);
		}
		
		if (query_pencarian.length() > 0) {
			String[][] hasil_pencarian = null;
			
			if (fromPenjualan != null) {
				hasil_pencarian = produkModel.searchProdukByNama(query_pencarian);
			} else if (fromPembelian != null) {
				hasil_pencarian = produkModel.searchProdukByNama(query_pencarian, kode_supplier);
			}
			
			for (int i=0; i<hasil_pencarian.length; i++) {
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
				
				JLabel lblKodeProduk = new JLabel(hasil_pencarian[i][0]);
				lblKodeProduk.setFont(new Font("Consolas", Font.PLAIN, 11));
				GridBagConstraints gbc_lblKodeProduk = new GridBagConstraints();
				gbc_lblKodeProduk.anchor = GridBagConstraints.WEST;
				gbc_lblKodeProduk.insets = new Insets(0, 0, 5, 5);
				gbc_lblKodeProduk.gridx = 1;
				gbc_lblKodeProduk.gridy = 1;
				panel_1.add(lblKodeProduk, gbc_lblKodeProduk);
				
				String nama_produk = hasil_pencarian[i][1];
				nama_produk = nama_produk.length() > 30 ? nama_produk.substring(0, 27) + "..." : nama_produk;
				
				JLabel lblNamaProduk = new JLabel(nama_produk);
				lblNamaProduk.setFont(new Font("Tahoma", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNamaProduk = new GridBagConstraints();
				gbc_lblNamaProduk.anchor = GridBagConstraints.WEST;
				gbc_lblNamaProduk.insets = new Insets(0, 0, 5, 5);
				gbc_lblNamaProduk.gridx = 1;
				gbc_lblNamaProduk.gridy = 2;
				panel_1.add(lblNamaProduk, gbc_lblNamaProduk);
				
				JLabel lblHargaSatuan = new JLabel(hasil_pencarian[i][3]);
				GridBagConstraints gbc_lblHargaSatuan = new GridBagConstraints();
				gbc_lblHargaSatuan.gridheight = 2;
				gbc_lblHargaSatuan.insets = new Insets(0, 0, 5, 5);
				gbc_lblHargaSatuan.gridx = 4;
				gbc_lblHargaSatuan.gridy = 1;
				panel_1.add(lblHargaSatuan, gbc_lblHargaSatuan);
				
				JButton btnPilih = new JButton("Pilih");
				btnPilih.setForeground(new Color(255, 255, 255));
				btnPilih.setBackground(new Color(255, 69, 0));
				GridBagConstraints gbc_btnPilih = new GridBagConstraints();
				gbc_btnPilih.gridheight = 2;
				gbc_btnPilih.insets = new Insets(0, 0, 5, 5);
				gbc_btnPilih.gridx = 5;
				gbc_btnPilih.gridy = 1;
				panel_1.add(btnPilih, gbc_btnPilih);
				
				ProdukData pd = new ProdukData(produkModel, hasil_pencarian[i][0]);
				
				HasilPencarian self = this;
				
				btnPilih.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if (fromPembelian != null || fromPenjualan != null) {
							if (fromPenjualan != null) {
								fromPenjualan.produkDipilih(pd);								
							} else if(fromPembelian != null) {
								if (pd.getKode_supplier() != kode_supplier) return; 
								
								fromPembelian.produkDipilih(pd);
							}
						}
						
						self.setVisible(false);
					}
				});
				
				searchListPanel.add(panel_1);				
			}
			
			contentPane.revalidate();
		}
	}

	public void setProdukModel(Produk produkModel) {
		this.produkModel = produkModel;
	}
}