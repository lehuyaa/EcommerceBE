package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.repository.ProductRepository;
import com.ldh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> products = new ArrayList<>();
    @Override
    public List<Product> getAllProduct() {
        products = productRepository.findAll();
        return products;
    }
}
