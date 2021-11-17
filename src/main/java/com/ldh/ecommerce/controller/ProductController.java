package com.ldh.ecommerce.controller;


import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    public ProductServiceImp productServiceImp = new ProductServiceImp();

    @GetMapping("/getAllProduct")
    public CommonResponse getAllProduct () {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), productServiceImp.getAllProduct());
    }
    @Autowired
    private EntityManager entityManager;


    @GetMapping("/searchProduct")
    public CommonResponse searchProduct (@RequestBody String searchKey) {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), productServiceImp.searchProduct(searchKey));
    }

}
