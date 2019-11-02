package com.francis.byteworkstest.dto;

import com.francis.byteworkstest.enumType.UserRoleType;

public class DeveloperResponseDto {

	private String developerCode;
	
	private String developerFristName;
	
	private String developerLastName;
	
	private String developerPhone;
	
	private String developerEmail;
	
	private String developerGender;
	
	private String address;
	
	private UserRoleType userRoleType;
	

	
	
	public String getDeveloperCode() {
		return developerCode;
	}

	public void setDeveloperCode(String developerCode) {
		this.developerCode = developerCode;
	}

	public String getDeveloperFristName() {
		return developerFristName;
	}

	public void setDeveloperFristName(String developerFristName) {
		this.developerFristName = developerFristName;
	}

	public String getDeveloperLastName() {
		return developerLastName;
	}

	public void setDeveloperLastName(String developerLastName) {
		this.developerLastName = developerLastName;
	}

	public String getDeveloperPhone() {
		return developerPhone;
	}

	public void setDeveloperPhone(String developerPhone) {
		this.developerPhone = developerPhone;
	}

	public String getDeveloperEmail() {
		return developerEmail;
	}

	public void setDeveloperEmail(String developerEmail) {
		this.developerEmail = developerEmail;
	}

	public String getDeveloperGender() {
		return developerGender;
	}

	public void setDeveloperGender(String developerGender) {
		this.developerGender = developerGender;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserRoleType getUserRoleType() {
		return userRoleType;
	}

	public void setUserRoleType(UserRoleType userRoleType) {
		this.userRoleType = userRoleType;
	}

	
}
