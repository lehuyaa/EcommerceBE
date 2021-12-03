package com.ldh.ecommerce.repository;

import com.ldh.ecommerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository  extends JpaRepository<OrderDetails, Long> {
}
