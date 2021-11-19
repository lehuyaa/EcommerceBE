package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.ShipAddress;
import com.ldh.ecommerce.repository.ShipAddressRepository;
import com.ldh.ecommerce.service.ShipAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipAddressServiceImp implements ShipAddressService {

    @Autowired
    public ShipAddressRepository shipAddressRepository;

    public List<ShipAddress> shipAddresses = new ArrayList<>();

    @Override
    public List<ShipAddress> getAllByUserId(Long userId) {
        shipAddresses.clear();
        shipAddresses = shipAddressRepository.findAllByUserId(userId);
        return shipAddresses;
    }
}
