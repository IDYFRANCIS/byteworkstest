package com.francis.byteworkstest.dto;


import com.francis.byteworkstest.enumType.UserRoleType;



public class UserDto {
	
	private String userCode = "";	
    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private String email= "" ;
    private String phone = "";
    private String activationCode = "";
    private boolean active;
	private UserRoleType role;
	
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public UserRoleType getRole() {
		return role;
	}
	public void setRole(UserRoleType role) {
		this.role = role;
	}
	
	
}
