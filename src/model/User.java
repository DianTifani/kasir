package model;

public class User extends BaseModel {
	
	public User(DatabaseConnection dbconn) {
		super(dbconn);
	}
	
	public String [][] getUser(String kode_user) {
		String data[][] = null;
		
		String query = "SELECT username, nama from user where kode_user='"+kode_user+"'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public String [][] getAllUser() {
		String data[][] = null;
		
		String query = "SELECT kode_user, username, nama from user";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
	
	public boolean cekUser(String username, String password) {
		String data[][] = null;
		
		String query = "SELECT COUNT(kode_user) FROM user WHERE username='"+ username +"' AND password='"+ password +"'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		int count = Integer.valueOf(data[0][0]);
		
		if (count != 1 || count == 0) return false;
		
		return true;
	}
	
	public String[][] getUserWithUsernamePassword(String username, String password) {
		String data[][] = null;
		
		String query = "SELECT kode_user, username, nama FROM user WHERE username='"+ username + "' AND password='"+ password +"'";
		executeQuery(query);
		
		data = resultSetToArray();
		close();
		
		return data;
	}
}
