package com.ldh.ecommerce.request;

import java.util.List;

public class AddMultiProduct {
    private List<AddProductRequest> listProduct;


    public List<AddProductRequest> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<AddProductRequest> listProduct) {
        this.listProduct = listProduct;
    }
}
