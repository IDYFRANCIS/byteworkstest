package com.francis.byteworkstest.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.model.Order;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Order findById(long id);
	
	public Order findByOrderNumber(String orderNumber);
	
	Collection<Order> findByDeveloper_Orders_PaymentType(String developerCode, PaymentType paymentType);
	
	Collection<Order> findByDeliveryType(DeliveryMethod deliveryType);
	
	Collection<Order> findByFoodType(FoodType foodType);
	
	Collection<Order> findByPaymentType(PaymentType paymentType);

}
