package com.ldh.ecommerce.response;

import com.ldh.ecommerce.model.Product;
import com.ldh.ecommerce.model.User;

import java.util.List;

public class ProfileUserResponse {
    private User user;
    private List<Product> productList;


    public ProfileUserResponse(User user, List<Product> productList) {
        this.user = user;
        this.productList = productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
