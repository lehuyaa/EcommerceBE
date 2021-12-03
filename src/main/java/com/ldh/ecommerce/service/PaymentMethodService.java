package com.ldh.ecommerce.service;

import com.ldh.ecommerce.model.Payment;

import java.util.List;

public interface PaymentMethodService {
    List<Payment> getAllPaymentMethod();
}
