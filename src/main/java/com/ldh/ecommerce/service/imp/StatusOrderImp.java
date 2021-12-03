package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.StatusOrder;
import com.ldh.ecommerce.repository.StatusOrderRepository;
import com.ldh.ecommerce.service.StatusOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusOrderImp implements StatusOrderService {

    @Autowired
    public StatusOrderRepository statusOrderRepository;

    List<StatusOrder> statusOrders = new ArrayList<>();
    @Override
    public List<StatusOrder> getAll() {
        statusOrders.clear();
        statusOrders = statusOrderRepository.findAll();
        return statusOrders;
    }
}
