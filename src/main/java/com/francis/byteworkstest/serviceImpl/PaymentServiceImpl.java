package com.francis.byteworkstest.serviceImpl;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francis.byteworkstest.constant.AppConstants;
import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.ApiResponseDto;
import com.francis.byteworkstest.dto.CardChargeRequest;
import com.francis.byteworkstest.dto.CardChargeResponseDto;
import com.francis.byteworkstest.dto.ErrorResponseDto;
import com.francis.byteworkstest.dto.PayForOrderRequestDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.PaymentStatus;
import com.francis.byteworkstest.model.Developer;
import com.francis.byteworkstest.model.Order;
import com.francis.byteworkstest.model.Payment;
import com.francis.byteworkstest.model.User;
import com.francis.byteworkstest.repository.DeveloperRepository;
import com.francis.byteworkstest.repository.OrderRepository;
import com.francis.byteworkstest.repository.PaymentRepository;
import com.francis.byteworkstest.repository.UserRepository;
import com.francis.byteworkstest.service.PaymentService;
import com.francis.byteworkstest.utility.Utility;
import com.google.gson.Gson;



@Transactional
@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	AppConstants appConstants;
	
	@Autowired
	UserRepository userRepo;
		
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	DeveloperRepository developerRepo;
	
	
	
	
	
	

	@Override
	public ServerResponse create(String userCode, PayForOrderRequestDto request) {
		
        ServerResponse response = new ServerResponse();
		
		//Validate inputs
		if (userCode == null || userCode.isEmpty()) {
			response.setData("");
            response.setMessage("Please provide userCode");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		if (request.getOrderNumber() == null || request.getOrderNumber().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide order number");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		if (request.getCard().getNumber() == null || request.getCard().getNumber().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide card number");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (request.getCard().getExpiry_month() == null || request.getCard().getExpiry_month().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide card expiry month");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (request.getCard().getExpiry_year() == null || request.getCard().getExpiry_year().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide card expiry year");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (request.getCard().getCvv() == null || request.getCard().getCvv().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide card cvv");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		if (request.getPin() == null || request.getPin().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide card pin");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		try {
			
			//Validate user
			User user = userRepo.findByUserCode(userCode);
			if (user == null) {
				response.setData("");
	            response.setMessage("User not found");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			//validate order ids
			Order order = orderRepo.findByOrderNumber(request.getOrderNumber()); 
			if (order == null) {
				response.setData("");
	            response.setMessage("Order not found");
	            response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			//Calculate offer price
			double amount = order.getAmount();
			request.setAmount(String.valueOf(amount));
			request.setEmail(user.getEmail());
			
			ApiResponseDto api = new ApiResponseDto();
			Gson gson = new Gson();
        
			//Pay using paystack gateway
            api = Utility.httpPostRequest(appConstants.PAYSTACK_BVN_URL + "/charge", request, appConstants.PAYSTACK_AUTH_KEY);
            
            if (api.getStatus() == HttpURLConnection.HTTP_OK) {

                CardChargeResponseDto cardDataResponseDto = gson.fromJson(api.getResponse(), CardChargeResponseDto.class);
                
                if(cardDataResponseDto != null && cardDataResponseDto.getData().getStatus().equals("success")){
                	
                	String transactionNumber = Utility.generateDigit() + Utility.toDate() + Utility.generateDigit();
                    Payment payment = new Payment();
                    payment.setAmount(amount);
                    
                    payment.setPaymentId(payment.getPaymentId());
                    payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
                    payment.setReference(cardDataResponseDto.getData().getReference());
                    payment.setStatus(cardDataResponseDto.getData().getStatus());
                    payment.setChannel(cardDataResponseDto.getData().getChannel());
                    payment.setCreatedAt(cardDataResponseDto.getData().getCreated_at());
                    payment.setCurrency(cardDataResponseDto.getData().getCurrency());
                    payment.setCustomer(cardDataResponseDto.getData().getCustomer().getEmail());
               //     payment.setDeveloper(cardDataResponseDto.getData().);
                    payment.setPaidAt(cardDataResponseDto.getData().getPaid_at());
                    payment.setTransactionDate(cardDataResponseDto.getData().getTransaction_date());
                    
                 //   payment.setOrder(order.getDeveloper().getOrders());
                    payment.setUser(user);
                    payment.setTransactionNumber(transactionNumber);
                    
                   //Payment details
                    paymentRepo.save(payment);
                                    
                    response.setSuccess(cardDataResponseDto.isStatus());
        			response.setMessage("Payment Successful");
        			response.setData(payment);
        			response.setStatus(ServerResponseStatus.OK);
        			
                }else{
                	response.setSuccess(true);
        			response.setMessage(cardDataResponseDto.getMessage());
        			response.setData("");
        			response.setStatus(ServerResponseStatus.OK);
                }
               

            } else {
    			
                ErrorResponseDto errorResponseDto = gson.fromJson(api.getResponse(), ErrorResponseDto.class);
    			
    			response.setSuccess(errorResponseDto.isStatus());
    			response.setMessage(errorResponseDto.getData().getMessage());
    			response.setData(errorResponseDto.getData());
    			response.setStatus(ServerResponseStatus.FAILED);
            	
            }
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
		}
		return response;
	

	}

	@Override
	public ServerResponse getPaymentByTransactionnumber(String transactionNumber) {
		
		ServerResponse response = new ServerResponse();
		if (transactionNumber == null || transactionNumber.isEmpty()) {
			response.setData("");
            response.setMessage("Please provide transactionNumber");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}

		try {

            Payment payment = paymentRepo.findByTransactionNumber(transactionNumber);
            
        	if (payment == null) {
    			response.setData("");
                response.setMessage("Payment not found");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.FAILED);

                return response;
    		}
        	
    		response.setData(payment);
            response.setMessage("Get data successfully");
            response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
		}
		return response;
	}

	@Override
	public ServerResponse getPaymentsByStatus(PaymentStatus paymentStatus) {
		
		ServerResponse response = new ServerResponse();
		if (paymentStatus == null || paymentStatus.toString().isEmpty()) {
			response.setData("");
            response.setMessage("Please provide paymentStatus");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}

		try {

			  Collection<Payment> payments = paymentRepo.findByPaymentStatus(paymentStatus);
	            
	        	if (payments.size() < 1) {
	    			response.setData("");
	                response.setMessage("Not payment found");
	                response.setSuccess(false);
	                response.setStatus(ServerResponseStatus.OK);

	                return response;
	    		}
        
        	
    		response.setData(payments);
            response.setMessage("Get data successfully");
            response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
            
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
		}
		return response;
	}

	@Override
	public ServerResponse getPaymentByOrder(String orderNumber) {
		
		ServerResponse response = new ServerResponse();
		
		if (orderNumber == null || orderNumber.isEmpty()) {
			response.setData("");
            response.setMessage("Please provide subscriptionId");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}

		try {

            Payment payments = paymentRepo.findByOrder_OrderNumber(orderNumber);
            
        	if (payments == null) {
    			response.setData("");
                response.setMessage("Not payment found");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.OK);

                return response;
    		}
        	
    		response.setData(payments);
            response.setMessage("Get data successfully");
            response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
		}
		return response;
	}

	@Override
	public ServerResponse getPayments() {
		ServerResponse response = new ServerResponse();
		try {

            Collection<Payment> payments = (Collection<Payment>) paymentRepo.findAll();
            
        	if (payments.size() < 1) {
    			response.setData("");
                response.setMessage("Not payment found");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.OK);

                return response;
    		}
        	
    		response.setData(payments);
            response.setMessage("Get data successfully");
            response.setSuccess(true);
            response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
		}
		return response;
	}
	
	
}
