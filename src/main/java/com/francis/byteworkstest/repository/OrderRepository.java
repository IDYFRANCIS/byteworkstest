package com.francis.byteworkstest.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.model.Order;


/**
 * Order Repository
 * @author Francis
 *
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Order findById(long id);
	
	// find order account by orderNumber  	
	public Order findByOrderNumber(String orderNumber);
	
	// find order accounts by deliveryType  	
	Collection<Order> findByDeliveryType(DeliveryMethod deliveryType);
	
	// find order accounts by foodType  	
	Collection<Order> findByFoodType(FoodType foodType);
	
	// find order accounts by paymentType  	
	Collection<Order> findByPaymentType(PaymentType paymentType);

}
