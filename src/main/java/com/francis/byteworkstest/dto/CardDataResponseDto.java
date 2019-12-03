package com.francis.byteworkstest.dto;

public class CardDataResponseDto {
	
	private String reference;
	private String status;
	
	private String domain;
	private double amount;
	private String message;
	private String gateway_response;
	private String paid_at;
	private String created_at;
	private String channel;
	private String currency;
	private String ip_address;
	private Object log;
	private int fees;
	private Object fees_split;
	private Object plan;
	private String paidAt;
	private String createdAt;
	private String transaction_date;
	private Object plan_object;
	private Object subaccount;
	private ChargeCustomerDto customer;
	private ChargeAuthorizationDto authorization;
	private String display_text;

	
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

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGateway_response() {
		return gateway_response;
	}
	public void setGateway_response(String gateway_response) {
		this.gateway_response = gateway_response;
	}
	public String getPaid_at() {
		return paid_at;
	}
	public void setPaid_at(String paid_at) {
		this.paid_at = paid_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public Object getLog() {
		return log;
	}
	public void setLog(Object log) {
		this.log = log;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public Object getFees_split() {
		return fees_split;
	}
	public void setFees_split(Object fees_split) {
		this.fees_split = fees_split;
	}
	public Object getPlan() {
		return plan;
	}
	public void setPlan(Object plan) {
		this.plan = plan;
	}
	public String getPaidAt() {
		return paidAt;
	}
	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public Object getPlan_object() {
		return plan_object;
	}
	public void setPlan_object(Object plan_object) {
		this.plan_object = plan_object;
	}
	public Object getSubaccount() {
		return subaccount;
	}
	public void setSubaccount(Object subaccount) {
		this.subaccount = subaccount;
	}
	public ChargeCustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(ChargeCustomerDto customer) {
		this.customer = customer;
	}
	public ChargeAuthorizationDto getAuthorization() {
		return authorization;
	}
	public void setAuthorization(ChargeAuthorizationDto authorization) {
		this.authorization = authorization;
	}
	public String getDisplay_text() {
		return display_text;
	}
	public void setDisplay_text(String display_text) {
		this.display_text = display_text;
	}
	
}
