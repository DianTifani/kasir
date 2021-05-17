package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Produk;
import model.Supplier;
import model.data.ProdukData;
import java.awt.Color;
import javax.swing.JComboBox;

public class FormProduk extends JFrame {

	private JPanel contentPane;
	
	private JLabel lblHargaSatuanError;
	private JLabel lblKodeProduk;
	private JLabel lblSupplier;
	private JLabel lblError;
	private JTextField tfNamaProduk;
	private JTextField tfSatuanProduk;
	private JTextField tfHargaSatuanProduk;
	private JTextField tfKodeProduk;
	private JComboBox<String> cbSupplier;
	
	private Produk produkModel;
	private Supplier supplierModel;
	
	private ProdukData produkData;
	
	private DataProduk viewDataProduk = null;
	
	private String[][] supplierList;

	/**
	 * Create the frame.
	 */
	public FormProduk() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 150, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 25, 0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDataProduk = new JLabel("Produk");
		lblDataProduk.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblDataProduk = new GridBagConstraints();
		gbc_lblDataProduk.anchor = GridBagConstraints.WEST;
		gbc_lblDataProduk.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataProduk.gridx = 1;
		gbc_lblDataProduk.gridy = 1;
		contentPane.add(lblDataProduk, gbc_lblDataProduk);
		
		lblKodeProduk = new JLabel("Kode Produk");
		GridBagConstraints gbc_lblKodeProduk = new GridBagConstraints();
		gbc_lblKodeProduk.anchor = GridBagConstraints.WEST;
		gbc_lblKodeProduk.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodeProduk.gridx = 1;
		gbc_lblKodeProduk.gridy = 3;
		contentPane.add(lblKodeProduk, gbc_lblKodeProduk);
		
		tfKodeProduk = new JTextField();
		GridBagConstraints gbc_tfKodeProduk = new GridBagConstraints();
		gbc_tfKodeProduk.gridwidth = 5;
		gbc_tfKodeProduk.insets = new Insets(0, 0, 5, 5);
		gbc_tfKodeProduk.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKodeProduk.gridx = 1;
		gbc_tfKodeProduk.gridy = 4;
		contentPane.add(tfKodeProduk, gbc_tfKodeProduk);
		tfKodeProduk.setColumns(10);
		
		JLabel lblNamaProduk = new JLabel("Nama Produk");
		GridBagConstraints gbc_lblNamaProduk = new GridBagConstraints();
		gbc_lblNamaProduk.anchor = GridBagConstraints.WEST;
		gbc_lblNamaProduk.gridwidth = 2;
		gbc_lblNamaProduk.insets = new Insets(0, 0, 5, 5);
		gbc_lblNamaProduk.gridx = 1;
		gbc_lblNamaProduk.gridy = 6;
		contentPane.add(lblNamaProduk, gbc_lblNamaProduk);
		
		tfNamaProduk = new JTextField();
		GridBagConstraints gbc_tfNamaProduk = new GridBagConstraints();
		gbc_tfNamaProduk.gridwidth = 5;
		gbc_tfNamaProduk.insets = new Insets(0, 0, 5, 5);
		gbc_tfNamaProduk.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNamaProduk.gridx = 1;
		gbc_tfNamaProduk.gridy = 7;
		contentPane.add(tfNamaProduk, gbc_tfNamaProduk);
		tfNamaProduk.setColumns(10);
		
		JLabel lblSatuanProduk = new JLabel("Satuan");
		GridBagConstraints gbc_lblSatuanProduk = new GridBagConstraints();
		gbc_lblSatuanProduk.anchor = GridBagConstraints.WEST;
		gbc_lblSatuanProduk.gridwidth = 2;
		gbc_lblSatuanProduk.insets = new Insets(0, 0, 5, 5);
		gbc_lblSatuanProduk.gridx = 1;
		gbc_lblSatuanProduk.gridy = 9;
		contentPane.add(lblSatuanProduk, gbc_lblSatuanProduk);
		
		tfSatuanProduk = new JTextField();
		GridBagConstraints gbc_tfSatuanProduk = new GridBagConstraints();
		gbc_tfSatuanProduk.gridwidth = 5;
		gbc_tfSatuanProduk.insets = new Insets(0, 0, 5, 5);
		gbc_tfSatuanProduk.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSatuanProduk.gridx = 1;
		gbc_tfSatuanProduk.gridy = 10;
		contentPane.add(tfSatuanProduk, gbc_tfSatuanProduk);
		tfSatuanProduk.setColumns(10);
		
		JLabel lblHargaSatuanProduk = new JLabel("Harga Satuan");
		GridBagConstraints gbc_lblHargaSatuanProduk = new GridBagConstraints();
		gbc_lblHargaSatuanProduk.anchor = GridBagConstraints.WEST;
		gbc_lblHargaSatuanProduk.insets = new Insets(0, 0, 5, 5);
		gbc_lblHargaSatuanProduk.gridx = 1;
		gbc_lblHargaSatuanProduk.gridy = 12;
		contentPane.add(lblHargaSatuanProduk, gbc_lblHargaSatuanProduk);
		
		tfHargaSatuanProduk = new JTextField();
		GridBagConstraints gbc_tfHargaSatuanProduk = new GridBagConstraints();
		gbc_tfHargaSatuanProduk.gridwidth = 5;
		gbc_tfHargaSatuanProduk.insets = new Insets(0, 0, 5, 5);
		gbc_tfHargaSatuanProduk.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfHargaSatuanProduk.gridx = 1;
		gbc_tfHargaSatuanProduk.gridy = 13;
		contentPane.add(tfHargaSatuanProduk, gbc_tfHargaSatuanProduk);
		tfHargaSatuanProduk.setColumns(10);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( simpanDataProduk() ) {					
					close();
				}
			}
		});
		
		lblHargaSatuanError = new JLabel("Harga satuan harus berupa angka");
		lblHargaSatuanError.setForeground(Color.RED);
		GridBagConstraints gbc_lblHargaSatuanError = new GridBagConstraints();
		gbc_lblHargaSatuanError.anchor = GridBagConstraints.WEST;
		gbc_lblHargaSatuanError.gridwidth = 4;
		gbc_lblHargaSatuanError.insets = new Insets(0, 0, 5, 5);
		gbc_lblHargaSatuanError.gridx = 1;
		gbc_lblHargaSatuanError.gridy = 14;
		contentPane.add(lblHargaSatuanError, gbc_lblHargaSatuanError);
		
		lblHargaSatuanError.setVisible(false);
		
		lblSupplier = new JLabel("Supplier");
		GridBagConstraints gbc_lblSupplier = new GridBagConstraints();
		gbc_lblSupplier.anchor = GridBagConstraints.WEST;
		gbc_lblSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplier.gridx = 1;
		gbc_lblSupplier.gridy = 16;
		contentPane.add(lblSupplier, gbc_lblSupplier);
		
		cbSupplier = new JComboBox<String>();
		cbSupplier.setBackground(SystemColor.text);
		GridBagConstraints gbc_cbSupplier = new GridBagConstraints();
		gbc_cbSupplier.gridwidth = 5;
		gbc_cbSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_cbSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSupplier.gridx = 1;
		gbc_cbSupplier.gridy = 17;
		contentPane.add(cbSupplier, gbc_cbSupplier);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 4;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 18;
		contentPane.add(lblError, gbc_lblError);
		
		btnSimpan.setForeground(SystemColor.text);
		btnSimpan.setBackground(SystemColor.textHighlight);
		GridBagConstraints gbc_btnSimpan = new GridBagConstraints();
		gbc_btnSimpan.insets = new Insets(0, 0, 5, 5);
		gbc_btnSimpan.gridx = 5;
		gbc_btnSimpan.gridy = 19;
		contentPane.add(btnSimpan, gbc_btnSimpan);
	}
	
	public void tampilkanDataProduk(ProdukData produkData) {
		this.produkData = produkData;
		this.produkModel = produkData.getProdukModel();
		
		tfKodeProduk.setText(produkData.getKode_produk());
		tfKodeProduk.setEnabled(false);
		tfNamaProduk.setText(produkData.getNama_produk());
		tfSatuanProduk.setText(produkData.getSatuan());
		tfHargaSatuanProduk.setText(String.valueOf( produkData.getHarga_satuan() ));
		for (int i=0; i<supplierList.length; i++) {

			if (supplierList[i][1].equals(produkData.getSupplier())) {
				cbSupplier.setSelectedIndex(i);
			}
		}
		
	}
	
	public boolean simpanDataProduk() {
		boolean isError = false;
		
		if (tfKodeProduk.getText().length() == 0) isError = true;
		if (tfNamaProduk.getText().length() == 0) isError = true;
		if (tfSatuanProduk.getText().length() == 0) isError = true;
		if (tfHargaSatuanProduk.getText().length() == 0) isError = true;
		
		if (isError) {
			lblError.setText("Semua data isian form wajib diisi!");
			return false;
		}
		
		try {
			if (produkData != null) {
				// update
				produkData.setNama_produk( tfNamaProduk.getText() );
				produkData.setSatuan( tfSatuanProduk.getText() );
				produkData.setHarga_satuan( Double.valueOf( tfHargaSatuanProduk.getText() ));
				String supplier = String.valueOf( cbSupplier.getSelectedItem() );
				int kode_supplier = Integer.valueOf(getKodeSupplier(supplier));
				produkData.setKode_supplier(kode_supplier);
				
				produkModel.updateProduk(produkData);
			} else {
				// insert
				String kode_produk = tfKodeProduk.getText();
				String nama = tfNamaProduk.getText();
				String satuan = tfSatuanProduk.getText();
				Double harga_satuan = Double.valueOf( tfHargaSatuanProduk.getText() );
				String supplier = String.valueOf( cbSupplier.getSelectedItem() );
				int kode_supplier = Integer.valueOf(getKodeSupplier(supplier));
				
				produkModel.addProduk(kode_produk, nama, satuan, harga_satuan, kode_supplier);
			}			
		} catch (NumberFormatException e) {
			lblHargaSatuanError.setVisible(true);
			return false;
		}
		
		return true;
	}
	
	private void close() {
		this.viewDataProduk.refresh();
		this.setVisible(false);
	}
	
	public void viewShow(DataProduk viewDataProduk) {
		this.viewDataProduk = viewDataProduk;
		this.produkModel = viewDataProduk.getProdukModel();
		this.supplierModel = viewDataProduk.getSupplierModel();
		updateListSupplier();
		this.setVisible(true);
	}

	public void updateListSupplier() {
		cbSupplier.removeAllItems();
		supplierList = this.supplierModel.getAllSupplier();
		
		for (int i=0; i<supplierList.length; i++) {
			cbSupplier.addItem(supplierList[i][1]);
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public String getKodeSupplier(String nama_supplier) {
		for (int i=0; i<supplierList.length; i++) {
			if (supplierList[i][1].equals(nama_supplier)) return supplierList[i][0];
		}
		
		return null;
	}
}
