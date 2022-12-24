package com.kisaragi.app.requests.order;

import java.util.List;

public class CreateOrderRequest {
    private int storeId;
    private String tracking;
    private String userId;
    private String address;

    private List<ProductsInOrderDTO> products;
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProductsInOrderDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsInOrderDTO> products) {
        this.products = products;
    }
}


