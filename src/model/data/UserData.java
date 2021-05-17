package model.data;

import model.User;

public class UserData {
	
	private User userModel;
	private int kode_user;
	private String nama;
	private String username;
	
	public UserData(User model) {
		this.userModel = model;
	}
	
	public UserData(User model, String username, String password) {
		this(model);
		setData(username, password);
	}
	
	public void setData(String username, String password) {
		if (this.userModel.cekUser(username, password)) {
			
			String [][] data = this.userModel.getUserWithUsernamePassword(username, password);
			
			this.kode_user = Integer.valueOf(data[0][0]);
			this.username = data[0][1];
			this.nama = data[0][2];
			
		}
	}

	public int getKode_user() {
		return kode_user;
	}

	public String getNama() {
		return nama;
	}

	public String getUsername() {
		return username;
	}
}
