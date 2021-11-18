package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.repository.ProductRepository;
import com.ldh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

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
    public List<Product> getAllProduct() {
        products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> searchProduct(String searchKey) {
        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();



        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("productName")
                .matching(searchKey)
                .createQuery();
        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, Product.class);
        return jpaQuery.getResultList();
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
}
