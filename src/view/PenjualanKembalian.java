package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PenjualanKembalian extends JFrame {

	private JPanel contentPane;
	private JLabel lblKembalian; 
	
	private Penjualan fromPenjualan;

	/**
	 * Create the frame.
	 */
	public PenjualanKembalian() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 30, 0, 30, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 15, 0, 0, 25, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblKembalian = new JLabel("Rp. 55.150");
		lblKembalian.setForeground(Color.RED);
		lblKembalian.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_lblKembalian = new GridBagConstraints();
		gbc_lblKembalian.gridwidth = 3;
		gbc_lblKembalian.insets = new Insets(0, 0, 5, 5);
		gbc_lblKembalian.gridx = 1;
		gbc_lblKembalian.gridy = 2;
		contentPane.add(lblKembalian, gbc_lblKembalian);
		
		JLabel lblTerimaKasih = new JLabel("Ucapkan Terima Kasih, kemudian tekan tombol");
		lblTerimaKasih.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTerimaKasih = new GridBagConstraints();
		gbc_lblTerimaKasih.gridwidth = 3;
		gbc_lblTerimaKasih.insets = new Insets(0, 0, 5, 5);
		gbc_lblTerimaKasih.gridx = 1;
		gbc_lblTerimaKasih.gridy = 4;
		contentPane.add(lblTerimaKasih, gbc_lblTerimaKasih);
		
		JLabel lblThxLanjutan = new JLabel("Selesai untuk menyimpan transaksi");
		lblThxLanjutan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblThxLanjutan = new GridBagConstraints();
		gbc_lblThxLanjutan.gridwidth = 3;
		gbc_lblThxLanjutan.insets = new Insets(0, 0, 5, 5);
		gbc_lblThxLanjutan.gridx = 1;
		gbc_lblThxLanjutan.gridy = 5;
		contentPane.add(lblThxLanjutan, gbc_lblThxLanjutan);
		
		PenjualanKembalian self = this;
		
		JButton btnSelesai = new JButton("Selesai");
		btnSelesai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				
				fromPenjualan.simpanTransaksi();
			}
		});
		btnSelesai.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnSelesai = new GridBagConstraints();
		gbc_btnSelesai.anchor = GridBagConstraints.EAST;
		gbc_btnSelesai.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelesai.gridx = 3;
		gbc_btnSelesai.gridy = 7;
		contentPane.add(btnSelesai, gbc_btnSelesai);
	}
	
	public void tampilkanKembalian(long kembalian) {
		lblKembalian.setText(String.valueOf(kembalian));
	}
	
	public void viewShow(Penjualan from) {
		this.setVisible(true);
		this.fromPenjualan = from;
	}

}
