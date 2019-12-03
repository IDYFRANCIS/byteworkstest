package com.francis.byteworkstest.dto;

public class BvnVerificationResponse {

	private boolean status;
	private String message;
	private BvnVerificationResponseData data;
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BvnVerificationResponseData getData() {
		return data;
	}
	public void setData(BvnVerificationResponseData data) {
		this.data = data;
	}
	
}
