package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Transaksi;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DataTransaksi extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblJenisTrx;
	private JLabel lblWaktuTrx;
	private JLabel lblKasirTrx;
	
	private Transaksi transaksiModel;
	private model.DataTransaksi dataTransaksiModel;

	/**
	 * Create the frame.
	 */
	private DataTransaksi() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{25, 0, 15, 0, 100, 150, 25, 0};
		gbl_contentPane.rowHeights = new int[]{25, 0, 15, 0, 0, 0, 15, 200, 25, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDataTransaksi = new JLabel("Data Transaksi");
		lblDataTransaksi.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblDataTransaksi = new GridBagConstraints();
		gbc_lblDataTransaksi.anchor = GridBagConstraints.WEST;
		gbc_lblDataTransaksi.gridwidth = 4;
		gbc_lblDataTransaksi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataTransaksi.gridx = 1;
		gbc_lblDataTransaksi.gridy = 1;
		contentPane.add(lblDataTransaksi, gbc_lblDataTransaksi);
		
		JLabel lblJenis = new JLabel("Jenis");
		lblJenis.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblJenis = new GridBagConstraints();
		gbc_lblJenis.anchor = GridBagConstraints.WEST;
		gbc_lblJenis.insets = new Insets(0, 0, 5, 5);
		gbc_lblJenis.gridx = 1;
		gbc_lblJenis.gridy = 3;
		contentPane.add(lblJenis, gbc_lblJenis);
		
		lblJenisTrx = new JLabel("");
		lblJenisTrx.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblJenisTrx = new GridBagConstraints();
		gbc_lblJenisTrx.anchor = GridBagConstraints.WEST;
		gbc_lblJenisTrx.gridwidth = 2;
		gbc_lblJenisTrx.insets = new Insets(0, 0, 5, 5);
		gbc_lblJenisTrx.gridx = 3;
		gbc_lblJenisTrx.gridy = 3;
		contentPane.add(lblJenisTrx, gbc_lblJenisTrx);
		
		JLabel lblWaktu = new JLabel("Waktu");
		lblWaktu.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWaktu = new GridBagConstraints();
		gbc_lblWaktu.gridwidth = 2;
		gbc_lblWaktu.anchor = GridBagConstraints.WEST;
		gbc_lblWaktu.insets = new Insets(0, 0, 5, 5);
		gbc_lblWaktu.gridx = 1;
		gbc_lblWaktu.gridy = 4;
		contentPane.add(lblWaktu, gbc_lblWaktu);
		
		lblWaktuTrx = new JLabel("");
		lblWaktuTrx.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWaktuTrx = new GridBagConstraints();
		gbc_lblWaktuTrx.anchor = GridBagConstraints.WEST;
		gbc_lblWaktuTrx.gridwidth = 2;
		gbc_lblWaktuTrx.insets = new Insets(0, 0, 5, 5);
		gbc_lblWaktuTrx.gridx = 3;
		gbc_lblWaktuTrx.gridy = 4;
		contentPane.add(lblWaktuTrx, gbc_lblWaktuTrx);
		
		JLabel lblKasir = new JLabel("Kasir");
		lblKasir.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblKasir = new GridBagConstraints();
		gbc_lblKasir.anchor = GridBagConstraints.WEST;
		gbc_lblKasir.insets = new Insets(0, 0, 5, 5);
		gbc_lblKasir.gridx = 1;
		gbc_lblKasir.gridy = 5;
		contentPane.add(lblKasir, gbc_lblKasir);
		
		lblKasirTrx = new JLabel("");
		lblKasirTrx.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblKasirTrx = new GridBagConstraints();
		gbc_lblKasirTrx.anchor = GridBagConstraints.WEST;
		gbc_lblKasirTrx.insets = new Insets(0, 0, 5, 5);
		gbc_lblKasirTrx.gridx = 3;
		gbc_lblKasirTrx.gridy = 5;
		contentPane.add(lblKasirTrx, gbc_lblKasirTrx);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 7;
		contentPane.add(scrollPane, gbc_scrollPane);
	}
	
	public DataTransaksi(Transaksi transaksiModel, model.DataTransaksi dataTransaksiModel) {
		this();
		this.transaksiModel = transaksiModel;
		this.dataTransaksiModel = dataTransaksiModel;
	}
	
	public void viewShow(String kode_transaksi) {
		String[][] transaksi = transaksiModel.getTransaksi(kode_transaksi);
		
		String jenisTrx = transaksi[0][5] == null ? "Penjualan" : "Pembelian";
		lblJenisTrx.setText(jenisTrx);
		lblWaktuTrx.setText(transaksi[0][1]);
		lblKasirTrx.setText(transaksi[0][3]);
		
		String[][] data = dataTransaksiModel.getDetailTransaksi(kode_transaksi);
		
		String[] columnNames = {"Kode Produk", "Nama Produk", "Qty", "Subtotal"};
		
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		
		this.repaint();
		this.setVisible(true);
	}
}
