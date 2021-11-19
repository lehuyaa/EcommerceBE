package com.ldh.ecommerce.service;

import com.ldh.ecommerce.model.ShipAddress;

import java.util.List;

public interface ShipAddressService {

    List<ShipAddress> getAllByUserId(Long userId);
}
