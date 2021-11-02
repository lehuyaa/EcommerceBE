package com.ldh.ecommerce.service;

import com.ldh.ecommerce.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void changeName(String firstName, String lastName, String username);
}
