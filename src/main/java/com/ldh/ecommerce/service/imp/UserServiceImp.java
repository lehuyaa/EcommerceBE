package com.ldh.ecommerce.service.imp;

import com.ldh.ecommerce.model.User;
import com.ldh.ecommerce.repository.UserRepository;
import com.ldh.ecommerce.response.CommonResponse;
import com.ldh.ecommerce.response.MessageResponse;
import com.ldh.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    public UserRepository userRepository;


    @Override
    public void changeName(String firstName, String lastName, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.get().setFirstName(firstName);
        user.get().setLastName(lastName);
        userRepository.save(user.get());

    }
}
