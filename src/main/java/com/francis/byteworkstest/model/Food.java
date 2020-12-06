package com.francis.byteworkstest.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
@Entity
@Table(name = "foods")
public class Food implements Serializable{
	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "food_id", nullable = false, unique = true)
	private long id;	
	
	@Column(name = "food_code", nullable = false, unique = true)
	private String foodCode;	
	
	@Column(name = "food_name", nullable = false)
	private String foodName;
	
	@Column(name = "food_amount", nullable = false)
	private double foodAmount;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinTable(name = "food", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"), inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "food_id"))
//	private Set<Order> order;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	
	
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

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodAmount() {
		return foodAmount;
	}

	public void setFoodAmount(double foodAmount) {
		this.foodAmount = foodAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	 
	
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "category_id")
//	private FoodCategory foodCategory;

	
}
