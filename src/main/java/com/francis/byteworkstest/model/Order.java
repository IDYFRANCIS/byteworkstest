package com.francis.byteworkstest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;



@SuppressWarnings("serial")
@Entity
@Table(name = "orders")
public class Order implements Serializable{
	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "order_id", nullable = false, unique = true)
	private long id;
	
	@Column(name = "order_number", nullable = false, unique = true)
	private String orderNumber;	
	
	@Column(name = "payment_type", nullable = false)
	private PaymentType paymentType;
	
	@Column(name = "delivery_method", nullable = false)
	private DeliveryMethod deliveryType;
	
	@Column(name = "amount", nullable = false)
	private double amount;
	
	@Column(name = "quantity", nullable = false)
	private long quantity;
	
	@Column(name = "food_type", nullable = false)
	private FoodType foodType;
	
	//@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;
	
	@Column(name = "date_created", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrdered;
	
//	@Column(name = "date_update", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateUpdatedOrder;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

//	public Date getDateUpdatedOrder() {
//		return dateUpdatedOrder;
//	}
//
//	public void setDateUpdatedOrder(Date dateUpdatedOrder) {
//		this.dateUpdatedOrder = dateUpdatedOrder;
//	}

}
