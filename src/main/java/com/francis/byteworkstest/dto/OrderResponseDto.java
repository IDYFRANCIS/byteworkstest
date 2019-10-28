package com.francis.byteworkstest.dto;

import java.util.Date;

import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;

public class OrderResponseDto {
	
	private long quantity;
	private double amount;
	private FoodType foodType;
	private DeliveryMethod deliveryType;
	private PaymentType paymentType;
	private Date dateCreated;
	private Date dateUpdatedOrder;
	private String developerFirstName;
	private String developerLastName;
	private String developerPhone;
	private String developerEmail;
	
	
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	public DeliveryMethod getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(DeliveryMethod deliveryType) {
		this.deliveryType = deliveryType;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdatedOrder() {
		return dateUpdatedOrder;
	}
	public void setDateUpdatedOrder(Date dateUpdatedOrder) {
		this.dateUpdatedOrder = dateUpdatedOrder;
	}
	public String getDeveloperFirstName() {
		return developerFirstName;
	}
	public void setDeveloperFirstName(String developerFirstName) {
		this.developerFirstName = developerFirstName;
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
	
	

}
