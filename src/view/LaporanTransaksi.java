package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.Transaksi;
import model.DataTransaksi;
import javax.swing.JTabbedPane;

public class LaporanTransaksi extends JFrame {

	private JPanel contentPane;
	private Dashboard viewDashboard;
	private JPanel panelPenjualan;
	private JPanel panelPenjualanTrxList;
	private JPanel panelPembelian;
	private JPanel panelPembelianTrxList;
	
	private JDatePickerImpl datePicker;
	private JDatePickerImpl datePicker2;
	
	private Transaksi transaksiModel;
	private DataTransaksi dataTransaksiModel;
	
	private ArrayList<JPanel> pembelianPanelList = new ArrayList<JPanel>();
	private ArrayList<JPanel> penjualanPanelList = new ArrayList<JPanel>();
	
	private view.DataTransaksi viewDataTransaksi;

	/**
	 * Create the frame.
	 */
	public LaporanTransaksi() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 200, 5, 200, 25, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 15, 0, 15, 15, 0, 300, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLaporanTransaksi = new JLabel("Laporan Transaksi");
		lblLaporanTransaksi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblLaporanTransaksi = new GridBagConstraints();
		gbc_lblLaporanTransaksi.anchor = GridBagConstraints.WEST;
		gbc_lblLaporanTransaksi.insets = new Insets(0, 0, 5, 5);
		gbc_lblLaporanTransaksi.gridx = 1;
		gbc_lblLaporanTransaksi.gridy = 1;
		contentPane.add(lblLaporanTransaksi, gbc_lblLaporanTransaksi);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		Date date = cal.getTime();
		UtilDateModel model = new UtilDateModel();
		model.setValue(date);
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.insets = new Insets(0, 0, 5, 5);
		gbc_datePicker.gridx = 1;
		gbc_datePicker.gridy = 3;
		contentPane.add(datePicker, gbc_datePicker);
		
		JLabel lblSampai = new JLabel("sampai");
		GridBagConstraints gbc_lblSampai = new GridBagConstraints();
		gbc_lblSampai.insets = new Insets(0, 0, 5, 5);
		gbc_lblSampai.gridx = 2;
		gbc_lblSampai.gridy = 3;
		contentPane.add(lblSampai, gbc_lblSampai);
		
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, 1);
		
		Date date2 = cal.getTime();
		UtilDateModel model2 = new UtilDateModel();
		model2.setValue(date2);
        model2.setSelected(true);
		
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		GridBagConstraints gbc_datePicker2 = new GridBagConstraints();
		gbc_datePicker2.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker2.insets = new Insets(0, 0, 5, 5);
		gbc_datePicker2.gridx = 3;
		gbc_datePicker2.gridy = 3;
		contentPane.add(datePicker2, gbc_datePicker2);
		
		JButton btnTampilkan = new JButton("Tampilkan");
		btnTampilkan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTransaksiBetween();
			}
		});
		btnTampilkan.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnTampilkan = new GridBagConstraints();
		gbc_btnTampilkan.gridwidth = 2;
		gbc_btnTampilkan.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTampilkan.insets = new Insets(0, 0, 5, 5);
		gbc_btnTampilkan.gridx = 4;
		gbc_btnTampilkan.gridy = 3;
		contentPane.add(btnTampilkan, gbc_btnTampilkan);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 3;
		gbc_tabbedPane.gridwidth = 5;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 5;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		panelPenjualan = new JPanel();
		tabbedPane.addTab("Penjualan", null, panelPenjualan, null);
		GridBagLayout gbl_panelPenjualan = new GridBagLayout();
		gbl_panelPenjualan.columnWidths = new int[]{15, 0, 15, 0};
		gbl_panelPenjualan.rowHeights = new int[]{5, 0, 5, 0, 10, 0};
		gbl_panelPenjualan.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPenjualan.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelPenjualan.setLayout(gbl_panelPenjualan);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panelPenjualan.add(scrollPane, gbc_scrollPane);
		
		panelPenjualanTrxList = new JPanel();
		scrollPane.setViewportView(panelPenjualanTrxList);
		GridBagLayout gbl_panelPenjualanTrxList = new GridBagLayout();
		gbl_panelPenjualanTrxList.columnWidths = new int[]{2, 0, 2, 0};
		gbl_panelPenjualanTrxList.rowHeights = new int[]{2, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPenjualanTrxList.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPenjualanTrxList.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPenjualanTrxList.setLayout(gbl_panelPenjualanTrxList);
		
		panelPembelian = new JPanel();
		tabbedPane.addTab("Pembelian", null, panelPembelian, null);
		GridBagLayout gbl_panelPembelian = new GridBagLayout();
		gbl_panelPembelian.columnWidths = new int[]{15, 0, 15, 0};
		gbl_panelPembelian.rowHeights = new int[]{10, 0, 5, 0, 10, 0};
		gbl_panelPembelian.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPembelian.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelPembelian.setLayout(gbl_panelPembelian);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		panelPembelian.add(scrollPane_1, gbc_scrollPane_1);
		
		panelPembelianTrxList = new JPanel();
		scrollPane_1.setViewportView(panelPembelianTrxList);
		GridBagLayout gbl_panelPembelianTrxList = new GridBagLayout();
		gbl_panelPembelianTrxList.columnWidths = new int[]{2, 0, 2, 0};
		gbl_panelPembelianTrxList.rowHeights = new int[]{2, 50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPembelianTrxList.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPembelianTrxList.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPembelianTrxList.setLayout(gbl_panelPembelianTrxList);
		
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
		gbc_btnKembali.gridy = 8;
		contentPane.add(btnKembali, gbc_btnKembali);
	}
	
	public LaporanTransaksi(Dashboard dashboard) {
		this();
		
		viewDashboard = dashboard;
		transaksiModel = viewDashboard.getTransaksiModel();
		dataTransaksiModel = viewDashboard.getDataTransaksiModel();
		viewDataTransaksi = new view.DataTransaksi(transaksiModel, dataTransaksiModel);
	}
	
	public void listGenerator(int pos, JPanel panel, String kode_transaksi, String waktu_trx, String nama_kasir, String qty, String nilai_transaksi, ArrayList<JPanel> panelList) {
		// panel container
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(5, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1 + pos;
		panel.add(panel_1, gbc_panel_1);
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 30, 50, 25, 25, 0, 5};
		gbl_panel_1.rowHeights = new int[]{5, 10, 10, 5};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.5, 0.5, 0.5, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		// no_telp
		JLabel lblWaktuTrx = new JLabel(waktu_trx);
		lblWaktuTrx.setFont(new Font("Consolas", Font.PLAIN, 11));
		GridBagConstraints gbc_lblWaktuTrx = new GridBagConstraints();
		gbc_lblWaktuTrx.anchor = GridBagConstraints.WEST;
		gbc_lblWaktuTrx.insets = new Insets(0, 0, 5, 5);
		gbc_lblWaktuTrx.gridx = 1;
		gbc_lblWaktuTrx.gridy = 1;
		panel_1.add(lblWaktuTrx, gbc_lblWaktuTrx);
		
		JLabel lblNamaKasir = new JLabel(nama_kasir);
		lblNamaKasir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNamaKasir = new GridBagConstraints();
		gbc_lblNamaKasir.anchor = GridBagConstraints.WEST;
		gbc_lblNamaKasir.insets = new Insets(0, 0, 5, 5);
		gbc_lblNamaKasir.gridx = 1;
		gbc_lblNamaKasir.gridy = 2;
		panel_1.add(lblNamaKasir, gbc_lblNamaKasir);
		
		JLabel lblQty = new JLabel(qty);
		GridBagConstraints gbc_lblQty = new GridBagConstraints();
		gbc_lblQty.anchor = GridBagConstraints.CENTER;
		gbc_lblQty.gridheight = 2;
		gbc_lblQty.insets = new Insets(0, 0, 5, 5);
		gbc_lblQty.gridx = 3;
		gbc_lblQty.gridy = 1;
		panel_1.add(lblQty, gbc_lblQty);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		
		JLabel lblNilaiTransaksi = new JLabel(format.format(Double.valueOf(nilai_transaksi)));
		GridBagConstraints gbc_lblNilaiTransaksi = new GridBagConstraints();
		gbc_lblNilaiTransaksi.gridheight = 2;
		gbc_lblNilaiTransaksi.insets = new Insets(0, 0, 5, 5);
		gbc_lblNilaiTransaksi.gridx = 4;
		gbc_lblNilaiTransaksi.gridy = 1;
		panel_1.add(lblNilaiTransaksi, gbc_lblNilaiTransaksi);
		
		JButton btnDetailTrx = new JButton("Lihat Detail");
		btnDetailTrx.setForeground(new Color(255,255,255));
		btnDetailTrx.setBackground(new Color(0,120,215));
		GridBagConstraints gbc_btnDetailTrx = new GridBagConstraints();
		gbc_btnDetailTrx.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetailTrx.gridheight = 1;
		gbc_btnDetailTrx.insets = new Insets(0,0,5,5);
		gbc_btnDetailTrx.gridx = 5;
		gbc_btnDetailTrx.gridy = 1;
		panel_1.add(btnDetailTrx, gbc_btnDetailTrx);
		
		btnDetailTrx.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewDataTransaksi.viewShow(kode_transaksi);
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
				
				transaksiModel.deleteTransaksi(kode_transaksi);
				refresh();
			}
		});
		
		panelList.add(panel_1);
	}
	
	public void showAllPembelian() {
		String[][] pembelian = transaksiModel.getAllTransaksiPembelian();
		
		showPembelian(pembelian);
	}
	
	public void showPembelian(String[][] pembelian) {
		this.panelPembelianTrxList.removeAll();
		
		for (JPanel panel : pembelianPanelList) {
			panel.setVisible(false);
		}
		
		for (int i=0; i<pembelian.length; i++) {
			listGenerator(i, panelPembelianTrxList, pembelian[i][0], pembelian[i][1], pembelian[i][3], pembelian[i][6], pembelian[i][7], pembelianPanelList);
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public void showAllPenjualan() {
		String[][] penjualan = transaksiModel.getAllTransaksiPenjualan();
		
		showPenjualan(penjualan);
	}
	
	public void showPenjualan(String[][] penjualan) {
		this.panelPenjualanTrxList.removeAll();
		
		for (JPanel panel : penjualanPanelList) {
			panel.setVisible(false);
		}
		
		for (int i=0; i<penjualan.length; i++) {
			listGenerator(i, panelPenjualanTrxList, penjualan[i][0], penjualan[i][1], penjualan[i][3], penjualan[i][4], penjualan[i][5], penjualanPanelList);
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public void searchTransaksi(Date from, Date to) {
		String[][] pembelian = transaksiModel.getAllPembelianBetween(from, to);
		String[][] penjualan = transaksiModel.getAllPenjualanBetween(from, to);
		
		showPembelian(pembelian);
		showPenjualan(penjualan);
	}
	
	public void showTransaksiBetween() {
		Date from = (Date) datePicker.getModel().getValue();
		Date to = (Date) datePicker2.getModel().getValue();
		
		searchTransaksi(from, to);
	}
		
	public void viewShow() {
		refresh();
		
		this.setVisible(true);
		this.viewDashboard.getFrame().setVisible(false);
	}

	public void refresh() {
		showTransaksiBetween();
	}
}

class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            Calendar today = Calendar.getInstance();
            today.add(Calendar.DATE, 1);
            
            if (cal.getTime().after(today.getTime())) return dateFormatter.format(today.getTime());
            
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
