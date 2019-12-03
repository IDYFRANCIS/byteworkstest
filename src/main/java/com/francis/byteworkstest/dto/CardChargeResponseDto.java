package com.francis.byteworkstest.dto;

public class CardChargeResponseDto {

	private boolean status;
	private String message;
	private CardDataResponseDto data;
	
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
	public CardDataResponseDto getData() {
		return data;
	}
	public void setData(CardDataResponseDto data) {
		this.data = data;
	}
	
	
}
