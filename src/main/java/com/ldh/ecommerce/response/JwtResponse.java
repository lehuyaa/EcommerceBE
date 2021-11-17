package com.ldh.ecommerce.response;


import com.ldh.ecommerce.security.UserDetailsImpl;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private UserDetailsImpl user;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse(String token, UserDetailsImpl user) {
        this.token = token;
        this.user = user;
    }

    public UserDetailsImpl getUser() {
        return user;
    }

    public void setUser(UserDetailsImpl user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

