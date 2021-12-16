package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.repository.ProductRepository;
import com.ldh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> products = new ArrayList<>();


    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Product> getAllProduct(int pageNo) {


        Pageable paging=PageRequest.of(pageNo, 15) ;
        Page<Product> pagedResult = productRepository.findAll(paging);

        return pagedResult.getContent();
    }

    @Override
    public List<Product> getByUserId(Long userId) {
        products.clear();
        products = productRepository.findAllByUserId(userId);
        return products;
    }


    @Override
    public List<Product> getByCategoryId(Long categoryId) {
        products.clear();
        products = productRepository.findAllByCategoryId(categoryId);
        return products;
    }

    @Override
    public List<Product> getRanDomProductByCategoryId(Long categoryId) {
        products.clear();
        products = productRepository.findAllByCategoryId(categoryId);
        Random rand = new Random();
        List<Product> randomList = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            randomList.add(products.remove(rand.nextInt(products.size())));
        }
        return randomList;
    }
}
