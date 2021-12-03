package com.ldh.ecommerce.request;

import java.util.List;

public class OrderItemRequest {
    private Long idSeller;
    private List<ProductOrderRequest> listProduct;


    public Long getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Long idSeller) {
        this.idSeller = idSeller;
    }

    public List<ProductOrderRequest> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductOrderRequest> listProduct) {
        this.listProduct = listProduct;
    }
}
