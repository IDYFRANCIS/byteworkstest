package com.francis.byteworkstest.serviceImpl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.OrderDto;
import com.francis.byteworkstest.dto.OrderResponseDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.model.Developer;
import com.francis.byteworkstest.model.Order;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.DeveloperRepository;
import com.francis.byteworkstest.repository.OrderRepository;
import com.francis.byteworkstest.service.OrderService;
import com.francis.byteworkstest.utility.Utility;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	DeveloperRepository developerRepo;
	
	Utility utility = new Utility();

	@Override
	public Collection<Order> findAll() {

		try {
			
			return (Collection<Order>) orderRepo.findAll();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Order findById(long id) {

		try {
		
			return orderRepo.findById(id);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Order findByOrderNumber(String orderNumber) {

		try {
			
			return orderRepo.findByOrderNumber(orderNumber);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ServerResponse createOrder(OrderDto request) {

		ServerResponse response = new ServerResponse();
		
		Order order = null;
		
		String code = request.getDeveloperCode() != null ? request.getDeveloperCode() : request.getDeveloperCode();
		long quantity = request.getQuantity() != 0 ? request.getQuantity() : request.getQuantity();
		PaymentType payment = request.getPaymentType() != null ? request.getPaymentType() : request.getPaymentType();
		DeliveryMethod delivery = request.getDeliveryType() != null ? request.getDeliveryType() : request.getDeliveryType();
		FoodType food = request.getFoodType() != null ? request.getFoodType() : request.getFoodType();
		

		if (code == null || code.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter developer code");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (quantity == 0) {
			response.setData("");
            response.setMessage("Please enter quantity of food to be ordred");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (payment == null || payment.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select payment method");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		if (delivery == null || delivery.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select delivery method");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (food == null || food.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select food");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
	
		try {
			
			Developer confirmDeveloper = developerRepo.findByDeveloperCode(request.getDeveloperCode());
			
			if (confirmDeveloper == null) {
				response.setData("");
                response.setMessage("Developer does not exist");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.FAILED);

                return response;
			}
			
			
			order = new Order();
			String orderCode = "ORDER" + System.currentTimeMillis();
			
			order.setDeveloper(confirmDeveloper);
			order.setFoodType(request.getFoodType());
			order.setDeliveryType(request.getDeliveryType());
			order.setPaymentType(request.getPaymentType());
			order.setQuantity(request.getQuantity());
			order.setOrderNumber(orderCode);
			
			
			
			if (request.getFoodType().equals(FoodType.FRIED_RICE)) {
				order.setAmount(500 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.GARRI_AND_EGGUSUSOUP)) {
				order.setAmount(700 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.GARRI_AND_OKROSOUP)) {
				order.setAmount(700 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.GARRI_AND_VEGETABLESOUP)) {
				order.setAmount(800 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.JELLOF_RICE)) {
				order.setAmount(450 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.RICE_AND_BEANS)) {
				order.setAmount(500 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.RICE_AND_PEPPERSOUP)) {
				order.setAmount(600 * request.getQuantity());
			}
			
			if (request.getFoodType().equals(FoodType.RICE_AND_STEW)) {
				order.setAmount(550 * request.getQuantity());
			}
			
			//entityManager.persist(order);
			
			orderRepo.save(order);
			
			response.setData(order);
			response.setMessage("Developer order successfully placed");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			response.setData("");
	        response.setMessage("Developer order was not placed");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
	        
	        return response;
			
		}
		
		return response;
	}

	@Override
	public ServerResponse getOrderByOrderNumber(String orderNumber) {

		ServerResponse response =  new ServerResponse();
		
		if(orderNumber == null || orderNumber.isEmpty()) {
			response.setData("");
            response.setMessage("Please provide order number");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		
		try {
			
			Order orders = orderRepo.findByOrderNumber(orderNumber);
			
			if (orders == null) {
				response.setData("");
	            response.setMessage("Order with order number " + orders.getOrderNumber()+ " does not exist");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.OK);
	            return response;
			}
			
			
			response.setData(orders);
            response.setMessage("Oder fetched successfully");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.OK);
			
			
		}catch(Exception e) {
			response.setData("");
            response.setMessage("Something went wrong");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return response;
		}
		
		return response;
	}

	
	
	@Override
	public ServerResponse getOrderByPaymentType(PaymentType paymentType) {

		ServerResponse response =  new ServerResponse();
		
//		if(orderNumber == null || orderNumber.isEmpty()) {
//			response.setData("");
//            response.setMessage("Please provide developer code");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//            return response;
//		}
		
		if(paymentType == null || paymentType.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select payment type");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		
		try {
			
			Collection<Order> payment = orderRepo.findByPaymentType(paymentType);
			
			 paymentType.toString();
			
			if (payment.size() < 1) {
				response.setData("");
	            response.setMessage("No order found for selected payment type");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.OK);
	            return response;
			}
			
			response.setData(payment);
            response.setMessage("Order fetched successfully for selected payment type");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.OK);
			
			
		}catch(Exception e) {
			response.setData("");
            response.setMessage("Something went wrong");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return response;
		}
		
		return response;
	}

	
	
	@Override
	public ServerResponse getOrderByFoodType(FoodType foodType) {

		ServerResponse response =   new ServerResponse();
		
//		if(orderNumber == null || orderNumber.isEmpty()) {
//			response.setData("");
//            response.setMessage("Please provide developer code");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//            return response;
//		}
		
		if(foodType == null || foodType.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select food type");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		
		try {
			
			Collection<Order> food = orderRepo.findByFoodType(foodType);
			
			foodType.name();
			if (food.size() < 1) {
				response.setData("");
	            response.setMessage("No order found for selected food type");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.OK);
	            return response;
			}
			
			response.setData(food);
            response.setMessage("Order fetched successfully for selected food type");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.OK);
			
		}catch(Exception e) {
			response.setData("");
            response.setMessage("Something went wrong");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return response;
		}
		
		return response;
	}

	
	
	
	@Override
	public ServerResponse getOrderByDeliveryType(DeliveryMethod deliveryType) {

           ServerResponse response = new ServerResponse();
		
           if(deliveryType == null || deliveryType.toString().isEmpty()) {
   			   response.setData("");
               response.setMessage("Please provide developer code");
               response.setSuccess(false);
               response.setStatus(ServerResponseStatus.FAILED);
               return response;
   		}
   		
   		  if (deliveryType == null || deliveryType.toString().isEmpty()) {
   			   response.setData("");
               response.setMessage("Please select delivery type");
               response.setSuccess(false);
               response.setStatus(ServerResponseStatus.FAILED);
               return response;
   		}
           
           try {
        	   
        	   Collection<Order> delivery = orderRepo.findByDeliveryType(deliveryType);
        	   
        	   deliveryType.toString();
        	   if (delivery.size() < 1) {
   				response.setData("");
   	            response.setMessage("No order found for selected delivery method");
   	            response.setSuccess(false);
   	            response.setStatus(ServerResponseStatus.OK);
   	            return response;
   			}
   			
   			   response.setData(delivery);
               response.setMessage("Order fetched successfully for selected delivery method");
               response.setSuccess(false);
               response.setStatus(ServerResponseStatus.OK);
   			
        	   
           }catch(Exception e) {
        	   response.setData("");
               response.setMessage("Something went wrong");
               response.setSuccess(false);
               response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
               e.printStackTrace();
               return response; 
           }
           
		return response;
	}

	   @Override
	    public ServerResponse getAllOrders() {

		   ServerResponse response = new ServerResponse();
			try {
				
				Collection<Order> orders = findAll();
				
				if (orders.size() < 1) {
					response.setData("");
			        response.setMessage("Order list is empty");
			        response.setSuccess(false);
			        response.setStatus(ServerResponseStatus.OK);
			        return response;
				}
				
				response.setData(orders);
		        response.setMessage("Get data successfully");
		        response.setSuccess(true);
		        response.setStatus(ServerResponseStatus.OK);
			} catch (Exception e) {
				response.setData("");
		        response.setMessage("Something went wrong");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		   
	}

}
