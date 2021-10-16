package com.ldh.ecommerce.request;

public class LoginRequest {
    private String username;
    private String password;

    /**
     * Create an empty LoginRequest object
     */
    public LoginRequest() {
        super();
    }

    /**
     * Create a LoginRequest object with full attributes
     *
     * @param username user's user name
     * @param password
     */
    public LoginRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}