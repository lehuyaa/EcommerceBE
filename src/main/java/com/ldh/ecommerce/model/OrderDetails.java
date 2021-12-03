package com.ldh.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private String productPrice;

    @Column(name = "productImage")
    private String productImage;

    @Column(name = "productQuantity")
    private Long productQuantity;

    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "total")
    private Long total;

    public OrderDetails() {
    }

    public OrderDetails(Long id, Long productId, String productName, String productPrice, String productImage, Long productQuantity, Long orderId, Long total) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productQuantity = productQuantity;
        this.orderId = orderId;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
