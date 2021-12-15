package com.ldh.ecommerce.request;

public class ProductOrderRequest {
    private Long id;
    private Long idBuyer;
    private Long idSeller;
    private String nameSeller;
    private String productImage;
    private String productName;
    private Long productPrice;
    private Long quantity;
    private int location;

    public Long getId() {
        return id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Long idBuyer) {
        this.idBuyer = idBuyer;
    }

    public Long getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Long idSeller) {
        this.idSeller = idSeller;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
