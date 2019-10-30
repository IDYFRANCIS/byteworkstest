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
	
	public Collection<Order> findAll();
	
	public Order findById(long id);
	
	public Order findByOrderNumber(String orderNumber);
	
	public ServerResponse createOrder(OrderDto request, PaymentType paymentType, FoodType foodType, DeliveryMethod deliverType);
	
	public ServerResponse getOrderByOrderNumber(String orderNumber);
	
	public ServerResponse getOrderByPaymentType(PaymentType paymentType);
	
	public ServerResponse getOrderByFoodType(FoodType foodType);
	
	public ServerResponse getOrderByDeliveryType(DeliveryMethod deliveryType);
	
	public ServerResponse getAllOrders();

}
