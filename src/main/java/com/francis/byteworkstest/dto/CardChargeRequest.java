package com.francis.byteworkstest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CardChargeRequest {
	
	private int count;
	private String orderNumber;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String amount;
	private String pin;
	private CardDetails card;
	@JsonIgnore
	private MetadataDto metadata;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public CardDetails getCard() {
		return card;
	}
	public void setCard(CardDetails card) {
		this.card = card;
	}
	public MetadataDto getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataDto metadata) {
		this.metadata = metadata;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
}
