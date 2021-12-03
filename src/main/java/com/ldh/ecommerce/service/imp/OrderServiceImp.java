package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.Order;
import com.ldh.ecommerce.repository.OrderRepository;
import com.ldh.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    public OrderRepository orderRepository;
    List<Order> orderList = new ArrayList<>();

    @Override
    public List<Order> getAllOrderByUserId(Long userId) {
        orderList.clear();
        orderList = orderRepository.findAllByUserId(userId);
        return orderList;
    }
}
