package com.ldh.ecommerce.response;

import java.util.List;

public class InfoProductOrder {
    private Long productId;
    private String productName;
    private Long productQuantity;
    private Long total;
    private String productImage;
    private List<String> listUser;


    public InfoProductOrder() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public List<String> getListUser() {
        return listUser;
    }

    public void setListUser(List<String> listUser) {
        this.listUser = listUser;
    }
}
