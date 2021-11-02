package com.ldh.ecommerce.controller;

import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public UserServiceImp userServiceImp = new UserServiceImp();

    @PutMapping("/changeName")
    public String changeName (@RequestBody String firstName) {
//        userServiceImp.changeName(firstName, lastName, username);

        return"aaaaa";
    }
}
