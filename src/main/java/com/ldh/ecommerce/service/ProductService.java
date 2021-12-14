package com.ldh.ecommerce.service;

import com.ldh.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {

    List<Product> getAllProduct(int pageNo);
    List<Product> getByUserId(Long userId);
    List<Product> getByCategoryId(Long categoryId);
    List<Product> getRanDomProductByUserId(Long userId);
}
