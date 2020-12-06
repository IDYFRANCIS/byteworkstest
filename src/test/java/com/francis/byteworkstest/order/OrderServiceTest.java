package com.francis.byteworkstest.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.francis.byteworkstest.ByteworkstestApplication;
import com.francis.byteworkstest.dto.OrderDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.service.OrderService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = ByteworkstestApplication.class)
public class OrderServiceTest {

	@Autowired
	OrderService orderService;
	
	@Test
	public void create(){
		String developerCode = "DEV1580358070165";
		long quantity = 1;
		PaymentType paymentType = null;
		FoodType foodType = null;
		DeliveryMethod deliverType = null;
		OrderDto order = new OrderDto();
		order.setDeveloperCode(developerCode);
		order.setQuantity(quantity);
		ServerResponse response = orderService.createOrder(order, PaymentType.CARD, FoodType.JELLOF_RICE, DeliveryMethod.Office_Delivery);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void getAllOrders(){
		ServerResponse response = orderService.getAllOrders();
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void getOrderByOrderNumber(){
		ServerResponse response = orderService.getOrderByOrderNumber("ORDER1580358105244");
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	@Test
	public void getOrderByDeliveryType(){
		DeliveryMethod delivery = null;
		ServerResponse response = orderService.getOrderByDeliveryType(DeliveryMethod.Office_Delivery);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	
	@Test
	public void getOrderByFoodType(){
		FoodType food = null;
		ServerResponse response = orderService.getOrderByFoodType(FoodType.JELLOF_RICE);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	
	@Test
	public void getOrderByPaymentType(){
		PaymentType payment = null;
		ServerResponse response = orderService.getOrderByPaymentType(PaymentType.ON_DELIVERY);
		System.out.println(response.getMessage());
		assertEquals(response.isSuccess(), true);
	}
	
	
}
