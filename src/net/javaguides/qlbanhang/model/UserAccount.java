package net.javaguides.qlbanhang.model;

public class UserAccount {

	private String maNV;
	private String password;
	private String fullName;
	private String phone;
	private String email;
	
	public UserAccount(){
	}
	
	
	public UserAccount(String maNV, String password, String fullName, String phone, String email) {
		super();
		this.maNV = maNV;
		this.password = password;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
	}


	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "UserAccount{'maNV':'" + maNV + 
				"','password':'" + password + 
				"','fullName':'" + fullName + 
				"','phone':'" + phone + 
				"','email':'" + email + "'}";
	}

	
}
