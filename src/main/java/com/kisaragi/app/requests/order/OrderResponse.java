package com.kisaragi.app.requests.order;

import com.kisaragi.app.order.State;

import java.util.List;

public class OrderResponse {
    private int order_id;
    private String tracking;
    private String user_id;
    private String store_name;
    private List<GetOrderProductsResponse> products;
    private float total;
    private String address;
    private State state;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public List<GetOrderProductsResponse> getProducts() {
        return products;
    }

    public void setProducts(List<GetOrderProductsResponse> products) {
        this.products = products;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
