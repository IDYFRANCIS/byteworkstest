package com.francis.byteworkstest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.CardChargeRequest;
import com.francis.byteworkstest.dto.PayForOrderRequestDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.PaymentStatus;
import com.francis.byteworkstest.service.PaymentService;

//import com.africaprudential.shareportal.Constant.ServerResponseStatus;
//import com.africaprudential.shareportal.Dto.CardChargeRequest;
//import com.africaprudential.shareportal.Dto.ServerResponse;
//import com.africaprudential.shareportal.EnumType.PaymentStatus;
//import com.africaprudential.shareportal.Service.PaymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "payment", produces = "application/json")
@Api(tags = "Payment Management", description = "Endpoint")
public class PaymentController {

	@Autowired 
	PaymentService service;
	
	private HttpHeaders responseHeaders = new HttpHeaders();	

	@ApiOperation(value = "Make payment for order", response = ServerResponse.class)
	@RequestMapping(value ="/{userCode}", method = RequestMethod.POST)
//	@PreAuthorize("hasAuthority('isAdmin') OR hasAuthority('isShareholder')")
	@ResponseBody
	public ResponseEntity<?> create(@RequestHeader("Authorization")  String authorization, @PathVariable("userCode") String userCode, @RequestBody PayForOrderRequestDto request){
				
		ServerResponse response = new ServerResponse();
		
		try {
			response = service.create(userCode, request);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            e.printStackTrace();
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	

	@ApiOperation(value = "View payment by transaction number", response = ServerResponse.class)
	@RequestMapping(value ="/{transactionNumber}", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('isAdmin') OR hasAuthority('isShareholder')")
	@ResponseBody
	public ResponseEntity<?> getPaymentByTransactionnumber(@RequestHeader("Authorization")  String authorization, @PathVariable("transactionNumber") String transactionNumber){
				
		ServerResponse response = new ServerResponse();
		
		try {
			response = service.getPaymentByTransactionnumber(transactionNumber);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            e.printStackTrace();
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	@ApiOperation(value = "View all payments by order number", response = ServerResponse.class)
	@RequestMapping(value ="/order/{orderNumber}", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('isAdmin') OR hasAuthority('isShareholder')")
	@ResponseBody
	public ResponseEntity<?> getPaymentByOrderNumber(@RequestHeader("Authorization")  String authorization, @PathVariable("orderNumber") String orderNumber){
				
		ServerResponse response = new ServerResponse();
		
		try {
			response = service.getPaymentByOrder(orderNumber);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            e.printStackTrace();
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	@ApiOperation(value = "View all payments by paymentStatus", response = ServerResponse.class)
	@RequestMapping(value ="/status/{paymentStatus}", method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('isAdmin') OR hasAuthority('isShareholder')")
	@ResponseBody
	public ResponseEntity<?> getPaymentsByStatus(@RequestHeader("Authorization")  String authorization, @PathVariable("paymentStatus") PaymentStatus paymentStatus){
				
		ServerResponse response = new ServerResponse();
		
		try {
			response = service.getPaymentsByStatus(paymentStatus);
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            e.printStackTrace();
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	@ApiOperation(value = "View all payments", response = ServerResponse.class)
	@RequestMapping( method = RequestMethod.GET)
//	@PreAuthorize("hasAuthority('isAdmin') OR hasAuthority('isShareholder')")
	@ResponseBody
	public ResponseEntity<?> getPayments(@RequestHeader("Authorization")  String authorization){
				
		ServerResponse response = new ServerResponse();
		
		try {
			response = service.getPayments();
		 
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("An error occured");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            e.printStackTrace();
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	
}