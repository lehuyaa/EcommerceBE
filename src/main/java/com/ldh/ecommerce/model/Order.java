package com.ldh.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OrderTable")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long sellerId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "shipAddress_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ShipAddress shipAddress;

    @ManyToOne
    @JoinColumn(name = "status_order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StatusOrder statusOrder;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="orderId")
    private List<OrderDetails> orderDetails;

    public Order() {
    }

    public Order(Long id, Long userId, Long sellerId) {
        this.id = id;
        this.userId = userId;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ShipAddress getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(ShipAddress shipAddress) {
        this.shipAddress = shipAddress;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
