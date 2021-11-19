package com.ldh.ecommerce.repository;

import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.model.ShipAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipAddressRepository extends JpaRepository<ShipAddress, Long> {

    @Query(value="select * from ship_address s where s.user_id= :userId", nativeQuery=true)
    List<ShipAddress> findAllByUserId(Long userId);
}
