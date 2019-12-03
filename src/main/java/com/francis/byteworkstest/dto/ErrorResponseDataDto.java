package com.francis.byteworkstest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponseDataDto {

	@JsonIgnore
	private String reference;
	private String status;
	private String display_text;
	private String message;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDisplay_text() {
		return display_text;
	}
	public void setDisplay_text(String display_text) {
		this.display_text = display_text;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
