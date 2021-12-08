package com.ldh.ecommerce.service;

import com.ldh.ecommerce.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrderByUserId (Long userId);
    List<Order> getAllOrderBySellerId (Long sellerId);

}
