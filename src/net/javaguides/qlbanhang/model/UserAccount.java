package net.javaguides.qlbanhang.model;

import java.util.List;

public class UserAccount {

	private String maNV;
	private String password;
	private String fullName;
	private String phone;
	private String email;
	private List<String> roles;
	
	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


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
		StringBuilder roleStr = new StringBuilder("[");
		
		for(int i=0; i<roles.size(); i++) {
			if(i != roles.size() - 1) {
				roleStr.append("'" + roles.get(i) + "',");
			}else {
				roleStr.append("'" + roles.get(i) + "'");
			}
		}
		roleStr.append("]");
		
		return "UserAccount{'maNV':'" + maNV + 
				"','password':'" + password + 
				"','fullName':'" + fullName + 
				"','phone':'" + phone + 
				"','email':'" + email + 
				"','roles':" + roleStr.toString() + 
				"}";
	}

	
}
