package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Supplier;
import model.data.SupplierData;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FormSupplier extends JFrame {

	private JPanel contentPane;
	private JTextField tfNamaSupplier;
	private JTextField tfNoTelpSupplier;
	private JTextField tfAlamatSupplier;
	private JLabel lblError;
	
	private SupplierData supplierData = null;
	private Supplier supplierModel;
	
	private DataSupplier viewDataSupplier = null;
	
	/**
	 * Create the frame.
	 */
	public FormSupplier() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 150, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 25, 0, 0, 5, 0, 0, 5, 0, 0, 25, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDataSupplier = new JLabel("Supplier");
		lblDataSupplier.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblDataSupplier = new GridBagConstraints();
		gbc_lblDataSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataSupplier.gridx = 1;
		gbc_lblDataSupplier.gridy = 1;
		contentPane.add(lblDataSupplier, gbc_lblDataSupplier);
		
		JLabel lblNamaSupplier = new JLabel("Nama Supplier");
		GridBagConstraints gbc_lblNamaSupplier = new GridBagConstraints();
		gbc_lblNamaSupplier.anchor = GridBagConstraints.WEST;
		gbc_lblNamaSupplier.gridwidth = 2;
		gbc_lblNamaSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_lblNamaSupplier.gridx = 1;
		gbc_lblNamaSupplier.gridy = 3;
		contentPane.add(lblNamaSupplier, gbc_lblNamaSupplier);
		
		tfNamaSupplier = new JTextField();
		GridBagConstraints gbc_tfNamaSupplier = new GridBagConstraints();
		gbc_tfNamaSupplier.gridwidth = 5;
		gbc_tfNamaSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_tfNamaSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNamaSupplier.gridx = 1;
		gbc_tfNamaSupplier.gridy = 4;
		contentPane.add(tfNamaSupplier, gbc_tfNamaSupplier);
		tfNamaSupplier.setColumns(10);
		
		JLabel lblNoTelepon = new JLabel("No. Telepon");
		GridBagConstraints gbc_lblNoTelepon = new GridBagConstraints();
		gbc_lblNoTelepon.anchor = GridBagConstraints.WEST;
		gbc_lblNoTelepon.gridwidth = 2;
		gbc_lblNoTelepon.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoTelepon.gridx = 1;
		gbc_lblNoTelepon.gridy = 6;
		contentPane.add(lblNoTelepon, gbc_lblNoTelepon);
		
		tfNoTelpSupplier = new JTextField();
		GridBagConstraints gbc_tfNoTelpSupplier = new GridBagConstraints();
		gbc_tfNoTelpSupplier.gridwidth = 5;
		gbc_tfNoTelpSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_tfNoTelpSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNoTelpSupplier.gridx = 1;
		gbc_tfNoTelpSupplier.gridy = 7;
		contentPane.add(tfNoTelpSupplier, gbc_tfNoTelpSupplier);
		tfNoTelpSupplier.setColumns(10);
		
		JLabel lblAlamat = new JLabel("Alamat");
		GridBagConstraints gbc_lblAlamat = new GridBagConstraints();
		gbc_lblAlamat.anchor = GridBagConstraints.WEST;
		gbc_lblAlamat.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlamat.gridx = 1;
		gbc_lblAlamat.gridy = 9;
		contentPane.add(lblAlamat, gbc_lblAlamat);
		
		tfAlamatSupplier = new JTextField();
		GridBagConstraints gbc_tfAlamatSupplier = new GridBagConstraints();
		gbc_tfAlamatSupplier.gridwidth = 5;
		gbc_tfAlamatSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_tfAlamatSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAlamatSupplier.gridx = 1;
		gbc_tfAlamatSupplier.gridy = 10;
		contentPane.add(tfAlamatSupplier, gbc_tfAlamatSupplier);
		tfAlamatSupplier.setColumns(10);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (simpanDataSupplier() ) {
					close();
				}
			}
		});
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 4;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 11;
		contentPane.add(lblError, gbc_lblError);
		btnSimpan.setForeground(SystemColor.text);
		btnSimpan.setBackground(SystemColor.textHighlight);
		GridBagConstraints gbc_btnSimpan = new GridBagConstraints();
		gbc_btnSimpan.insets = new Insets(0, 0, 5, 5);
		gbc_btnSimpan.gridx = 5;
		gbc_btnSimpan.gridy = 12;
		contentPane.add(btnSimpan, gbc_btnSimpan);
	}
	
	public FormSupplier(Supplier supplierModel) {
		this();
		this.supplierModel = supplierModel;
	}
	
	public void tampilkanDataSupplier(SupplierData supplierData) {
		this.supplierData = supplierData;
		this.supplierModel = supplierData.getSupplierModel();
		
		tfNamaSupplier.setText(supplierData.getNama());
		tfNoTelpSupplier.setText(supplierData.getNo_telp());
		tfAlamatSupplier.setText(supplierData.getAlamat());
	}
	
	public boolean simpanDataSupplier() {
		boolean isError = false;
		
		if (tfNamaSupplier.getText().length() == 0) isError = true;
		if (tfNoTelpSupplier.getText().length() == 0) isError = true;
		if (tfAlamatSupplier.getText().length() == 0) isError = true;
		
		if (isError) {
			lblError.setText("Semua data isian form wajib diisi!");
			return false;
		}
		
		if (supplierData != null) {
			// update
			supplierData.setNama( tfNamaSupplier.getText() );
			supplierData.setNo_telp( tfNoTelpSupplier.getText() );
			supplierData.setAlamat( tfAlamatSupplier.getText() );
			
			supplierModel.updateSupplier(supplierData);		
		} else {
			// insert
			String nama = tfNamaSupplier.getText();
			String no_telp = tfNoTelpSupplier.getText();
			String alamat = tfAlamatSupplier.getText();
			
			supplierModel.addSupplier(nama, no_telp, alamat);
		}
		
		return true;
	}
	
	private void close() {
		this.viewDataSupplier.refresh();
		this.setVisible(false);
	}
	
	public void viewShow(DataSupplier viewDataSupplier) {
		this.viewDataSupplier = viewDataSupplier;
		this.setVisible(true);
	}

}
