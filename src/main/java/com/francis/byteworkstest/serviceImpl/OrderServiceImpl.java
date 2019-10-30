package com.francis.byteworkstest.serviceImpl;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.AppConstants;
import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.OrderDto;
import com.francis.byteworkstest.dto.OrderResponseDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.mail.EmailService;
import com.francis.byteworkstest.mail.Mail;
import com.francis.byteworkstest.model.Developer;
import com.francis.byteworkstest.model.Order;
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
	AppConstants appConstants;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	DeveloperRepository developerRepo;
	
	@Autowired
	private EmailService emailService;
	
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

//	@Override
//	public ServerResponse createOrder(OrderDto request) {
//
//		ServerResponse response = new ServerResponse();
//		
//		Order order = null;
//		
//		String code = request.getDeveloperCode() != null ? request.getDeveloperCode() : request.getDeveloperCode();
//		long quantity = request.getQuantity() != 0 ? request.getQuantity() : request.getQuantity();
//		PaymentType payment = request.getPaymentType() != null ? request.getPaymentType() : request.getPaymentType();
//		DeliveryMethod delivery = request.getDeliveryType() != null ? request.getDeliveryType() : request.getDeliveryType();
//		FoodType food = request.getFoodType() != null ? request.getFoodType() : request.getFoodType();
//		
//
//		if (code == null || code.isEmpty()) {
//			response.setData("");
//            response.setMessage("Please enter developer code");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//
//            return response;
//		}
//		
//		if (quantity == 0) {
//			response.setData("");
//            response.setMessage("Please enter quantity of food to be ordred");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//
//            return response;
//		}
//		
//		if (payment == null || payment.toString().isEmpty()) {
//			response.setData("");
//            response.setMessage("Please select payment method");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//
//            return response;
//		}
//		
//		
//		if (delivery == null || delivery.toString().isEmpty()) {
//			response.setData("");
//            response.setMessage("Please select delivery method");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//
//            return response;
//		}
//		
//		if (food == null || food.toString().isEmpty()) {
//			response.setData("");
//            response.setMessage("Please select food");
//            response.setSuccess(false);
//            response.setStatus(ServerResponseStatus.FAILED);
//
//            return response;
//		}
//		
//	
//		try {
//			
//			Developer confirmDeveloper = developerRepo.findByDeveloperCode(request.getDeveloperCode());
//			
//			if (confirmDeveloper == null) {
//				response.setData("");
//                response.setMessage("Developer does not exist");
//                response.setSuccess(false);
//                response.setStatus(ServerResponseStatus.FAILED);
//
//                return response;
//			}
//			
//			
//			order = new Order();
//			
//			String orderCode = "ORDER" + System.currentTimeMillis();
//			
//			order.setOrderNumber(orderCode);
//			order.setDeveloper(confirmDeveloper);
//			order.setFoodType(request.getFoodType());
//			order.setDeliveryType(request.getDeliveryType());
//			order.setPaymentType(request.getPaymentType());
//			order.setQuantity(request.getQuantity());
//			order.setDateOrdered(order.getDateOrdered());
//			
//			if (request.getFoodType().equals(FoodType.FRIED_RICE)) {
//				order.setAmount(500 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.GARRI_AND_EGGUSUSOUP)) {
//				order.setAmount(700 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.GARRI_AND_OKROSOUP)) {
//				order.setAmount(700 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.GARRI_AND_VEGETABLESOUP)) {
//				order.setAmount(800 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.JELLOF_RICE)) {
//				order.setAmount(450 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.RICE_AND_BEANS)) {
//				order.setAmount(500 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.RICE_AND_PEPPERSOUP)) {
//				order.setAmount(600 * request.getQuantity());
//			}
//			
//			if (request.getFoodType().equals(FoodType.RICE_AND_STEW)) {
//				order.setAmount(550 * request.getQuantity());
//			}
//			
//			//send mail notification on account creation
//			
////			Mail mail = new Mail();
////            mail.setTo(appConstants.APP_ADMIN_EMAIL);
////            mail.setFrom("idongesitukut25@gmail.com");
////            mail.setSubject("Food Order");
////
////            Map<String, Object> model = new HashMap<String, Object>();
////             {
////	            model.put("salutation", "Dear " + appConstants.APP_DEFAULT_ADMIN_NAME);
////
////			}
////            model.put("message", "Order Notification from ByteWorks Food Vendor App, An order has been placed by a developer for food with order number: <b>" + orderCode + "</b>  Kindly ensure that this order is suplied to the developer with the specified parameters");
////            model.put("link", appConstants.APP_WEB_URL + "/order/orderCode/" + orderCode);
////            mail.setModel(model);
////            mail.setTemplate("verify.ftl");
////            
////            emailService.sendSimpleMessage(mail);
//			
//			
//			orderRepo.save(order);
//			
//			OrderResponseDto dto = new OrderResponseDto();
//			dto.setOrderNumber(orderCode);
//			dto.setFoodType(request.getFoodType());
//			dto.setPaymentType(request.getPaymentType());
//			dto.setDeliveryType(request.getDeliveryType());
//			dto.setQuantity(request.getQuantity());
//		//	dto.setDeveloperCode(request.getDeveloperCode());
//			dto.setUserFirstName(confirmDeveloper.getUser().getFirstName());
//			dto.setUserMiddleName(confirmDeveloper.getUser().getMiddleName());
//			dto.setUserLastName(confirmDeveloper.getUser().getLastName());
//			dto.setGender(confirmDeveloper.getUser().getGender());
//			dto.setEmail(confirmDeveloper.getUser().getEmail());
//			dto.setPhone(confirmDeveloper.getUser().getPhone());
//			dto.setAddress(confirmDeveloper.getUser().getAddress());
//			dto.setDateOrdered(order.getDateOrdered());
//			dto.setAmount(order.getAmount());
//			
//			response.setData(dto);
//			response.setMessage("Developer order successfully placed");
//			response.setSuccess(true);
//			response.setStatus(ServerResponseStatus.OK);
//			
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			response.setData("");
//	        response.setMessage("Developer order was not placed");
//	        response.setSuccess(false);
//	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
//	        
//	        return response;
//			
//		}
//		
//		return response;
//	}

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
	            response.setMessage("Order with order number " + orderNumber + " does not exist");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.OK);
	            return response;
			}
			
			OrderResponseDto dto1 = new OrderResponseDto();
			dto1.setOrderNumber(orders.getOrderNumber());
			dto1.setFoodType(orders.getFoodType());
			dto1.setPaymentType(orders.getPaymentType());
			dto1.setDeliveryType(orders.getDeliveryType());
			dto1.setQuantity(orders.getQuantity());
		//	dto1.setDeveloperCode(orders.getDeveloper().getDeveloperCode());
			dto1.setUserFirstName(orders.getDeveloper().getUser().getFirstName());
			dto1.setUserMiddleName(orders.getDeveloper().getUser().getMiddleName());
			dto1.setUserLastName(orders.getDeveloper().getUser().getLastName());
			dto1.setGender(orders.getDeveloper().getUser().getGender());
			dto1.setEmail(orders.getDeveloper().getUser().getEmail());
			dto1.setPhone(orders.getDeveloper().getUser().getPhone());
			dto1.setAddress(orders.getDeveloper().getUser().getAddress());
			dto1.setDateOrdered(orders.getDateOrdered());
			dto1.setAmount(orders.getAmount());
			
			response.setData(dto1);
            response.setMessage("Oder fetched successfully");
            response.setSuccess(true);
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
		
		
		if(paymentType == null || paymentType.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select payment type");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		
		try {
			
			Collection<Order> payment = orderRepo.findByPaymentType(paymentType);
			
			
			if (payment.size() < 1) {
				response.setData("");
	            response.setMessage("No order found for selected payment type");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.OK);
	            return response;
			}
			
			
			response.setData(payment);
            response.setMessage("Order fetched successfully for selected payment type");
            response.setSuccess(true);
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
            response.setSuccess(true);
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
               response.setSuccess(true);
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
				
				Collection<Order> orders2 = findAll();
				
				if (orders2.size() < 1) {
					response.setData("");
			        response.setMessage("Order list is empty");
			        response.setSuccess(false);
			        response.setStatus(ServerResponseStatus.OK);
			        return response;
				}
				
				
				response.setData(orders2);
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

	@Override
	public ServerResponse createOrder(OrderDto request, PaymentType paymentType, FoodType foodType,
			DeliveryMethod deliverType) {
		
        ServerResponse response = new ServerResponse();
		
		Order order = null;
		
		String code = request.getDeveloperCode() != null ? request.getDeveloperCode() : request.getDeveloperCode();
		long quantity = request.getQuantity() != 0 ? request.getQuantity() : request.getQuantity();
//		PaymentType payment = request.getPaymentType() != null ? request.getPaymentType() : request.getPaymentType();
//		DeliveryMethod delivery = request.getDeliveryType() != null ? request.getDeliveryType() : request.getDeliveryType();
//		FoodType food = request.getFoodType() != null ? request.getFoodType() : request.getFoodType();
	

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
		
		if (paymentType == null || paymentType.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select payment method");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		if (deliverType == null || deliverType.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please select delivery method");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (foodType == null || foodType.toString().isEmpty()) {
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
			
			order.setOrderNumber(orderCode);
			order.setDeveloper(confirmDeveloper);
			order.setFoodType(foodType);
			order.setDeliveryType(deliverType);
			order.setPaymentType(paymentType);
			order.setQuantity(request.getQuantity());
			order.setDateOrdered(order.getDateOrdered());
			
			if (foodType.equals(FoodType.FRIED_RICE)) {
				order.setAmount(500 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.GARRI_AND_EGGUSUSOUP)) {
				order.setAmount(700 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.GARRI_AND_OKROSOUP)) {
				order.setAmount(700 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.GARRI_AND_VEGETABLESOUP)) {
				order.setAmount(800 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.JELLOF_RICE)) {
				order.setAmount(450 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.RICE_AND_BEANS)) {
				order.setAmount(500 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.RICE_AND_PEPPERSOUP)) {
				order.setAmount(600 * request.getQuantity());
			}
			
			if (foodType.equals(FoodType.RICE_AND_STEW)) {
				order.setAmount(550 * request.getQuantity());
			}
			
			//send mail notification on account creation
			
//			Mail mail = new Mail();
//            mail.setTo(appConstants.APP_ADMIN_EMAIL);
//            mail.setFrom("idongesitukut25@gmail.com");
//            mail.setSubject("Food Order");
//
//            Map<String, Object> model = new HashMap<String, Object>();
//             {
//	            model.put("salutation", "Dear " + appConstants.APP_DEFAULT_ADMIN_NAME);
//
//			}
//            model.put("message", "Order Notification from ByteWorks Food Vendor App, An order has been placed by a developer for food with order number: <b>" + orderCode + "</b>  Kindly ensure that this order is suplied to the developer with the specified parameters");
//            model.put("link", appConstants.APP_WEB_URL + "/order/orderCode/" + orderCode);
//            mail.setModel(model);
//            mail.setTemplate("verify.ftl");
//            
//            emailService.sendSimpleMessage(mail);
			
			
			orderRepo.save(order);
			
			OrderResponseDto dto = new OrderResponseDto();
			dto.setOrderNumber(orderCode);
			dto.setFoodType(foodType);
			dto.setPaymentType(paymentType);
			dto.setDeliveryType(deliverType);
			dto.setQuantity(request.getQuantity());
		//	dto.setDeveloperCode(request.getDeveloperCode());
			dto.setUserFirstName(confirmDeveloper.getUser().getFirstName());
			dto.setUserMiddleName(confirmDeveloper.getUser().getMiddleName());
			dto.setUserLastName(confirmDeveloper.getUser().getLastName());
			dto.setGender(confirmDeveloper.getUser().getGender());
			dto.setEmail(confirmDeveloper.getUser().getEmail());
			dto.setPhone(confirmDeveloper.getUser().getPhone());
			dto.setAddress(confirmDeveloper.getUser().getAddress());
			dto.setDateOrdered(order.getDateOrdered());
			dto.setAmount(order.getAmount());
			
			response.setData(dto);
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

}
