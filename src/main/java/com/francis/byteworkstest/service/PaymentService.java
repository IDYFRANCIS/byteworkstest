package com.francis.byteworkstest.service;

import org.springframework.stereotype.Service;

import com.francis.byteworkstest.dto.CardChargeRequest;
import com.francis.byteworkstest.dto.PayForOrderRequestDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.enumType.PaymentStatus;



@Service
public interface PaymentService {
	
    ServerResponse create(String userCode, PayForOrderRequestDto request);
	
	ServerResponse getPaymentByTransactionnumber(String transactionNumber);
	
	ServerResponse getPaymentsByStatus(PaymentStatus paymentStatus);
	
	ServerResponse getPaymentByOrder(String orderNumber);
	
	ServerResponse getPayments();


}
