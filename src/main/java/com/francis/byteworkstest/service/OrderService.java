package com.francis.byteworkstest.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.francis.byteworkstest.dto.OrderDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.model.Order;

@Service
public interface OrderService {
	
	//Method to get all orders 
	public Collection<Order> findAll();
	
	public Order findById(long id);
	
	//Method to get order by order number
	public Order findByOrderNumber(String orderNumber);
	
	//Method to create orders and select various parameters for order
	public ServerResponse createOrder(OrderDto request, PaymentType paymentType, FoodType foodType, DeliveryMethod deliverType);
	
	//Method to get orders by order number
	public ServerResponse getOrderByOrderNumber(String orderNumber);
	
	//Method to get orders by payment type
	public ServerResponse getOrderByPaymentType(PaymentType paymentType);
	
	//Method to get orders by type of food ordered
	public ServerResponse getOrderByFoodType(FoodType foodType);
	
	//Methods to get orders by delivery type
	public ServerResponse getOrderByDeliveryType(DeliveryMethod deliveryType);
	
	//Methods to get all food ordered
	public ServerResponse getAllOrders();
	
	public ServerResponse getOrderByFood(String foodName);

}
