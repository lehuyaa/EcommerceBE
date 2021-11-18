package com.ldh.ecommerce.repository;


import com.ldh.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="select * from product p where p.user_id= :userId", nativeQuery=true)
    List<Product> findAllByUserId(Long userId);

    @Query(value="select * from product p where p.category_id= :categoryId", nativeQuery=true)
    List<Product> findAllByCategoryId(Long categoryId);

}
