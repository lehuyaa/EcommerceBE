package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.Category;
import com.ldh.ecommerce.repository.CategoryRepository;
import com.ldh.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getAllCategory() {
        categories = categoryRepository.findAll();
        return categories;
    }
}
