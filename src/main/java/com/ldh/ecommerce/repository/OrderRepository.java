package com.ldh.ecommerce.repository;

import com.ldh.ecommerce.model.Order;
import com.ldh.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

    @Query(value="select * from order_table o where o.user_id= :userId", nativeQuery=true)
    List<Order> findAllByUserId(Long userId);

    @Query(value="select * from order_table o where o.seller_id= :sellerId", nativeQuery=true)
    List<Order> findAllBySellerId(Long sellerId);
}
