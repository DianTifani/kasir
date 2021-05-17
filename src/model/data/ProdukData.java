package model.data;

import model.Produk;

public class ProdukData {
	
	private Produk produkModel;
	private String kode_produk;
	private String nama_produk;
	private String satuan;
	private String supplier;
	private int kode_supplier;
	private double harga_satuan;
	private boolean valid;
	private int qty;
	
	public ProdukData(Produk model) {
		this.produkModel = model;
	}
	
	public ProdukData(Produk model, String kode_produk) {
		this(model);
		setData(kode_produk);
	}
	
	public void setData(String kode_produk) {
		String[][] data = produkModel.getProduk(kode_produk);
		
		if (data.length > 0) {
			this.kode_produk = kode_produk;
			this.nama_produk = data[0][1];
			this.satuan = data[0][2];
			this.harga_satuan = Double.valueOf(data[0][3]);
			this.supplier = data[0][4];
			this.kode_supplier = Integer.valueOf(data[0][5]);
			this.valid = true;
		}
		
	}

	public String getSupplier() {
		return supplier;
	}

	public String getKode_produk() {
		return kode_produk;
	}

	public void setKode_produk(String kode_produk) {
		setData(kode_produk);
	}

	public Produk getProdukModel() {
		return produkModel;
	}

	public String getNama_produk() {
		return nama_produk;
	}

	public String getSatuan() {
		return satuan;
	}

	public double getHarga_satuan() {
		return harga_satuan;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public int getQty() {
		return qty;
	}

	public void setNama_produk(String nama_produk) {
		this.nama_produk = nama_produk;
	}

	public void setSatuan(String satuan) {
		this.satuan = satuan;
	}

	public void setHarga_satuan(double harga_satuan) {
		this.harga_satuan = harga_satuan;
	}

	public int getKode_supplier() {
		return kode_supplier;
	}

	public void setKode_supplier(int kode_supplier) {
		this.kode_supplier = kode_supplier;
	}
}
