package com.ldh.ecommerce.repository;

import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value="select * from review r where r.product_id= :productId", nativeQuery=true)
    List<Review> findAllByProductId(Long productId);
}
