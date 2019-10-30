package com.francis.byteworkstest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "food")
public class Food implements Serializable{
	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "food_id", nullable = false, unique = true)
	private long id;
	
	
	@Column(name = "food_code", nullable = false, unique = true)
	private String foodCode;	
	
	@Column(name = "food_name", nullable = false)
	private double foodName;
	
	@Column(name = "food_amount", nullable = false)
	private double foodAmount;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id")
	private FoodCategory foodCategory;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}

	public double getFoodName() {
		return foodName;
	}

	public void setFoodName(double foodName) {
		this.foodName = foodName;
	}

	public double getFoodAmount() {
		return foodAmount;
	}

	public void setFoodAmount(double foodAmount) {
		this.foodAmount = foodAmount;
	}

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}

}
