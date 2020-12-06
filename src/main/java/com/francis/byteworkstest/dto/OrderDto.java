package com.francis.byteworkstest.dto;

import java.util.List;

import com.francis.byteworkstest.model.Food;

public class OrderDto {

	private String developerCode;
	private long quantity;
	private  List<Food> foodName;
	
		
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
	public List<Food> getFoodName() {
		return foodName;
	}
	public void setFoodName(List<Food> foodName) {
		this.foodName = foodName;
	}
	
	
	
	
}
