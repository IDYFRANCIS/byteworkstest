package com.francis.byteworkstest.dto;

public class ErrorResponseDto {

	private boolean status;
	private String message;
	private ErrorResponseDataDto data;
	
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
	public ErrorResponseDataDto getData() {
		return data;
	}
	public void setData(ErrorResponseDataDto data) {
		this.data = data;
	}
}
