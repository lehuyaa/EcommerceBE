package com.ldh.ecommerce.service;

import com.ldh.ecommerce.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> getAllProduct();
    List<Product> getByUserId(Long userId);
    List<Product> getByCategoryId(Long categoryId);
}
