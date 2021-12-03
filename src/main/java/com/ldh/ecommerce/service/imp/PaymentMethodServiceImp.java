package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.Payment;
import com.ldh.ecommerce.repository.PaymentRepository;
import com.ldh.ecommerce.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodServiceImp implements PaymentMethodService {

    @Autowired
    public PaymentRepository paymentRepository;

    List<Payment> payments = new ArrayList<>();
    @Override
    public List<Payment> getAllPaymentMethod() {
        payments.clear();
        payments = paymentRepository.findAll();
        return payments;
    }
}
