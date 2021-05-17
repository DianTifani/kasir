package model.data;

import java.util.ArrayList;

import model.Produk;

public class ProdukTransaksi {
	
	private Produk produkModel;
	private ArrayList<ProdukData> produkTransaksi;
	
	public ProdukTransaksi(Produk produkModel) {
		this.produkModel = produkModel;
	}
	
	public ProdukData tambahProduk(String kode_produk) {
		ProdukData produk = new ProdukData(produkModel, kode_produk);
		
		if (produk != null) {
			produkTransaksi.add(produk);
		}
		
		return produk;
	}
	
	public boolean hapusProduk(ProdukData produk) {
		if (produkTransaksi.contains(produk)) {
			produkTransaksi.remove(produk);
			return true;
		}
		
		return false;
	}
	
}
