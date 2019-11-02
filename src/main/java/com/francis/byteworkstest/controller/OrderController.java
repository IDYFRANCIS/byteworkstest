package com.francis.byteworkstest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.OrderDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.francis.byteworkstest.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/*
 * Food Ordering and order request fetched by different parameters endpoint manager 
 */
@Controller
@RequestMapping(value = "/order", produces = "application/json")
@Api(tags = "Order Management", description = "Endpoint")
public class OrderController {
	
	
	@Autowired
	OrderService orderService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	

	@ApiOperation(value = "Developers can place order for food and get the total cost for the order", response = ServerResponse.class)
	@RequestMapping(value = "/{paymentType}/{foodType}/{deliveryType}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createOrder(@RequestHeader("Authorization")  String authorization, @RequestBody OrderDto request, @PathVariable("paymentType") PaymentType paymentType, @PathVariable("foodType") FoodType foodType, @PathVariable("deliveryType") DeliveryMethod deliveryType){
		
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = orderService.createOrder(request, paymentType, foodType, deliveryType);
					
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	
	@ApiOperation(value = "Get order by order Number", response = ServerResponse.class)
	@RequestMapping(value = "/{orderNumber}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getOrderByOrderNumber(@RequestHeader("Authorization")  String authorization, @PathVariable("orderNumber") String orderNumber){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = orderService.getOrderByOrderNumber(orderNumber);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	@ApiOperation(value = "Get order by developer and paymentType", response = ServerResponse.class)
	@RequestMapping(value = "/paymentType/{paymentType}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getOrderByPaymentType(@RequestHeader("Authorization")  String authorization, @PathVariable("paymentType") PaymentType paymentType){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = orderService.getOrderByPaymentType(paymentType);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	
	@ApiOperation(value = "Get order by developer and food Type", response = ServerResponse.class)
	@RequestMapping(value = "/foodType/{foodType}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getOrderByFoodType(@RequestHeader("Authorization")  String authorization,  @PathVariable("foodType") FoodType foodType){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = orderService.getOrderByFoodType(foodType);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	
	@ApiOperation(value = "Get order by developer and delivery Type", response = ServerResponse.class)
	@RequestMapping(value = "/deliveryType/{deliveryType}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getOrderByDeliveryMethod(@RequestHeader("Authorization")  String authorization,  @PathVariable("deliveryType") DeliveryMethod deliveryType){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = orderService.getOrderByDeliveryType(deliveryType);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	

	
	@ApiOperation(value = "Get all oders", response = ServerResponse.class)
    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization")  String authorization){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = orderService.getAllOrders();
		
		} catch (Exception e) {
			response.setData("An error occured in user account verification" + e.getMessage());
			response.setMessage("An error occured in user account verification");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
	
	
}
