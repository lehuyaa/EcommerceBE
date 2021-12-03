package com.ldh.ecommerce.request;

import com.ldh.ecommerce.model.Payment;
import com.ldh.ecommerce.model.ShipAddress;

import java.util.List;

public class OrderRequest {

    private ShipAddress address;
    private Payment payment;
    private List<OrderItemRequest> listSeller;
    private Long userID;

    public ShipAddress getAddress() {
        return address;
    }

    public void setAddress(ShipAddress address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderItemRequest> getListSeller() {
        return listSeller;
    }

    public void setListSeller(List<OrderItemRequest> listSeller) {
        this.listSeller = listSeller;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
