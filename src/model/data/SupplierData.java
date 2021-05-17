package model.data;

import model.Supplier;

public class SupplierData {
	
	private Supplier supplierModel;
	
	private String kode_supplier;
	private String nama;
	private String no_telp;
	private String alamat;
	
	public SupplierData(Supplier model) {
		supplierModel = model;
	}
	
	public SupplierData(Supplier model, String kode_supplier) {
		this(model);
		setData(kode_supplier);
	}
	
	public void setData(String kode_supplier) {
		String[][] supplierData = supplierModel.getSupplier(kode_supplier);
		
		if (supplierData.length > 0) {
			this.kode_supplier = kode_supplier;
			this.nama = supplierData[0][1];
			this.no_telp = supplierData[0][2];
			this.alamat = supplierData[0][3];
		}
	}

	public String getKode_supplier() {
		return kode_supplier;
	}

	public void setKode_supplier(String kode_supplier) {
		setData(kode_supplier);
	}

	public Supplier getSupplierModel() {
		return supplierModel;
	}

	public String getNama() {
		return nama;
	}

	public String getNo_telp() {
		return no_telp;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
}
