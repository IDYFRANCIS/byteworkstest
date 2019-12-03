package com.francis.byteworkstest.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.enumType.PaymentStatus;
import com.francis.byteworkstest.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String>{
	
	Payment findByTransactionNumber(String transactionNumber);
	
	Payment findByOrder_OrderNumber(String orderNumber);
	
	Collection<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

}
