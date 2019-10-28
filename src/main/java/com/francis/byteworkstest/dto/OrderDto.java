package com.francis.byteworkstest.dto;

import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;

public class OrderDto {

	private String developerCode;
	private long quantity;
	private PaymentType paymentType;
	private DeliveryMethod deliveryType;
	private FoodType foodType;
	
	
	
	public String getDeveloperCode() {
		return developerCode;
	}
	public void setDeveloperCode(String developerCode) {
		this.developerCode = developerCode;
	}
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public DeliveryMethod getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(DeliveryMethod deliveryType) {
		this.deliveryType = deliveryType;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	
	
}
