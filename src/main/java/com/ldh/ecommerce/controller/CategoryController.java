package com.ldh.ecommerce.controller;


import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    public CategoryServiceImp categoryServiceImp = new CategoryServiceImp();

    @GetMapping("/getAllCategory")
    public CommonResponse getAllCategory () {
        return new CommonResponse(HttpStatus.OK,new MessageResponse(""), categoryServiceImp.getAllCategory());
    }
}
